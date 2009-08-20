package blacklinen.msf.jusbs.data.visual.datapanels;

import java.awt.event.ActionEvent;

import blacklinen.msf.jusbs.data.MainPanelController;

@SuppressWarnings("serial")
public class FileObjectPanel extends DataPanel 
{
	public FileObjectPanel(String name,int index, MainPanelController cont) 
	{
		super(name, "Open ", "Open this file in system standard application", index, DataPanel.DATA, cont);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		super.cont.openData(super.index);
	}

}
