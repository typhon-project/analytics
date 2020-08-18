package ac.york.typhon.analytics.generation.plugin;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;

public class UpdateProjectHandler implements IHandler {

	private String theIProjectPath;
	private IProject theIProject;

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
		if (!isIProject(event)) {
			MessageDialog.openError(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Error", "Not a project.");
			return null;
		}

		try {
			IRunnableWithProgress op = new UpdateProjectHandlerExecutor(theIProject, theIProjectPath, event);
			new ProgressMonitorDialog(HandlerUtil.getActiveWorkbenchWindow(event).getShell()).run(true, true, op);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean isIProject(ExecutionEvent event) {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selection instanceof ITreeSelection) {
			theIProject = (IProject) ((IAdaptable) ((TreeSelection) selection).getPaths()[0].getFirstSegment())
					.getAdapter(IProject.class);
			if (theIProject != null)
				theIProjectPath = theIProject.getProject().getLocation().toOSString();
			return theIProject != null;
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
