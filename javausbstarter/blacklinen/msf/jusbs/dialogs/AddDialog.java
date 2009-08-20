package blacklinen.msf.jusbs.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import blacklinen.msf.jusbs.Controller;
import blacklinen.msf.jusbs.ProgramFilter;
import blacklinen.msf.jusbs.data.program.Program;
import blacklinen.msf.jusbs.data.visual.datapanels.DataPanel;

@SuppressWarnings("serial")
public class AddDialog extends JDialog implements ActionListener, FocusListener
{
	private Controller cont;
	private JLabel addText;
	private JLabel pathURL;
	private JTextField name;
	private JTextField path;
	private JButton browse;
	private JComboBox type;
	private JButton add;
	
	public AddDialog(Controller cont)
	{
		super(cont.getMainWindow());
		this.cont = cont;
		this.addText = new JLabel("Name:");
		this.pathURL = new JLabel("Path or URL:");
		this.name = new JTextField(15);
		this.path = new JTextField(15);
		this.browse = new JButton("Browse");
		this.type = new JComboBox(new String[]{"Program", "Website", "File"});
		this.add = new JButton("Add Entry");
		createGUI();
	}
	protected void createGUI()
	{
		setTitle("Add Entry");
		setLayout(new GridLayout(5,3));
		add(new JLabel());
		add(new JLabel());
		add(new JLabel());
		add(addText);
		add(name);
		add(new JLabel());
		add(pathURL);
		add(path);
		final JDialog dia = this; 
		browse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser choo = new JFileChooser();
				choo.setCurrentDirectory(new File(cont.getMountPoint()));
				choo.setFileFilter(new ProgramFilter());
				int select = choo.showOpenDialog(dia);
				if(select == JFileChooser.APPROVE_OPTION)
				{
					String relative;
					if((relative = Program.relativePath(cont.getMountPoint(), choo.getSelectedFile().getAbsolutePath())) != null)
					{
						path.setText(relative);
						if(name.getText().trim().length() < 1)
						{
							String pathstr = path.getText();
							int pos1 = pathstr.lastIndexOf(cont.getFileSep())+1;
							int pos2 = pathstr.lastIndexOf(".");
							if(pos1 < 0)
								pos1 = pathstr.length();
							name.setText(pathstr.substring(pos1, pos2));
							if(pathstr.contains(".")&&!pathstr.endsWith("exe"))
								type.setSelectedIndex(2);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(dia, "Java USB Starter was unable to create a relative path", "No relative path", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		this.path.addFocusListener(this);
		add(browse);
		add(new JLabel("Type"));
		add(type);
		add(new JLabel());
		add(new JLabel());
		add.addActionListener(this);
		add(add);
		pack();
		center();
	}
	public void showDialog()
	{
		setVisible(true);
	}
	protected void center()
	{
		int height = getToolkit().getScreenSize().height;
		int width = getToolkit().getScreenSize().width;
		setLocation(((width-getWidth())/2),((height-getHeight())/2));
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(name.getText().trim().length() < 1 || path.getText().trim().length() < 1)
			JOptionPane.showMessageDialog(this, "You have to enter a path and a path or a URL", "Some Informations are missed", JOptionPane.INFORMATION_MESSAGE);
		else
		{
			int select = type.getSelectedIndex();
			System.out.println("Selected:" + select);
			if(name.getText().trim().length() < 2 )
				JOptionPane.showMessageDialog(this, "No Name","Youe have to enter a name for the entry.",JOptionPane.INFORMATION_MESSAGE);
			else if(select == 0)
			{
					cont.getDataController().add(new String[]{name.getText(), path.getText()},DataPanel.PROGRAMS);
			}
			else if(select == 1)
			{
				cont.getDataController().add(new String[]{name.getText(), path.getText()}, DataPanel.LINKS);
			}
			else if(select == 2)
			{
				cont.getDataController().add(new String[]{name.getText(), path.getText()},DataPanel.DATA);
			}
		}
		dispose();
	}
	@Override
	public void focusGained(FocusEvent arg0) 
	{}
	@Override
	public void focusLost(FocusEvent e) 
	{
		if(e.getID() == FocusEvent.FOCUS_LOST)
		{
			if(this.path.getText().contains("//"))
				this.type.setSelectedIndex(1);
			else
				this.type.setSelectedIndex(0);
			System.out.println("Select: "+type.getSelectedIndex());
		}		
	}
}
