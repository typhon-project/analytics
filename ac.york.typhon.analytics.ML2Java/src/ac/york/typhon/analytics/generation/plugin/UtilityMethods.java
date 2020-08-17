package ac.york.typhon.analytics.generation.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.eclipse.pde.internal.core.natures.PDE;

import it.univaq.disim.typhon.TyphonMLStandaloneSetupGenerated;

public class UtilityMethods {

	static String name;
	static IProgressMonitor progressMonitor = new NullProgressMonitor();
	static IFile theIFile;
	IWorkspace workspace = ResourcesPlugin.getWorkspace();
	IWorkspaceRoot root = workspace.getRoot();
	static IProject project;

	public static IJavaProject createPluginProject(IFile theFile) throws CoreException {
		theIFile = theFile;
		name = theFile.getName();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		project = root.getProject(name);
		if (!project.exists()) {
			project.create(progressMonitor);
		}
		project.open(progressMonitor);
		IProjectDescription desc = project.getDescription();
		desc.setNatureIds(new String[] { PDE.PLUGIN_NATURE, JavaCore.NATURE_ID });
		project.setDescription(desc, progressMonitor);

		IJavaProject javaProject = JavaCore.create(project);

		IFolder binFolder = project.getFolder("bin");
		binFolder.create(false, true, null);
		javaProject.setOutputLocation(binFolder.getFullPath(), null);

		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
		LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
		for (LibraryLocation element : locations) {
			entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
		}
		// add libs to project class path
		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);

		IFolder sourceFolder = project.getFolder("src");
		sourceFolder.create(false, true, null);

		IPackageFragmentRoot packageRoot = javaProject.getPackageFragmentRoot(sourceFolder);
		IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
		IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
		newEntries[oldEntries.length] = JavaCore.newSourceEntry(packageRoot.getPath());
		javaProject.setRawClasspath(newEntries, null);

