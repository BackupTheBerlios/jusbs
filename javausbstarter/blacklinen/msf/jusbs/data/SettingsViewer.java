package blacklinen.msf.jusbs.data;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import blacklinen.msf.jusbs.Controller;
import blacklinen.msf.jusbs.data.settings.Settings;

@SuppressWarnings("serial")
public class SettingsViewer extends JFrame implements DataViewer
{
	private Controller cont;
	private Settings sett;
	private JList left;
	private JPanel general;
	private String[] list;
	private NumberFormat form;
	private JFormattedTextField heightField;
	private JFormattedTextField widthField;
	private JCheckBox fullBox;
	
	public SettingsViewer(Controller controller)
	{
		super();
		this.cont = controller;
		this.sett = cont.getSettings();
		this.left = new JList();
		this.general = new JPanel();
		this.list = new String[2];
		this.list[0] = new String("General Settings");
		this.list[1] = new String("Searchmacines");
		this.setLayout(new BorderLayout());
		this.setTitle("Java-USB-Starter Settings");
	}
	
	protected void createGUI()
	{
		this.left.setListData(list);
		add(left, BorderLayout.WEST);
		this.createGeneralSettings();
		this.createButtons();
		add(this.general, BorderLayout.CENTER);
		setSize(600,400);
		
	}
	protected void createGeneralSettings()
	{
		this.general.setLayout(new GridLayout(9,2));
		JLabel height = new JLabel("Screenheight: ");
		this.form = NumberFormat.getNumberInstance();
		form.setMaximumFractionDigits(0);
		form.setGroupingUsed(false);
		this.heightField = new JFormattedTextField(form);
		this.heightField.setText((String) sett.get("height"));
		JLabel width = new JLabel("Screenwidth: ");
		this.widthField = new JFormattedTextField(form);
		this.widthField.setText((String) sett.get("width"));
		JLabel fullscreen = new JLabel("Fullscreen");
		this.fullBox = new JCheckBox();
		if(this.sett.get("fullscreen").equals("false"))
			this.fullBox.setSelected(false);
		else
			this.fullBox.setSelected(true);
		this.general.add(width);
		this.general.add(widthField);
		this.general.add(new JPanel());
		this.general.add(new JPanel());
		this.general.add(height);
		this.general.add(heightField);
		this.general.add(new JPanel());
		this.general.add(new JPanel());
		this.general.add(fullscreen);
		this.general.add(fullBox);
		this.general.add(new JLabel("Changes will taken effect\n at next startup"));
		this.general.add(new JLabel(""));
	}
	protected void createButtons()
	{
		final SettingsViewer frame = this;
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				frame.save();
			}
		});
		JButton chancel = new JButton("Chancel");
		chancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				frame.dispose();
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(ok);
		buttonPanel.add(chancel);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	public void save()
	{
		this.sett.set("width",this.widthField.getText());
		this.sett.set("height", this.heightField.getText());
		if(this.fullBox.isSelected())
			this.sett.set("fullscreen", "true");
		else
			this.sett.set("fullscreen", "false");
		try
		{
			this.sett.save();
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
		this.dispose();
	}
	public void showDialog()
	{
		this.setLocationByPlatform(true);
		this.createGUI();
		this.setVisible(true);
	}
}
