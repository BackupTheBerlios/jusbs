package blacklinen.msf.jusbs.data.settings;

import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GeneralPanel extends SettingsPanel
{
	protected JLabel titel;
	protected JLabel screenHeight;
	protected JLabel screenWidth;
	protected JLabel fullscreen;
	protected JCheckBox full;
	protected JFormattedTextField heightField;
	protected JFormattedTextField widthField;
	protected NumberFormat form;
	protected Settings sett;
	
	
	public GeneralPanel(Settings sett)
	{
		super();
		this.sett = sett;
		this.titel = new JLabel("General Settings");
		this.screenHeight = new JLabel("Screenheight:");
		this.screenWidth = new JLabel("Screenwidth:");
		this.fullscreen = new JLabel("Fullscreen:");
		this.full = new JCheckBox();
		this.heightField = new JFormattedTextField(form);
		this.widthField = new JFormattedTextField(form);
		this.form = NumberFormat.getNumberInstance();
		this.makeGUI();
	}
	
	protected void makeGUI()
	{
		this.setLayout(new GridLayout(6,2));
		this.form.setMaximumFractionDigits(0);
		this.form.setGroupingUsed(false);
		this.widthField.setText((String) sett.get("width"));
		this.heightField.setText((String) sett.get("height"));
		if(this.sett.get("fullscreen").equals("false"))
			this.full.setSelected(false);
		else
			this.full.setSelected(true);
		this.add(this.titel);
		this.add(new JPanel());
		this.add(this.screenWidth);
		this.add(this.widthField);
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(this.screenHeight);
		this.add(this.heightField);
		this.add(new JPanel());
		this.add(new JPanel());
		this.add(this.fullscreen);
		this.add(this.full);
	}
	
	public void setSettings()
	{
		this.sett.set("height", this.heightField.getText().trim());
		this.sett.set("width", this.widthField.getText().trim());
		this.sett.set("fullscreen", this.full.isSelected()+"");
		System.out.println(this.full.isSelected()+"");
	}
}
