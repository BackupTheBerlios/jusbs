package blacklinen.msf.jusbs.data.settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import blacklinen.msf.jusbs.Controller;
import blacklinen.msf.jusbs.data.DataViewer;

@SuppressWarnings("serial")
public class SettingsViewer extends JFrame implements DataViewer, ListSelectionListener
{
	private Controller cont;
	private Settings sett;
	private JList left;
	private String[] list;
	protected SettingsPanel[] panels;
	protected JPanel centerPanel;
	
	public SettingsViewer(Controller controller)
	{
		super();
		this.cont = controller;
		this.sett = cont.getSettings();
		this.left = new JList();
		this.panels = new SettingsPanel[2];
		this.panels[0] = new GeneralPanel(this.sett);
		this.panels[1] = new SearchBarPanel(this.cont.getSearchPanelController(), this);
		this.centerPanel = new JPanel();
		this.list = new String[2];
		this.list[0] = new String("General Settings");
		this.list[1] = new String("Searchbar");
		this.setTitle("Java-USB-Starter Settings");
		this.createGUI();
	}
	
	protected void createGUI()
	{
		this.setLayout(new BorderLayout());
		this.left = new JList();
		this.left.setListData(this.list);
		this.left.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.left.addListSelectionListener(this);		
		this.add(left, BorderLayout.WEST);
		this.createButtons();
		this.centerPanel.add(this.panels[0]);
		this.centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(this.centerPanel, BorderLayout.CENTER);
		setSize(400,400);
	}
	protected void loadSettingsPanel(int index)
	{
		this.centerPanel.setVisible(false);
		System.out.println(index);
		this.centerPanel.removeAll();
		this.centerPanel.add(this.panels[index]);
		this.centerPanel.setVisible(true);
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
		try 
		{
			for(SettingsPanel pan : this.panels)
				pan.setSettings();
			this.sett.save();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dispose();
	}
	public void showDialog()
	{
		this.setLocationByPlatform(true);
		this.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{	
		System.out.println(this.left.getSelectedValue().toString()+" index:"+this.left.getSelectedIndex());
		this.loadSettingsPanel(this.left.getSelectedIndex());
	}
}
