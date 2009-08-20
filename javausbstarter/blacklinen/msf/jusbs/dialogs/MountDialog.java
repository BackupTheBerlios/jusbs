package blacklinen.msf.jusbs.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
//import javax.swing.JOptionPane;

import blacklinen.msf.jusbs.Controller;

@SuppressWarnings("serial")
public class MountDialog extends JDialog implements ActionListener
{
	private Controller parent;
	private JTextField path;
	private JButton ok;
	
	public MountDialog(Controller cont)
	{
		super(cont.getMainWindow());
		this.parent = cont;
		this.ok = new JButton("OK");
		this.path = new JTextField(15);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.createGUI();
	}
	
	protected void createGUI()
	{
		setLayout(new GridLayout(3,1));
		this.ok.addActionListener(this);
		add(new JLabel("Please type in the mountpoint of the USB-Stick."));
		add(path);
		add(this.ok);
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
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		dispose();
		parent.setMountPoint(path.getText());
		parent.loadSettings();
	}
}
