package blacklinen.msf.jusbs.data.websites;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

import blacklinen.msf.jusbs.data.DataController;
import blacklinen.msf.jusbs.data.MainPanelController;
import blacklinen.msf.jusbs.data.ObjectStore;

public class WebsiteStore extends ObjectStore<Website> implements DataController
{
	protected BufferedReader reader;
	protected MainPanelController main;
	
	public WebsiteStore(MainPanelController main)
	{ 
		super();
		this.main = main;
	}
	
	@Override
	public void load(String dic) throws IOException 
	{
		this.reader = new BufferedReader(new FileReader(dic+".jusbs"+File.separator+"websitelist.txt"));
		String line;
		while((line = this.reader.readLine())!= null)
		{
			Website web = new Website();
			if(!web.check(line))
				throw new IOException("Websitelist is broken");
			web.loadfromSaveString(line);
			super.objects.add(web);
		}
		super.objects.trimToSize();
	}

	@Override
	public void add(String[] arr)
	{
		Website web = new Website();
		if(!arr[1].contains("//"))
			arr[1] = new String("http://"+arr[1]);
		if(web.check(arr[0]+";"+arr[1]+";"+"Website"))
		{
			web.loadfromSaveString(arr[0]+";"+arr[1]+";"+"Website");
			super.add(web);
		}
		else
			System.out.println("Error");
		
	}

	@Override
	public void exec(int index) throws Exception
	{
		Website web = super.getElement(index);
		System.out.println("Open Website:"+ web.getURL().toString());
		Desktop desk = Desktop.getDesktop();
		desk.browse(new URI(web.getURL()));
	}

}
