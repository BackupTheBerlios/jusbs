package blacklinen.msf.jusbs.help;

import java.io.IOException;

public class HelpController 
{
	private HelpViewer main;
	private HelpListLoader loader;
	private HelpComponent[] helpContent;
	private String mountPoint;
	private String[] helpTopics;
	
	public HelpController(String location)
	{
		this.mountPoint = location;
		this.loader = new HelpListLoader();
		this.load();
		this.loader = new HelpListLoader();
		this.main = new HelpViewer(this);
		this.main.setTitle("Java-USB-Starter Help");
		this.makeHelpComponents();
	}
	public void showHelp()
	{
		this.main.setLocationRelativeTo(null);
		this.main.showHelp();
	}
	
	protected void load()
	{
		try
		{
			this.helpTopics = this.loader.load(this.mountPoint+System.getProperty("file.separator")+".jusbs"+System.getProperty("file.separator")+"hlist.txt");
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
	}
	
	protected void makeHelpComponents()
	{
		this.main.setTopics(this.helpTopics);
		this.helpContent = new HelpComponent[this.helpTopics.length];
		int num = 0;
		for(String str : this.helpTopics)
		{
			this.helpContent[num] = new HelpComponent();
			str = str.replaceAll(" ", "").toLowerCase();
			this.helpContent[num].load(this.mountPoint+System.getProperty("file.separator")+".jusbs"+System.getProperty("file.separator")+str+".html");
			num++;
		}
		this.main.setHelpComponents(this.helpContent);
	}
	public void dispose()
	{
		this.helpContent = null;
		this.helpTopics = null;
		this.loader = null;
		//this.main.dispose();
		this.main = null;
		this.mountPoint = null;
	}
}
