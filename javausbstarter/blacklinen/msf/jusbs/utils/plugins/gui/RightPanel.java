package blacklinen.msf.jusbs.utils.plugins.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import blacklinen.msf.jusbs.utils.plugins.PluginController;
import blacklinen.msf.jusbs.utils.plugins.RightPanelManager;

@SuppressWarnings("serial")
public class RightPanel extends JPanel
{
	protected RightPanelManager cont;
	protected PluginController plugins;
	
	public RightPanel(RightPanelManager manag)
	{
		super();
		this.cont = manag;
	}
	
	protected void makeGUI()
	{
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
}
