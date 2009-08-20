package blacklinen.msf.jusbs.utils.plugins.data;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import blacklinen.msf.jusbs.utils.plugins.PluginController;
import blacklinen.msf.jusbs.utils.plugins.Pluginable;

public class PluginClassLoader
{
	protected PluginController cont;
	protected ClassLoader loader;
	
	public PluginClassLoader(PluginController cont)
	{
		this.cont = cont;
	}
	
	@SuppressWarnings("unchecked")
	public Class<? extends Pluginable> loadPlugin(String name) throws MalformedURLException, ClassNotFoundException
	{
		URL urls[] = new URL[]{new File(this.cont.getLocation()+File.separator+name).toURI().toURL()};
		this.loader = new URLClassLoader(urls);
		Class plugin = this.loader.loadClass(name.substring(0, name.lastIndexOf(".")));
		Class[] interfaces = plugin.getInterfaces();
		boolean conform = false;
		for(Class cla : interfaces)
		{
			if(cla.getName().equalsIgnoreCase("blacklinen.msf.jusbs.utils.plugins.Pluginable"))
				conform = true;
			System.out.println(cla.getName());
		}
		if(!conform)
			plugin = null;
		return plugin;
	}
}
