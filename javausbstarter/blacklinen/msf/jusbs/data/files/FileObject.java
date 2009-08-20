package blacklinen.msf.jusbs.data.files;

import java.io.File;

import blacklinen.msf.jusbs.data.ListObject;
import blacklinen.msf.jusbs.data.ObjectStoreable;

public class FileObject implements ObjectStoreable, ListObject
{
	protected String name;
	protected String relativePath;
	protected String tag;
	
	public FileObject()
	{
		this.name = new String();
		this.relativePath = new String();
		this.tag = new String("File");
	}
	
	public FileObject(String name, String relativePath, String tag)
	{
		this.name = name;
		this.relativePath = relativePath;
		this.tag = tag;
	}
	
	@Override
	public boolean check(String line) 
	{
		System.out.println(line);
		String[] str = line.split(";");
		if(str.length != 2 && str.length != 3)
			return false;
		else if(str[1].contains("/") || str[1].contains("\\"))
				return false;
			return true;
	}

	@Override
	public void loadfromSaveString(String line) 
	{
		String[] str = line.split(";");
		this.name = new String(str[0]);
		str[1] = str[1].replace("?", File.separator);
		this.relativePath = new String(str[1]);
		if(str.length == 2)
			this.tag = "File";
		else
			this.tag = new String(str[2]);
	}

	@Override
	public String toSaveString() 
	{
		String tempPath= this.relativePath.replace(File.separator, "?");
		return this.name+";"+tempPath+";"+this.tag;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPath()
	{
		return this.relativePath;
	}
	
	public String getTag()
	{
		return this.tag;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public void setPath(String newPath)
	{
		this.relativePath = newPath;
	}
	
	public void setTag(String newTag)
	{
		this.tag = new String(newTag);
	}
}
