package blacklinen.msf.jusbs.data.visual.datapanels;

import java.awt.event.ActionEvent;

import blacklinen.msf.jusbs.data.MainPanelController;

@SuppressWarnings("serial")
public class ProgramPanel extends DataPanel 
{
	protected String path;
	
	public ProgramPanel(String name, String path,int index,MainPanelController cont)
	{
		super(name,"Start ","Start "+path,index, DataPanel.PROGRAMS, cont);
		this.path = path;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{	
		cont.start(super.index);
	}
}
