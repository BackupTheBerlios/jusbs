package blacklinen.msf.jusbs.utils.searchPanel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JPanel;

import blacklinen.msf.jusbs.Controller;

public class SearchPanelController 
{
	protected Controller cont;
	protected SearchEngineStore sto;
	protected SearchPanel panel;
	
	public SearchPanelController(Controller cont)
	{
		this.cont = cont;
		this.sto = new SearchEngineStore();
		this.panel = new SearchPanel(this);
	}
	
	public void load() throws IOException
	{
		this.sto.load(cont.getMountPoint()+".jusbs"+File.separator+"searches.txt");
		this.panel.setOptions(this.sto.getNames());
	}
	
	protected void write() throws IOException
	{
		this.sto.write(cont.getMountPoint()+".jusbs"+File.separator+"searches.txt");
	}
	
	public void store()
	{
		try
		{
		this.write();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	protected void add(String name,String line)
	{
		String[] str = line.split("SEARCH");
		SearchEngine eng = new SearchEngine(name,str[0],str[1]);
		this.sto.add(eng);
	}
	public void add(SearchEngine eng)
	{
		this.sto.add(eng);
	}
	
	public void remove(int index)
	{
		this.sto.remove(index);
	}
	
	public JPanel getGUI()
	{
		return panel;
	}
	
	public void open(int index,String str)
	{
		SearchEngine eng = this.sto.getElement(index);
		try 
		{
			eng.lookup(str);
		} 
		catch (URISyntaxException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<SearchEngine> getElements()
	{
		return this.sto.getElements();
	}
}
