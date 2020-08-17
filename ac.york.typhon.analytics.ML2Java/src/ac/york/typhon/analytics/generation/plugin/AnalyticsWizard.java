package ac.york.typhon.analytics.generation.plugin;

import org.eclipse.jface.wizard.Wizard;

public class AnalyticsWizard extends Wizard {

	protected AnalyticsWizardPage page;
	private UpdateProjectHandlerExecutor host;
	private String defaulturl;

	public AnalyticsWizard(UpdateProjectHandlerExecutor updateProjectHandlerExecutor, String defaulturl) {
		super();
		this.host = updateProjectHandlerExecutor;
		this.defaulturl = defaulturl;
		setNeedsProgressMonitor(true);
	}

	@Override
	public String getWindowTitle() {
		return "Update Typhon Polystore URL";
	}

	@Override
	public void addPages() {
		page = new AnalyticsWizardPage(defaulturl);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		host.setUrl(page.getURL());
		return true;
	}
}
