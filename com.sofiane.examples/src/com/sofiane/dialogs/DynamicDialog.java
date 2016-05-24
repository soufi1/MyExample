package com.sofiane.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class DynamicDialog extends Dialog {
	private ComboViewer combo;
	private String daten = "daten";
	private String infos = "infos";
	private String calander = "Calander";
	private String checkBox = "Checkbox";
	private Object value;
	private int count = 1;
	public Text mytext;
	FormToolkit toolkit;

	public DynamicDialog(Shell shell) {
		super(shell);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout());

		final ScrolledComposite scrolledComposite = new ScrolledComposite(
				container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		final Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		scrolledComposite.setContent(composite);
		scrolledComposite.setSize(composite.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));

		final Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false));

		final Label lblDefault = new Label(composite_1, SWT.NONE);
		lblDefault.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblDefault.setText("Default:");

		final ComboViewer combo = new ComboViewer(composite_1, SWT.NONE);
		final String tab[] = { "infos", "daten", "Calander", "Checkbox" };
		combo.setContentProvider(new ArrayContentProvider());
		combo.setLabelProvider(new LabelProvider());
		combo.setInput(tab);
		combo.setSelection(new StructuredSelection(tab[0]));

		combo.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();

				if (selection.size() > 0) {

					value = (Object) selection.getFirstElement();

					if (value.equals(daten)) {
						System.out.println(value);
					} else if (value.equals(infos)) {
						System.out.println(value);

					} else if (value.equals(checkBox)) {
						System.out.println(value);

					} else if (value.equals(calander)) {
						System.out.println(value);

					}

					// // showMessage("Selected element  : "
					// // + selection.getFirstElement());
				}
			}
		});

		final Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(3, false));
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false));

		final Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new GridLayout());
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false));

		Button btnAdd = new Button(composite_3, SWT.NONE);
		btnAdd.setText("weiter");
		final String table[] = { "Name", "Vorname", "email" };
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final Label label2 = new Label(composite_2, SWT.NONE);
				label2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
						false, 1, 1));
				label2.setText(String.valueOf(count++));
				final Label label3 = new Label(composite_2, SWT.NONE);
				label3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
						false, 1, 1));

				label3.setText(table[0]);

				mytext = new Text(composite_2, SWT.BORDER);
				mytext.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
						false));

				// DO THIS:
				scrolledComposite.layout(true, true);
				scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT,
						SWT.DEFAULT));

			}
		});

		return container;
	}

	@Override
	protected void createButtonsForButtonBar(final Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	// overriding this methods allows you to set the
	// title of the custom dialog
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Selection dialog");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500, 350);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

	private void saveInput() {
		infos = mytext.getText();
	}

	public ComboViewer getCombo() {
		return combo;
	}

	public void setCombo(ComboViewer combo) {
		this.combo = combo;
	}

	public String getDaten() {
		return daten;
	}

	public void setDaten(String daten) {
		this.daten = daten;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public String getCalander() {
		return calander;
	}

	public void setCalander(String calander) {
		this.calander = calander;
	}

	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void cleare() {

	}

//	private void showMessage(String message) {
//		MessageDialog.openInformation(combo.getControl().getShell(), "Message",
//				message);
//	}

}
