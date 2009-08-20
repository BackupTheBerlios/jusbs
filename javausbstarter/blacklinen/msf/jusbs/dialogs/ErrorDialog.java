package blacklinen.msf.jusbs.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import blacklinen.msf.jusbs.Controller;

@SuppressWarnings("serial")
public abstract class ErrorDialog extends JDialog implements ActionListener
{
	protected JLabel message;
	protected JTextArea text;
	protected JButton ok;
	protected JScrollPane scroll;
	
	public ErrorDialog(Controller cont)
	{
		super(cont.getMainWindow());
		this.message = new JLabel("The following error occured in JavaUSBStarter:");
		this.text = new JTextArea(12, 256);
		this.ok = new JButton("OK");
		this.scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.makeGUI();
	} 
	
	protected void makeGUI()
	{
		setTitle("Error");
		setLayout(new GridLayout(3,1));
		add(this.message);
		//this.text.setLineWrap(true);
		this.scroll.setViewportView(this.text);
		add(this.scroll);
		JPanel buttPanel = new JPanel();
		this.ok.addActionListener(this);
		buttPanel.add(this.ok);
		add(buttPanel);
	}
	
	protected void center()
	{
		int height = getToolkit().getScreenSize().height;
		int width = getToolkit().getScreenSize().width;
		setLocation(((width-getWidth())/2),((height-getHeight())/2));
	}
	
	public void showDialog(Exception e)
	{
		this.text.setText(e.getLocalizedMessage()+"\n"+e.getCause());
		this.setSize(500, 200);
		this.center();
		setVisible(true);
	}
}
