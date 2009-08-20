package blacklinen.msf.jusbs.data.websites;

import java.net.MalformedURLException;
import java.net.URL;

import blacklinen.msf.jusbs.data.ObjectStoreable;

public class Website implements ObjectStoreable {

	protected String url;
	protected String name;
	protected String tag;
	
	public Website()
	{
		this.name = new String();
		this.url = null;
		this.tag = new String("Website");
	}
	public Website(String url, String name, String newTag)
	{
		this.url = new String(url);
		this.name = new String(name);
		this.tag = new String(newTag);
	}
	@Override
	public boolean check(String line) 
	{
		String[] data = line.split(";");
		if(data.length < 2)
			return false;
		try 
		{
			new URL(data[1]);
		} 
		catch (MalformedURLException e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public void loadfromSaveString(String line) 
	{
		String[] data = line.split(";");
		this.name = data[0];
		this.url = new String(data[1]);
		if(data.length == 2)
			this.tag = "Website";
		else
			this.tag = new String(data[2]);
	}

	@Override
	public String toSaveString() 
	{
		return this.name+";"+this.url+";"+this.tag;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public String getURL()
	{
		return this.url;
	}

	public String getTag()
	{
		return this.tag;
	}
	
	public void setName(String newName)
	{
		this.name = new String(newName);
	}
	
	public void setURL(String newURL)
	{
		this.url = new String(newURL);
	}
	
	public void setTag(String newTag)
	{
		this.tag = new String(newTag);
	}
}
