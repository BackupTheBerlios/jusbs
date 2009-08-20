package blacklinen.msf.jusbs.utils.searchPanel;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import blacklinen.msf.jusbs.data.ObjectStoreable;

public class SearchEngine implements ObjectStoreable 
{
	protected String part1;
	protected String part2;
	protected String name;
	
	public SearchEngine()
	{
		part1 = null;
		part2 = null;
		name = null;
	}
	
	public SearchEngine(String str1,String str2, String str3)
	{
		part1 = str2;
		part2 = str3;
		name = str1;
	}
	
	@Override
	public boolean check(String line) 
	{
		String[] str = line.split(";");
		if(str.length != 2)
			return false;
		if(! str[1].contains("POST"))
			return false;
		try
		{
			new URI(str[1]);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	@Override
	public void loadfromSaveString(String line) 
	{
		System.out.println(line);
		String[] str = line.split(";");
		name = str[0];
		String[] parts = str[1].split("POST");
		part1 = parts[0];
		if(parts.length <2)
			part2 = new String("");
		else
			part2 = parts[1];
	}

	@Override
	public String toSaveString() 
	{
		return name+";"+part1+"POST"+part2;
	}
	
	public void lookup(String forThing) throws URISyntaxException, IOException
	{
		Desktop desk = Desktop.getDesktop();
		forThing = forThing.replace(' ', '+');
		URI url = new URI(part1+forThing+part2);
		desk.browse(url);
	}
	
	public String getName()
	{
		return this.name;
	}
	public String getURL()
	{
		return this.part1+"POST"+this.part2;
	}
}
