package blacklinen.msf.jusbs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import blacklinen.msf.jusbs.dialogs.AddDialog;

/**
 *  This class is the mainwindow of the JavaUSBStarter
 *  
 * @author blacklinen
 *
 */
@SuppressWarnings("serial")
class Viewer extends JFrame
{
	
	public Viewer(Controller controller)
	{
		super();
		final Controller cont = controller;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Java-USB-Starter runs on: "+System.getProperty("os.name")+" "+System.getProperty("os.version"));
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem add = new JMenuItem("Add Entry...");
		add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				AddDialog dia = new AddDialog(cont);
				dia.showDialog();
			}
		});
		menu.add(add);
		JMenuItem remove =new JMenuItem("Remove Entry");
		menu.add(remove);
		menu.addSeparator();
		JMenuItem settings = new JMenuItem("Settings");
		settings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				cont.showSettingsDialog();
			}
		});
		menu.add(settings);
		menu.addSeparator();
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
			}
		});
		menu.add(exit);
		menubar.add(menu);
		JMenu help = new JMenu("Help");
		JMenuItem version = new JMenuItem("Version");
		version.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				versionDialog();
			}
		});
		help.add(version);
		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				cont.showHelp();
			}
		});
		help.add(helpItem);
		menubar.add(help);		
		setJMenuBar(menubar);
	}
	
	public void versionDialog()
	{
		final JDialog dia = new JDialog(this);
		dia.setLayout(new BoxLayout(dia.getContentPane(), BoxLayout.Y_AXIS));
		dia.setTitle("Version");
		dia.setLocationRelativeTo(this);
		JLabel vers = new JLabel("Version: 0.8");
		dia.add(vers);
		JLabel built = new JLabel("Built: 0032");
		dia.add(built);
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dia.dispose();
			}
		});
		dia.add(ok);
		dia.setSize(170,120);
		dia.setVisible(true);
	}
}