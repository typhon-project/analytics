package ac.york.typhon.analytics.generation.plugin;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AnalyticsWizardPage extends WizardPage {

	private String defaulturl;
	private Text url;
	private Composite container;

	public AnalyticsWizardPage(String defaulturl) {
		super("Update URL");
		setTitle("Update URL");
		setControl(url);
		this.defaulturl = defaulturl;
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Typhon Polystore URL:");

		url = new Text(container, SWT.BORDER | SWT.SINGLE);
		url.setText(defaulturl);
		url.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!url.getText().isEmpty())
					setPageComplete(true);
				else
					setPageComplete(false);
			}

		});
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		url.setLayoutData(gd);

		// required to avoid an error in the system
		setControl(container);
		//
		setPageComplete(url.getText().length()>0);
	}

	public String getURL() {
		return url.getText();
	}
}
