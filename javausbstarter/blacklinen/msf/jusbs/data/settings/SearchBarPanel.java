package blacklinen.msf.jusbs.data.settings;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;

import blacklinen.msf.jusbs.dialogs.AddSearchEngineDialog;
import blacklinen.msf.jusbs.utils.searchPanel.SearchEngine;
import blacklinen.msf.jusbs.utils.searchPanel.SearchPanelController;

@SuppressWarnings("serial")
public class SearchBarPanel extends SettingsPanel 
{
	protected SearchPanelController store;
	protected SettingsViewer view;
	protected AddSearchEngineDialog dia; 
	protected JLabel titel;
	protected JTable table;
	protected JPanel buttonPanel;
	protected JButton buttAdd;
	protected JButton buttRemove;
	
	public SearchBarPanel(SearchPanelController store, SettingsViewer parent)
	{
		super();
		this.view = parent;
		this.store = store;
		this.titel = new JLabel("Searchengines:");
		this.buttonPanel = new JPanel();
		this.buttAdd = new JButton("Add a searchengine");
		this.buttRemove = new JButton("Remove");
		this.dia = new AddSearchEngineDialog(this, this.view);
		this.makeGUI();
	}
	
	protected void makeGUI()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(this.titel);
		this.add(new JPanel());
		this.makeTable();
		this.add(this.table.getTableHeader());
		this.add(this.table);
		this.makeButtons();
		this.add(this.buttonPanel);
	}
	protected void makeTable()
	{
		ArrayList<SearchEngine> enges = this.store.getElements();
		enges.trimToSize();
		String[][] macines = new String[enges.size()][2];
		int num = 0;
		while(num < enges.size() && num < macines.length)
		{
			SearchEngine eng = enges.get(num);
			macines[num][0] = new String(eng.getName());
			macines[num][1] = new String(eng.getURL());
			num++;
			System.out.println("num "+num);
		}
		String[] header = new String[]{"Name","URL"}; 
		this.table = new JTable(macines, header);
		this.table.getTableHeader().setReorderingAllowed( false );
		this.table.getTableHeader().setResizingAllowed( false );
		this.table.setSelectionMode(JList.HORIZONTAL_WRAP);
		this.table.setEnabled(true);
	}
	
	protected void makeButtons()
	{
		this.buttonPanel.setLayout(new FlowLayout());
		this.buttAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dia.showDialog();
			}
		});
		this.buttRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.out.println("Selected Row: "+table.getSelectedRow());
				if(table.getSelectedRow() > -1)
				{
					store.remove(table.getSelectedRow());
					setVisible(false);
					removeAll();
					makeGUI();
					setVisible(true);
				}
			}
		});
		this.buttonPanel.add(this.buttAdd);
		this.buttonPanel.add(this.buttRemove);
	}
	@Override
	public void setSettings() 
	{
		this.store.store();
	}

	public SettingsViewer getMainWindow()
	{
		return this.view;
	}
	
	public void add(String name, String url)
	{
		SearchEngine eng = new SearchEngine();
		if(eng.check(name+";"+url))
		{
			eng.loadfromSaveString(name+";"+url);
			this.store.add(eng);
			setVisible(false);
			removeAll();
			makeGUI();
			setVisible(true);
		}
		else
		{
			//dia.showDialog();
			System.out.println("Oh somthink is wrong:"+name+" "+ url);
		}
	}
}
