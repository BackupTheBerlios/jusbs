package blacklinen.msf.jusbs.data.program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import blacklinen.msf.jusbs.data.DataController;
import blacklinen.msf.jusbs.data.MainPanelController;
import blacklinen.msf.jusbs.data.ObjectStore;

public class ProgramStore extends ObjectStore<Program> implements DataController
{
	protected BufferedReader reader;
	protected MainPanelController main;
	
	public ProgramStore(MainPanelController main)
	{
		super();
		this.main = main;
	}
	@Override
	public void load(String dic) throws IOException 
	{
		reader = new BufferedReader(new FileReader(dic+".jusbs"+File.separator+"programlist.txt"));
		String line;
		while((line = reader.readLine())!= null)
		{
			Program prog = new Program();
			if(!prog.check(line))
				throw new IOException("Programlist is broken");
			prog.loadfromSaveString(line);
			super.objects.add(prog);
		}
		super.objects.trimToSize();
	}
	
	public void exec(int index) throws IOException
	{
		super.objects.get(index).Start(main.getMountPoint());
	}
	@Override
	public void add(String[] arr)
	{
		Program prog = new Program();
		if(prog.check(arr[0]+";"+arr[1]+";"+arr[2]))
		{
			prog.loadfromSaveString(arr[0]+";"+arr[1]+";"+arr[2]);
			super.add(prog);
		}
	}
	
}
