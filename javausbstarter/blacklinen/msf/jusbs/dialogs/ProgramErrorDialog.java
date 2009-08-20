package blacklinen.msf.jusbs.dialogs;

import java.awt.event.ActionEvent;

import blacklinen.msf.jusbs.Controller;

@SuppressWarnings("serial")
public class ProgramErrorDialog extends ErrorDialog {

	public ProgramErrorDialog(Controller cont) 
	{
		super(cont);
		super.setTitle("JavaUSBStarter wasn't able to start this program");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		super.setVisible(false);
		super.dispose();
	}

	public void showDialog(Exception e)
	{
		super.showDialog(e);
		String message = super.text.getText();
		super.text.setText("Could't start this program.\n"+message);
	}
}
