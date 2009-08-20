package blacklinen.msf.jusbs.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import blacklinen.msf.jusbs.Controller;
import blacklinen.msf.jusbs.data.files.FileObject;
import blacklinen.msf.jusbs.data.files.FileObjectStore;
import blacklinen.msf.jusbs.data.program.Program;
import blacklinen.msf.jusbs.data.program.ProgramStore;
import blacklinen.msf.jusbs.data.visual.datapanels.DataPanel;
import blacklinen.msf.jusbs.data.visual.datapanels.FileObjectPanel;
import blacklinen.msf.jusbs.data.visual.datapanels.MainPanel;
import blacklinen.msf.jusbs.data.visual.datapanels.ProgramPanel;
import blacklinen.msf.jusbs.data.visual.datapanels.WebsitePanel;
import blacklinen.msf.jusbs.data.websites.Website;
import blacklinen.msf.jusbs.data.websites.WebsiteStore;
import blacklinen.msf.jusbs.dialogs.CloseDialog;
import blacklinen.msf.jusbs.dialogs.ErrorDialog;
import blacklinen.msf.jusbs.dialogs.ProgramErrorDialog;

public class MainPanelController
{
	protected Controller cont;
	protected ProgramStore progs;
	protected WebsiteStore websites;
	protected FileObjectStore files;
	protected MainPanel mainPanel;
	protected ErrorDialog ed;
	
	public MainPanelController(Controller cont)
	{
		this.cont = cont;
		this.progs = new ProgramStore(this);
		this.websites = new WebsiteStore(this);
		this.files = new FileObjectStore(this);
	}
	public void load() throws IOException
	{
		this.progs.load(this.getMountPoint());
		this.websites.load(this.getMountPoint());
		this.files.load(this.getMountPoint());
		this.makeMainPanel();
	}
	protected void makeMainPanel()
	{
		this.mainPanel = new MainPanel();
		this.addPrograms();
		this.addWebsites();
		this.addFiles();
	}
	protected void addPrograms()
	{
		ArrayList<Program> arr = this.progs.getElements();
		int num = 0;
		for(Program prog : arr)
		{
			this.mainPanel.add(new ProgramPanel(prog.getName(),this.getMountPoint()+prog.getPath(), num,this));
			num++;
		}
	}
	protected void addWebsites()
	{
		ArrayList<Website> arr= this.websites.getElements();
		int num = 0;
		for(Website web : arr)
		{
			this.mainPanel.add(new WebsitePanel(web.getName(), num,this));
			num++;
		}
	}
	
	protected void addFiles()
	{
		ArrayList<FileObject> arr = this.files.getElements();
		int num = 0;
		for(FileObject obj : arr)
		{
			this.mainPanel.add(new FileObjectPanel(obj.getName(), num, this));
			num++;
		}
	}
	
	public MainPanel getGUI()
	{
		return this.mainPanel;
	}
	
	public void start(int index) 
	{
		try 
		{
			this.progs.exec(index);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			this.ed = new ProgramErrorDialog(this.cont);
			ed.showDialog(e);
		}
	}
	
	public void openWebsite(int index)
	{
		try
		{
			this.websites.exec(index);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void openData(int index)
	{
		try
		{
			this.files.exec(index);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void open(int index,int mode)
	{
		if(mode == DataPanel.PROGRAMS)
			this.start(index);
		else if(mode == DataPanel.LINKS) 
			this.openWebsite(index);
		else if(mode == DataPanel.DATA)
			this.openData(index);
	}
	public void add(String[] str,int mode)
	{
		try
		{
			if(mode == DataPanel.PROGRAMS)
				this.addProgram(str);
			else if(mode == DataPanel.LINKS)
				this.addLink(str);
			else if(mode == DataPanel.DATA)
				this.addData(str);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			ed = new CloseDialog(this.cont);
			ed.showDialog(ioe);
		}
		this.rebuilt();
	}
	
	protected void addProgram(String[] arr) throws IOException
	{
		this.progs.add(arr);
		this.progs.write(cont.getMountPoint()+".jusbs"+File.separator+"programlist.txt");
	}
	
	protected void addData(String[] arr) throws IOException
	{
		this.files.add(arr);
	}
	
	protected void addLink(String[] arr) throws IOException
	{
		this.websites.add(arr);
	}
	
	public void remove(int index, int mode)
	{
		try
		{
			if(mode == DataPanel.PROGRAMS)
				this.removeProgram(index);
			else if(mode == DataPanel.LINKS)
				this.removeWebsite(index);
			else if(mode == DataPanel.DATA)
				this.removeData(index);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			ed = new CloseDialog(this.cont);
			ed.showDialog(ioe);
		}
		this.rebuilt();
	}
	
	protected void removeProgram(int index) throws IOException 
	{
		this.progs.remove(index);
		this.progs.write(cont.getMountPoint()+".jusbs"+File.separator+"programlist.txt");
	}
	
	protected void removeWebsite(int index) throws IOException 
	{
		this.websites.remove(index);
		this.websites.write(cont.getMountPoint()+".jusbs"+File.separator+"websitelist.txt");
	}
	
	protected void removeData(int index) throws IOException
	{
		this.files.remove(index);
		this.files.write(cont.getMountPoint()+".jusbs"+File.separator+"filelist.txt");
	}
	
	public String getMountPoint() 
	{
		return cont.getMountPoint();
	}
	
	public void rebuilt()
	{
		this.mainPanel.setVisible(false);
		this.mainPanel.rebuilt();
		this.addPrograms();
		this.addWebsites();
		this.addFiles();
		this.mainPanel.setVisible(true);
	}
	/*public void showOnly(int... num)
	{
		
	}*/
}