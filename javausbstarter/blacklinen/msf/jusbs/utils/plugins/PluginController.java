package blacklinen.msf.jusbs.utils.plugins;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import blacklinen.msf.jusbs.utils.plugins.data.PluginClassLoader;

public class PluginController 
{
	protected RightPanelManager cont;
	protected String location;
	protected PluginClassLoader loader;
	protected ArrayList<Pluginable> plugins;
	
	public PluginController(RightPanelManager manag)
	{
		this.cont = manag;
		this.location = new String(this.cont.getPluginLocation());
		this.loader = new PluginClassLoader(this);
		this.plugins = new ArrayList<Pluginable>();
	}
	
	public void lookup() 
	{
		System.out.println(this.location);
		File f = new File(this.location);
		System.out.println(f.exists());
		String[] arr = f.list();
		for(String str : arr)
		{
			if(!str.endsWith(".class"))
				continue;
			else if(str.contains("$"))
				continue;
			else
				try
				{
					Class<? extends Pluginable> cla = this.loader.loadPlugin(str);
					if(cla != null)
						this.plugins.add(cla.newInstance());
				} 
				// TODO Make Exceptionhandling
				catch (MalformedURLException e)
				{
					e.printStackTrace();
				} 
				
				catch (InstantiationException e)
				{
					e.printStackTrace();
				} 
				
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				} 
				
				catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
	}
	
	public ArrayList<Pluginable> getPlugins()
	{
		return this.plugins;
	}
	public String getLocation()
	{
		return this.location;
	}
}
