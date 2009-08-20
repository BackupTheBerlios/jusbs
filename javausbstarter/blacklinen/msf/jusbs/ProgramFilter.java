package blacklinen.msf.jusbs;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * This class check weather the selected data is an executable file.  
 * @version 0.5
 * @author Blacklinen
 */
public class ProgramFilter extends FileFilter
{
	/**
	 * @param The selected file
	 * @return Weather is the selected file an executable file.
	 */
	public boolean accept(File f)
	{
		if(f.isDirectory())
			return true;
		if(f.getName().toLowerCase().endsWith(".exe"))
			return true;
		/*if(f.getName().toLowerCase().endsWith(".jar"))
			return true;*/
		else if(System.getProperty("os.name").toLowerCase().contains("linux"))
			if(!f.getName().contains("."))
				return true;
		return false;
	}
	/**
	 * @return A description of this filter.
	 */
	public String getDescription()
	{
		return "Executable Files (.exe)";
	}
}