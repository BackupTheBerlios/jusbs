package blacklinen.msf.jusbs.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import blacklinen.msf.jusbs.Controller;

@SuppressWarnings("serial")
@Deprecated
public class Settings extends Properties implements Data
{
	private Controller cont;
	
	public Settings(Controller cont)
	{
		this.cont = cont;
	}
	public void reset()
	{
		setProperty("height","768");
		setProperty("width","1024");
		setProperty("fullscreen","false");
	}

	@Override
	public boolean check() {
		if(getProperty("height") == null)
			return false;
		if(getProperty("width") == null)
			return false;
		if(getProperty("fullscreen") == null)
			return false;
		else
			return true;
	}
	public void set(String key, String value)
	{
		setProperty(key,value);
	}
	@Override
	public void load() throws IOException 
	{
		loadFromXML(new FileInputStream(cont.getMountPoint()+".jusbs"+cont.getFileSep()+"settings.xml"));
	}

	@Override
	public void save() throws IOException 
	{
		storeToXML(new FileOutputStream(cont.getMountPoint()+".jusbs"+cont.getFileSep()+"settings.xml"), "Java-USB-Starter Settings File");
	}
}
