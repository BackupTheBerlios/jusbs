package blacklinen.msf.jusbs.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import blacklinen.msf.jusbs.data.settings.SearchBarPanel;

@SuppressWarnings("serial")
public class AddSearchEngineDialog extends JDialog implements ActionListener
{
	protected SearchBarPanel panel;
	private JLabel addText;
	private JLabel pathURL;
	private JTextField name;
	private JTextField path;
	private JComboBox type;
	private JButton add;
	
	public AddSearchEngineDialog(SearchBarPanel panel, JFrame main)
	{
		super(main);
		this.panel = panel;
		addText = new JLabel("Name:");
		pathURL = new JLabel("URL:");
		name = new JTextField(15);
		path = new JTextField(15);
		type = new JComboBox(new String[]{"Website"});
		add = new JButton("Add SearchEngine");
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
		add(new JLabel("Enter the name here !"));
		add(pathURL);
		this.path.setToolTipText("Search in your internet browser for the word 'POST' and enter the URL here");
		add(path);
		add(new JLabel("Serach for the word 'POST'"));
		add(new JLabel("Type"));
		this.type.setEnabled(false);
		add(type);
		add(new JLabel("and paste the URL here !"));
		add(new JLabel());
		this.add.addActionListener(this);
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
			this.panel.add(this.name.getText().trim(), this.path.getText().trim());
		}
		dispose();
	}
}
