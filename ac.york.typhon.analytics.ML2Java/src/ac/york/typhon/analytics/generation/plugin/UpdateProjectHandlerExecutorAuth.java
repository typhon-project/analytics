package ac.york.typhon.analytics.generation.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfMetaModel;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.types.EolAnyType;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;

public class UpdateProjectHandlerExecutorAuth implements IRunnableWithProgress {

	private ExecutionEvent event;
	private String theIProjectPath;
	private IProject theIProject;
	private IFile theFile;
	private String url;

	public UpdateProjectHandlerExecutorAuth(IFile theFile, IProject theIProject, String theIProjectPath, ExecutionEvent event) {
		this.event = event;
		this.theIProject = theIProject;
		this.theIProjectPath = theIProjectPath;
		this.theFile = theFile;
	}

	@Override
	public void run(IProgressMonitor pm) throws InvocationTargetException, InterruptedException {

		try {

			// Create the standalone EgxModule
			EglFileGeneratingTemplateFactory factory = new EglFileGeneratingTemplateFactory();
			EgxModule egxModule = new EgxModule(factory);

			egxModule.getContext().getFrameStack().put(new Variable("path", theIProjectPath, new EolAnyType()));

			System.out.println(Activator.getDefault());
			java.net.URI EgxFile = Activator.getDefault().getBundle().getResource("files/authOrchestrator.egx").toURI();
			java.net.URI mm = Activator.getDefault().getBundle().getResource("models/authDSL.ecore").toURI();
			egxModule.parse(EgxFile);

			factory.setOutputRoot(new File(theIProjectPath).toURI().toString());
			ResourceSet resSet = new ResourceSetImpl();
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("flexmi", new XMIResourceFactoryImpl());
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMIResourceFactoryImpl());
			InputStream inputStream = UpdateProjectHandlerAuth.class.getResourceAsStream("/authDSL.ecore");
			Resource resource = resSet.createResource(URI.createURI("auth"));
			System.out.println(resource);
			System.out.println("inputStream" + inputStream);
			resource.load(inputStream, new HashMap<>());	
//			res.load(null);
			for(EObject o : resource.getContents()) {
				if (o instanceof EPackage) {
					EPackage.Registry.INSTANCE.put(((EPackage) o).getNsURI(), o);
				}
			}

			EmfModel sourceModel = 	createAndLoadAnEmfModel("authDSL", theFile.getRawLocation().toOSString(), "M", "true", "false");
		
			egxModule.getContext().getModelRepository().addModel(sourceModel);

			// Run a quick check to make sure we don't have any unresolved proxies in the
			// base model
			Map<EObject, Collection<Setting>> unresolved = EcoreUtil.UnresolvedProxyCrossReferencer.find(resSet);
			for (Collection<Setting> unresolvedSettings : unresolved.values()) {
				for (Setting setting : unresolvedSettings) {
					System.out.println("Unresolved proxy in " + setting.getEStructuralFeature().getName() + " for "
							+ setting.getEObject());
				}
			}

			// Execute the transformation.
			egxModule.execute();

			//
			UtilityMethodsAuth.refresh(theIProject);
			System.out.println("entities generated successfully.");

			//

			File pomfile = new File(theIProjectPath + "/pom.xml");
			if (!pomfile.exists()) {
				pomfile.createNewFile();
				// System.out.println(pomfile.getAbsolutePath());
				FileWriter fw2 = new FileWriter(pomfile);
				fw2.write(DefaultPOMAuth.contents());
				fw2.close();

				UtilityMethodsAuth.refresh(theIProject);
				System.out.println("project pom updated, please run an update in maven if needed.");
			}

		} catch (Exception ex) {
			LogUtil.log(ex);
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error",
							"An error has occured. Please see the Error Log.");
				}
			});
		} 
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	private EmfModel createAndLoadAnEmfModel(String metamodelURI, String modelFile, String modelName, String readOnLoad, String storeOnDisposal) throws EolModelLoadingException {
		EmfModel theModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_METAMODEL_URI, metamodelURI);
		properties.put(EmfModel.PROPERTY_MODEL_FILE, modelFile);
		properties.put(EmfModel.PROPERTY_NAME, modelName);
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad);
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, storeOnDisposal);
		theModel.load(properties, (IRelativePathResolver) null);
		return theModel;
	}
}
