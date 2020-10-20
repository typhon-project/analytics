package ac.york.typhon.analytics.generation.plugin;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Base64;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.execute.context.Variable;
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

import it.univaq.disim.typhon.TyphonMLStandaloneSetupGenerated;

public class UpdateProjectHandlerExecutor implements IRunnableWithProgress {

	private ExecutionEvent event;
	private String theIProjectPath;
	private IProject theIProject;
	private String url;

	public UpdateProjectHandlerExecutor(IProject theIProject, String theIProjectPath, ExecutionEvent event) {
		this.event = event;
		this.theIProject = theIProject;
		this.theIProjectPath = theIProjectPath;
	}

	@Override
	public void run(IProgressMonitor pm) throws InvocationTargetException, InterruptedException {

		File tmlfile = null;
		try {

			// Required (most probably) to register metamodels and factories.
			TyphonMLStandaloneSetupGenerated setup = new TyphonMLStandaloneSetupGenerated();
			setup.createInjectorAndDoEMFRegistration();

			// Create the standalone EgxModule
			EglFileGeneratingTemplateFactory factory = new EglFileGeneratingTemplateFactory();
			EgxModule egxModule = new EgxModule(factory);

			egxModule.getContext().getFrameStack().put(new Variable("path", theIProjectPath, new EolAnyType()));

			java.net.URI EgxFile = Activator.getDefault().getBundle().getResource("files/orchestrator.egx").toURI();

			egxModule.parse(EgxFile);

			factory.setOutputRoot(new File(theIProjectPath).toURI().toString());

			// retrieve the tml file from the server
			String defaulturl = "http://localhost:8080";
			String name = "admin";
			String password = "admin1@";
			String authString = name + ":" + password;
			String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());
						
			// open wizard for changing the url of the typhon polystore

			AnalyticsWizard w = new AnalyticsWizard(this, defaulturl);
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					WizardDialog dialog = new WizardDialog(shell, w);
					dialog.open();
				}
			});

			//

			if (!(url.length() > 0))
				return;

			Client restClient = Client.create();
			WebResource webResource = restClient.resource(url + "/models");

			// Start timing for calculating execution time
			ClientResponse resp = webResource.accept("application/json")
					.header("Authorization", "Basic " + authStringEnc).get(ClientResponse.class);
			if (resp.getStatus() != 200) {
				System.err.println("Unable to connect to the server");
			}

			String output = resp.getEntity(String.class);

			System.out.println(output);

			Object obj = new JSONParser().parse(output);

			JSONObject responceJSON = (JSONObject) obj;

			JSONObject embedded = (JSONObject) responceJSON.get("_embedded");

			JSONArray models = (JSONArray) embedded.get("models");

			JSONObject tml = null;
			for (Object o : models) {
				JSONObject jo = (JSONObject) o;
				if (jo.get("type").equals("ML"))
					tml = jo;
			}

			String model = tml.get("contents").toString();

			// System.out.println(model);

			//

			tmlfile = new File("tml.tml");
			if (!tmlfile.exists())
				tmlfile.createNewFile();
			FileWriter fw = new FileWriter(tmlfile);
			fw.write(model);
			fw.close();
			//

			ResourceSet resSet = new ResourceSetImpl();

			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("tml", new XMIResourceFactoryImpl());

			Resource res = resSet
					.createResource(org.eclipse.emf.common.util.URI.createFileURI(tmlfile.getAbsolutePath()));
			res.load(null);

			// res.getAllContents().forEachRemaining((k) -> {
			// System.out.println(k + " : " + k.eClass());
			// });

			// System.out.println(">" + res + "<");

			// In order to create the in memory emf model we need the package. We get it, we
			// create the model and put it in the repository.
			EPackage p = (EPackage) EPackage.Registry.INSTANCE.get("http://org.typhon.dsls.typhonml.sirius");

			final EmfModel sourceModel = new InMemoryEmfModel("TML", res, p);
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
			UtilityMethods.refresh(theIProject);
			System.out.println("entities generated successfully.");

			//

			File pomfile = new File(theIProjectPath + "/pom.xml");
			if (!pomfile.exists()) {
				pomfile.createNewFile();
				// System.out.println(pomfile.getAbsolutePath());
				FileWriter fw2 = new FileWriter(pomfile);
				fw2.write(DefaultPOM.contents());
				fw2.close();

				UtilityMethods.refresh(theIProject);
				System.out.println("project pom updated, please run an update in maven if needed.");
			}

			//

			File defaultanalyticsrunner = new File(theIProjectPath + "/src/analytics/DefaultAnalyticsRunner.java");
			if (!defaultanalyticsrunner.exists()) {
				File dirs = new File(theIProjectPath + "/src/analytics/");
				dirs.mkdirs();						
				defaultanalyticsrunner.createNewFile();
				FileWriter fw2 = new FileWriter(defaultanalyticsrunner);
				fw2.write(DefaultAnalyticsRunner.contents());
				fw2.close();

				UtilityMethods.refresh(theIProject);
			}

			//

			File defaultanalytics = new File(theIProjectPath + "/src/analytics/DefaultAnalytics.java");
			if (!defaultanalytics.exists()) {
				File dirs = new File(theIProjectPath + "/src/analytics/");
				dirs.mkdirs();
				defaultanalytics.createNewFile();
				FileWriter fw2 = new FileWriter(defaultanalytics);
				fw2.write(DefaultAnalytics.contents());
				fw2.close();

				UtilityMethods.refresh(theIProject);
			}

			//

		} catch (Exception ex) {
			LogUtil.log(ex);
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error",
							"An error has occured. Please see the Error Log.");
				}
			});
		} finally {
			try {
				tmlfile.delete();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
