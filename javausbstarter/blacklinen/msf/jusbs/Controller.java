/**
 * 
 */
package blacklinen.msf.jusbs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import blacklinen.msf.jusbs.data.MainPanelController;
import blacklinen.msf.jusbs.data.settings.Settings;
import blacklinen.msf.jusbs.data.settings.SettingsViewer;
import blacklinen.msf.jusbs.dialogs.MountDialog;
import blacklinen.msf.jusbs.help.HelpController;
import blacklinen.msf.jusbs.utils.searchPanel.SearchPanelController;


/**
 * This class is the "main" class in JavaUSBStarter, she loads the settings and initialize 
 * all other components of the JavaUSBStarter
 * 
 * @author Blacklinen
 *
 */
public class Controller 
{
	public static final int LINUX_MODE = 0;
	public static final int WINDOWS_MODE = 1;
	private String mountPoint; // The Dictonary in which the JavaUSBStarter runs
	private String fileSep;// The current FileSeperator
	private Settings sett; // The Settinscontroller
	private MainPanelController panelController; // The Mainpanelcontroller
	private Viewer main; // The MainWindow
	private SettingsViewer settview; // The Settingswindow
	private SearchPanelController search; // The controller for the SearchPanel
	private JScrollPane mainPanel; // The Scrollpane for MainPanel
	private Dimension maxDim; 
	private HelpController help; // The HelpController
	
	/**
	 * Constructs:
	 * this.filesep with: this.filesep = System.getProperty("file.separator");.
	 * this.mountPoint with = new String();.
	 * this.sett with: this.sett = new Settings(this);.
	 * this.panelController with = this.panelController = new MainPanelController(this);.
	 * this.main with: this.main = new Viewer(this);.
	 * this.search with: this.search = new SearchPanelController(this);.
	 * this.mainPanel with: this.mainPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);.
	 * this.maxDim with: this.maxDim = Toolkit.getDefaultToolkit().getScreenSize();.
	 */
	public Controller()
	{
		this.fileSep = System.getProperty("file.separator");
		this.mountPoint = new String();
		this.sett = new Settings(this);
		this.panelController = new MainPanelController(this);
		this.main = new Viewer(this);
		this.search = new SearchPanelController(this);
		this.mainPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.maxDim = Toolkit.getDefaultToolkit().getScreenSize();
	}
	/**
	 * Trys to get out the location of the JavaUSBStarter
	 * If mode == Controller.LINUX_MODE a dialog will be present where the user can enter
	 * the mountpoint of the USBMemorey-Stick.
	 * If mode == Controller.WINDOWS_MODE
	 * The JavaUSBStarter will create a file in his folder whith new File("temp.tmp")
	 * and use his path as loaction.
	 * @param mode The mode in which it should start.
	 */
	public void start(int mode)
	{
		if(mode == Controller.LINUX_MODE)
		{
				MountDialog dia = new MountDialog(this);
				dia.showDialog();
		}
		else if(mode == Controller.WINDOWS_MODE)
		{
			File temp = new File("temp.tmp");
			try
			{
				temp.createNewFile();
				temp.deleteOnExit();
				System.out.println(temp.getCanonicalPath());
				this.mountPoint = new String(temp.getCanonicalPath().substring(0, temp.getCanonicalPath().length() - temp.getName().length()));
			}
			catch(IOException ioe)
			{
				JOptionPane.showMessageDialog(main, "Java-UBS-Starter can not write in his runlocation. \nJava-USB-Starter will exit", "Can not write", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			loadSettings();
		}
		System.out.println("Path: "+this.mountPoint);
	}
	/**
	 *  Loads the settingsfile.
	 */
	public void loadSettings()
	{
		try
		{
				this.sett.load();
				this.search.load();
		}
		catch(FileNotFoundException fnfe)
		{
			File dir = new File(this.mountPoint+".JavaUSBStarter");
			if(!dir.exists())
				dir.mkdir();
			else
			{
				this.sett.reset();
				try
				{
					this.sett.save();
					
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
					System.out.println(ioe.getMessage());
				}
			}
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(main, "Java USB Starter can not load the settings \nThat can caused by a incorrect mountpoint \n Details:"+ioe.getMessage(), "Settings", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		if(!(this.sett.check()))
		{
			this.sett.reset();
			System.out.println("Settingsfile is broken start with defaults");
		}
		else
		{
			if(this.sett.get("fullscreen").equals("false"))
			{
				int height = Integer.parseInt(this.sett.getProperty("height"));
				int width = Integer.parseInt(this.sett.getProperty("width"));
				this.main.setSize(width , height);
			}
			else
			{
				this.main.setExtendedState(Frame.MAXIMIZED_BOTH);
				this.main.setSize(this.maxDim);
			}
		}
		this.main.add(search.getGUI(),BorderLayout.SOUTH);
		loadPrograms();
	}
	/**
	 * Initalize the MainPanelController this.panelController.
	 */
	public void loadPrograms()
	{
		try 
		{
			this.panelController.load();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.mainPanel.setViewportView(this.panelController.getGUI());
		this.main.add(this.mainPanel,BorderLayout.CENTER);
		this.main.setVisible(true);
	}
	/**
	 * Constructs this.help as new HelpController,
	 * and shows the help.
	 */
	public void showHelp()
	{
		this.help = new HelpController(this.getMountPoint());
		this.help.showHelp();
	}
	/**
	 * Constructs this.settview as new SettingsViewer,
	 * and shows the help.
	 */
	public void showSettingsDialog()
	{
		this.settview = new SettingsViewer(this);
		this.settview.showDialog();
	}
	/**
	 * Returns the this.mountPoint.
	 * @return The current mountpoint.
	 */
	public String getMountPoint()
	{
		return this.mountPoint;
	}
	
	public void setMountPoint(String str)
	{
		this.mountPoint = str;
		if(this.mountPoint.charAt(this.mountPoint.length()-1) != '/')
		{
			this.mountPoint = new String(this.mountPoint+"/");
		}
		System.out.println("Path:"+str);
	}
	public MainPanelController getDataController()
	{
		return this.panelController;
	}
	
	public String getFileSep()
	{
		return this.fileSep;
	}
	
	public Viewer getMainWindow()
	{
		return this.main;
	}
	
	public Settings getSettings()
	{
		return this.sett;
	}
	
	public SearchPanelController getSearchPanelController()
	{
		return this.search;
	}
}