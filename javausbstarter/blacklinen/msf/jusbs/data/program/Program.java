/**
 * 
 */
package blacklinen.msf.jusbs.data.program;

//import java.io.File;
import java.io.IOException;

import blacklinen.msf.jusbs.data.ListObject;
import blacklinen.msf.jusbs.data.ObjectStoreable;

/**
 * This class represents an executable file
 * @author Blacklinen
 * @version 0.7
 */
public class Program implements ObjectStoreable, ListObject
{
	private String relativePath;
	private String Name;
	@SuppressWarnings("unused")
	private static int LINUX = 0;
	@SuppressWarnings("unused")
	private static int WINDOWS = 1;
	protected int os;
	protected String tag;
	/**
	 * This constructs an empty Program 
	 */
	public Program()
	{
		relativePath = null;
		Name = null;
		this.tag = new String("Application");
		//program = null;
	}
	/**
	 * 
	 * @param in_Name The Name of the Program
	 * @param in_relativePath The relative path to the executable file 
	 */
	public Program(String in_Name, String in_relativePath)
	{
		Name = new String(in_Name);
		relativePath = new String(in_relativePath);
		this.tag = new String("Application");
	}
	
	public Program(String in_Name, String in_relativePath, String tag)
	{
		Name = new String(in_Name);
		relativePath = new String(in_relativePath);
		this.tag = new String(tag);
	}
	/**
	 * 
	 * 
	 * @param location
	 * @param absolutePath
	 * @return The relative Path
	 */
	public static String relativePath( String location , String absolutePath )
	{
		String relativePath = null;
		if(absolutePath.contains(location))
		{
			relativePath = absolutePath.substring(location.length() , absolutePath.length());
		}
		return relativePath;
	}
	/**
	 * Starts the program
	 * @throws IOException if an IOException occurred during starting
	 */
	public void Start() throws IOException
	{
		Runtime.getRuntime().exec(relativePath);
	}
	/**
	 * Starts the program
	 * 
	 * @param mountPoint The Mountpoint to resolve the relative Path
	 * 
	 * @throws IOException if an IOException occurred during starting
	 */
	public void Start(String mountPoint) throws IOException
	{
		Runtime.getRuntime().exec(mountPoint+relativePath);
		//program.getErrorStream();
		//program.getOutputStream();
	}
	/**
	 * 
	 * @return The Name of the Program
	 */
	public String getName()
	{
		return Name;
	}
	/**
	 *
	 * @return The relative path of the executable file
	 */
	public String getPath()
	{
		return relativePath;
	}
	/**
	 * 
	 * @return The selected operating system for this program
	 */
	public int getOS()
	{
		return this.os;
	}
	/**
	 * Returns the tag of the represented program.
	 * @return The current tag.
	 */
	public String getTag()
	{
		return this.tag;
	}
	/**
	 * Sets the name of the program
	 * @param in_Name The new name of the program
	 */
	public void setName(String in_Name)
	{
		Name = in_Name;
	}
	/**
	 * Sets a new relative path of the executable file
	 * @param in_path The new relative path
	 */
	public void setPath(String in_path)
	{
		relativePath = in_path;
	}
	/**
	 * 
	 * Sets the Operating System for this Programm;
	 * @param os
	 */
	public void setOS(int os)
	{
		if(os < 2)
			this.os = os;
		else
			this.os = 1;
	}
	/**
	 * Set's the tag of represented program;
	 * @param newTag The new tag.
	 */
	public void setTag(String newTag)
	{
		this.tag = new String(newTag);
	}
	
	@Override
	public boolean check(String line) 
	{
		String[] arr = line.split(";");
		if(arr.length < 2)
			return false;
		return true;	
	}
	@Override
	public void loadfromSaveString(String line) 
	{
		String[] arr = line.split(";");
		this.Name = new String(arr[0]);
		this.relativePath = new String(arr[1]);
		if(arr.length == 2)
			this.tag = "Application";
		else
			this.tag = new String(arr[2]);
	}
	@Override
	public String toSaveString() 
	{
		return this.getName()+";"+this.getPath()+";"+this.tag;
	}
}