		return javaProject;
	}

	public static void createTheManifestFile(String theSelectedFilePath, String theDestinationIProjectFolder)
			throws IOException {
		new File(theDestinationIProjectFolder + File.separator + "META-INF").mkdir();
		BufferedWriter output = new BufferedWriter(new FileWriter(
				theDestinationIProjectFolder + File.separator + "META-INF" + File.separator + "MANIFEST.MF", false));
		try {
			output.write("Manifest-Version: 1.0\n" + "Bundle-ManifestVersion: 2\n" + "Bundle-Name: " + name + "\n"
					+ "Bundle-SymbolicName: " + name + ";singleton:=true\n" + "Bundle-Version: 1.0.0.qualifier\n"
					+ "Require-Bundle: org.eclipse.core.runtime\n");
			output.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file...");
		}
	}

	public static void generateClasses(String theSelectedFilePath, String theDestinationIProjectFolder,
			IProject theSelectedFileParentIProject) throws Exception {

		// Required (most probably) to register metamodels and factories.
		TyphonMLStandaloneSetupGenerated setup = new TyphonMLStandaloneSetupGenerated();
		setup.createInjectorAndDoEMFRegistration();

		// Create the standalone EgxModule
		EglFileGeneratingTemplateFactory factory = new EglFileGeneratingTemplateFactory();
		EgxModule egxModule = new EgxModule(factory);
		java.net.URI EgxFile = Activator.getDefault().getBundle().getResource("files/orchestrator.egx").toURI();
		egxModule.parse(EgxFile);
		factory.setOutputRoot(new File(theDestinationIProjectFolder).toURI().toString());

		// Our models include cross-references to other models. By simply creating and
		// loading the base EMF model, cross-references are not resolved.
		// Thus we need to load all the models in a resource set and create an
		// InMemoryEMFModel.
		ResourceSet resSet = new ResourceSetImpl();

		// Base model resource.
		Resource dbResource = resSet.createResource(
				org.eclipse.emf.common.util.URI.createPlatformResourceURI(theIFile.getFullPath().toOSString(), true));
		dbResource.load(null);

		// In this version we iterate in the project, locate all the files with
		// extension .tml and put them in the resource set.
		File dir = new File(theSelectedFileParentIProject.getLocationURI());
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith(".tml") && !name.contains(theIFile.getName()));
			}
		});
		for (File tmlfile : files) {
			Resource res = resSet
					.createResource(org.eclipse.emf.common.util.URI.createFileURI(tmlfile.getAbsolutePath()));
			res.load(null);
		}

		// In order to create the in memory emf model we need the package. We get it, we
		// create the model and put it in the repository.
		EPackage p = (EPackage) EPackage.Registry.INSTANCE.get("http://org.typhon.dsls.typhonml.sirius");
		final EmfModel sourceModel = new InMemoryEmfModel("TML", dbResource, p);
		egxModule.getContext().getModelRepository().addModel(sourceModel);

		// The following should also work but because of the cross-reference it doesn't.
		// EmfModel sourceModel =
		// createAndLoadAnEmfModel("org.typhon.dsls.typhonml.sirius",
		// theSelectedFilePath, "TML", "true", "false");

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

	}

	protected static URI getFileURI(String fileName) throws URISyntaxException {

		System.out.println(UtilityMethods.class.getResource(fileName));
		URI binUri = UtilityMethods.class.getResource(fileName).toURI();
		URI uri = null;

		if (binUri.toString().indexOf("bin") > -1) {
			uri = new URI(binUri.toString().replaceAll("bin", "src"));
		} else {
			uri = binUri;
		}

		return uri;
	}

	public static void copyTheStaticContentClasses(String theDestinationIProjectFolder, IJavaProject theDestinationIProject) throws IOException, URISyntaxException {

		String theTargetDirectory = theDestinationIProjectFolder + File.separator + "src/org/typhon/entities/";
		System.out.println("The target dir: " + theTargetDirectory);
		File targetDir = new File(theTargetDirectory);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		HashMap<String, String> myMap = getAllFilesThatNeedToBeCopied();
		for (String fromFileString : myMap.keySet()) {
			String toFileString = myMap.get(fromFileString);
			URL fromFilePath = Activator.getDefault().getBundle().getResource("files/staticContent/" + fromFileString);
			File fromFile = new File(FileLocator.toFileURL(fromFilePath).getPath());
			File toFile = new File(theDestinationIProjectFolder + File.separator + toFileString);
			FileUtils.copyFile(fromFile, toFile);
		}
	}


	public static void createThePluginXmlFile(String theDestinationIProjectFolder) throws IOException {
		BufferedWriter output = new BufferedWriter(
				new FileWriter(theDestinationIProjectFolder + File.separator + "plugin.xml", false));
		try {
			output.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "	<?eclipse version=\"3.4\"?>\n"
					+ "	<plugin>\n" + "   </plugin>");
			output.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file...");
		}
	}

	public static void createThebuildPropertiesFile(String theDestinationIProjectFolder) throws IOException {
		BufferedWriter output = new BufferedWriter(
				new FileWriter(theDestinationIProjectFolder + File.separator + "build.properties", false));
		try {
			output.write("source.. = src/ \n output.. = bin/ \n bin.includes = META-INF/,\\\n.,\\\n" + "plugin.xml\n");
			output.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file...");
		}
	}

	private static EmfModel createAndLoadAnEmfModel(String metamodelURI, String modelFile, String modelName,
			String readOnLoad, String storeOnDisposal) throws EolModelLoadingException {
		EmfModel theModel = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_METAMODEL_URI, metamodelURI);
		properties.put(EmfModel.PROPERTY_MODEL_FILE, modelFile);
		properties.put(EmfModel.PROPERTY_NAME, modelName);
		properties.put(EmfModel.PROPERTY_EXPAND, true);
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad);
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, storeOnDisposal);
		properties.put(EmfModel.PROPERTY_ALIASES, "TML");
		theModel.load(properties, (IRelativePathResolver) null);
		return theModel;
	}

	public static void refresh(IProject parentProject) throws CoreException {
		parentProject.getProject().refreshLocal(2, null);
	}
	
	public static void refresh(IJavaProject parentProject) throws CoreException {
		parentProject.getProject().refreshLocal(2, null);
	}
	
	public static HashMap<String, String> getAllFilesThatNeedToBeCopied() {
		HashMap<String,String> myMap = new HashMap<String,String>();
		myMap.put("Entity.java", "src/org/typhon/entities/Entity.java");
		myMap.put("Event.java", "src/org/typhon/events/Event.java");
		myMap.put("PostEvent.java", "src/org/typhon/events/PostEvent.java");
		myMap.put("PreEvent.java", "src/org/typhon/events/PreEvent.java");
		myMap.put("Database.java", "src/org/typhon/databases/Database.java");
		myMap.put("Delete.java", "src/org/typhon/commands/Delete.java");
		myMap.put("Insert.java", "src/org/typhon/commands/Insert.java");
		myMap.put("Update.java", "src/org/typhon/commands/Update.java");
		myMap.put("Select.java", "src/org/typhon/commands/Select.java");
		myMap.put("DMLCommand.java", "src/org/typhon/commands/DMLCommand.java");

		return myMap;
		
	}
}