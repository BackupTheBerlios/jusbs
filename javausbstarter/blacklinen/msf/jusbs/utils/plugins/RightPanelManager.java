package blacklinen.msf.jusbs.utils.plugins;

import java.io.File;

import blacklinen.msf.jusbs.Controller;

public class RightPanelManager
{
	protected Controller cont;
	protected PluginController plugins;
	
	public RightPanelManager(Controller cont)
	{
		this.cont = cont;
		this.plugins = new PluginController(this);
	}
	
	protected void load()
	{
		this.plugins.lookup();
		
	}
	
	public String getPluginLocation()
	{
		return this.cont.getMountPoint()+".jusbs"+File.separator+"plugins"+File.separator;
	}
}
