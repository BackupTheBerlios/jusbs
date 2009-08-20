package blacklinen.msf.jusbs.help;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class HelpViewer extends JFrame implements ListSelectionListener 
{
	private JScrollPane scroll;
	private JList topics;
	private HelpComponent[] helps;
	
	public HelpViewer()
	{
		super();
		setLayout(new BorderLayout());
		scroll = new JScrollPane(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		topics = new JList();
		this.createGUI();
		
	}
	
	public HelpViewer(HelpController cont)
	{
		super();
		setLayout(new BorderLayout());
		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		topics = new JList();
		this.createGUI();
	}
	protected void createGUI()
	{
		topics.setSelectedIndex(0);
		add(scroll, BorderLayout.CENTER);
		add(topics, BorderLayout.WEST);
		this.prepare();
	}
	
	public void prepare()
	{
		topics.addListSelectionListener(this);
	}
	
	public void setTopics(String[] topics)
	{
		this.topics.setListData(topics);
	}
	
	public void showHelp()
	{
		setSize(1024,768);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void setHelpComponents(HelpComponent[] help)
	{
		this.helps = help;
		this.scroll.setViewportView(this.helps[0]);
		this.scroll.updateUI();
		//add(this.helps[0], BorderLayout.NORTH);
	}

	public void valueChanged(ListSelectionEvent arg0) 
	{	
		this.scroll.setViewportView(this.helps[this.topics.getSelectedIndex()]);
	}
	
}
