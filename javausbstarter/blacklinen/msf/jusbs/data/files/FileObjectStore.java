package blacklinen.msf.jusbs.data.files;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import blacklinen.msf.jusbs.data.DataController;
import blacklinen.msf.jusbs.data.MainPanelController;
import blacklinen.msf.jusbs.data.ObjectStore;

public class FileObjectStore extends ObjectStore<FileObject> implements DataController
{
	protected MainPanelController main;
	protected BufferedReader reader;

	public FileObjectStore(MainPanelController main)
	{
		super();
		this.main = main;
	}
	@Override
	public void load(String dic) throws IOException 
	{
		this.reader = new BufferedReader(new FileReader(dic+".jusbs"+File.separator+"filelist.txt"));
		String line;
		while((line = this.reader.readLine())!= null)
		{
			if(!line.startsWith("//"))
			{
				FileObject obj = new FileObject();
				if(!obj.check(line))
					throw new IOException("Websitelist is broken");
				obj.loadfromSaveString(line);
				super.objects.add(obj);
			}
		}
		super.objects.trimToSize();
	}
	@Override
	public void add(String[] arr)
	{
		FileObject obj = new FileObject();
		arr[1] = arr[1].replace(File.separatorChar, '?');
		if(obj.check(arr[0]+";"+arr[1]+";"+"File"))
		{
			obj.loadfromSaveString(arr[0]+";"+arr[1]+";"+"File");
			super.add(obj);
		}
		else
			System.out.println("Error this not a valid FileObject");
		
	}
	@Override
	public void exec(int index) throws Exception
	{
		FileObject obj = super.getElement(index);
		System.out.println("Open file:"+obj.getName()+" Path:"+obj.getPath());
		File f = new File(main.getMountPoint()+obj.getPath());
		Desktop desk = Desktop.getDesktop();
		desk.open(f);
	}

}
