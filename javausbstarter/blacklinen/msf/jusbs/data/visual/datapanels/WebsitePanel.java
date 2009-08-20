package blacklinen.msf.jusbs.data.visual.datapanels;

import java.awt.event.ActionEvent;

import blacklinen.msf.jusbs.data.MainPanelController;

@SuppressWarnings("serial")
public class WebsitePanel extends DataPanel {

	public WebsitePanel(String name,int index, MainPanelController cont) 
	{
		super(name, "Open ", "Open in System default browser.", index, DataPanel.LINKS, cont);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		super.cont.openWebsite(super.index);
	}

}
