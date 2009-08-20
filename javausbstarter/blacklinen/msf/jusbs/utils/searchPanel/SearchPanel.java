package blacklinen.msf.jusbs.utils.searchPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener
{
	protected SearchPanelController cont;
	protected JTextField text;
	protected JComboBox engines;
	protected JLabel part1;
	
	public SearchPanel(SearchPanelController cont)
	{
		this.cont = cont;
		this.text = new JTextField(15);
		this.engines = new JComboBox();
		this.part1 = new JLabel("Search with:");
		this.makeGUI();
	}
	protected void makeGUI()
	{
		this.setLayout(new FlowLayout());
		this.text.addActionListener(this);
		this.add(text);
		this.add(part1);
		this.add(engines);
	}
	public void setOptions(String[] options)
	{
		for(String str : options)
			this.engines.addItem(str);
		this.engines.setSelectedItem(0);
		//this.engines.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{	
		this.cont.open(this.engines.getSelectedIndex(),this.text.getText());
	}
}
