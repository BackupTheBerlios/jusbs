package blacklinen.msf.jusbs.data.visual.datapanels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel 
{
	protected int number;
	
	public MainPanel()
	{
		super();
		number = 1;
		makeGUI();
	}
	protected void makeGUI()
	{
		super.setVisible(false);
		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		super.setVisible(true);
	}
	public void rebuilt()
	{
		super.removeAll();
		number = 1;
	}
	public void add(DataPanel data)
	{
		data.setNumber(number);
		super.add(data);
		number++;
	}
}
