package net.zolotas.typhon.generation.plugin;

import javax.swing.text.Utilities;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.epsilon.common.dt.util.LogUtil;
import org.eclipse.epsilon.emc.emf.CachedResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class GenerateHandler implements IHandler {

    private IFile theFile;
	protected IJavaProject theDestinationIProject;
	protected String theDestinationIProjectFolder;
	private IProject theSelectedFileParentIProject;
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if(!getClickedFile(event)) {
        	MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error", "Something went wrong.");
        	return null;
		}
        
		if(!theFile.getFileExtension().equals("tml")) {
        	MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error", "This is not a TyphonML (.tml) file.");
        	return null;
        }
		
		try {
			 IRunnableWithProgress op = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					SubMonitor subMonitor = SubMonitor.convert(monitor, 70);
					try {
						theDestinationIProject = UtilityMethods.createPluginProject(theFile);
						//theDestinationIProject.getLocation().
						theDestinationIProjectFolder = theDestinationIProject.getProject().getLocation().toOSString();
						subMonitor.setTaskName("Generating the Plugin XML.");
						subMonitor.split(10);
						UtilityMethods.createThePluginXmlFile(theDestinationIProjectFolder);
						subMonitor.setTaskName("Generating the classes.");
						subMonitor.split(30);
						UtilityMethods.copyTheStaticContentClasses(theDestinationIProjectFolder, theDestinationIProject);
						subMonitor.setTaskName("Generating the classes.");
						subMonitor.split(30);
						UtilityMethods.generateClasses(theFile.getLocation().toOSString(), theDestinationIProjectFolder, theSelectedFileParentIProject);
						subMonitor.setTaskName("Generating the Project Manifest.");
						subMonitor.split(10);
						UtilityMethods.createTheManifestFile(theFile.getLocation().toOSString(), theDestinationIProjectFolder);
						subMonitor.setTaskName("Generating Build Properties.");
						subMonitor.split(10);
						UtilityMethods.createThebuildPropertiesFile(theDestinationIProjectFolder);
						subMonitor.setTaskName("Refreshing");
						subMonitor.split(10);
						UtilityMethods.refresh(theDestinationIProject);
					} catch (Exception ex) {
						LogUtil.log(ex);
						PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
							public void run() {
								MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error",
										"An error has occured. Please see the Error Log.");
							}
						});
					} finally {
						CachedResourceSet.getCache().clear();
					}
				}
			};
			 new ProgressMonitorDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell()).run(true, true, op);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
        return theFile;
    }
	
    private boolean getClickedFile(ExecutionEvent event) {
    	ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
        if(selection instanceof ITreeSelection) {
            theSelectedFileParentIProject = (IProject) ((IAdaptable) ((TreeSelection) selection).getPaths()[0].getFirstSegment()).getAdapter(IProject.class);
            theFile = (IFile) ((IAdaptable) ((TreeSelection) selection).getPaths()[0].getLastSegment()).getAdapter(IFile.class);

            return true;
        }
        return false;
    }
   
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
