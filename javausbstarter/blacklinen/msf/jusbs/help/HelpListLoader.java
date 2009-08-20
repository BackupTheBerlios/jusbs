package blacklinen.msf.jusbs.help;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HelpListLoader 
{
	private ArrayList<String> lines;
	private BufferedReader reader;
	
	public HelpListLoader()
	{
		this.lines = new ArrayList<String>();
	}
	
	public String[] load(String path) throws IOException
	{
		this.reader = new BufferedReader(new FileReader(path));
		String line;
		while(null != (line = this.reader.readLine()))
		{
			this.lines.add(line);
		}
		lines.trimToSize();
		return lines.toArray(new String[0]);
	}
}
