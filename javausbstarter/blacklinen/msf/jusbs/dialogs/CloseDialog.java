package blacklinen.msf.jusbs.dialogs;

import java.awt.event.ActionEvent;

import blacklinen.msf.jusbs.Controller;

@SuppressWarnings("serial")
public class CloseDialog extends ErrorDialog 
{

	public CloseDialog(Controller cont) 
	{
		super(cont);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
			System.exit(0);
	}

}
