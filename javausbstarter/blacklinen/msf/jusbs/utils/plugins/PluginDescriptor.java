package blacklinen.msf.jusbs.utils.plugins;

import blacklinen.msf.jusbs.data.ObjectStoreable;

public class PluginDescriptor implements ObjectStoreable
{
	protected String name;
	protected String classFile;
	protected String enabled;
	
	public PluginDescriptor()
	{
		this.name = new String();
		this.classFile = new String();
		this.enabled = new String();
	}
	
	public PluginDescriptor(String name, String classFile, boolean enabled)
	{
		this.name = name;
		this.classFile = classFile;
		this.enabled = new String(""+enabled);
	}
	
	@Override
	public boolean check(String line)
	{
		if(!line.contains(";"))
			return false;
		else if((line.split(";").length) != 3)
			return false;
		else if(!line.split(";")[2].equalsIgnoreCase("true") || !line.split(";")[2].equalsIgnoreCase("false"))
			return false;
		return true;
	}

	@Override
	public void loadfromSaveString(String line)
	{
		String[] str = line.split(";");
		str[0] = this.name;
		str[1] = this.classFile;
		str[2] = this.enabled;
	}

	@Override
	public String toSaveString()
	{
		return this.name+";"+this.classFile+";"+this.enabled;
	}

}
