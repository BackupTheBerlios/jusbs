package blacklinen.msf.jusbs.data.visual.datapanels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import blacklinen.msf.jusbs.data.MainPanelController;

@SuppressWarnings("serial")
public abstract class DataPanel extends JPanel implements ActionListener 
{
	public static final int PROGRAMS = 0;
	public static final int LINKS = 1;
	public static final int DATA = 2;
	
	/**
	 *  The mode of the viewed Data.
	 */
	protected int mode;
	/**
	 *  The index of the panel, to resolved in the MainPanelController.
	 */
	protected int index; 
	/**
	 *  The displayed number.
	 */
	protected int displayNumber;
	/**
	 * The button which is placed in the middle of the Panel.
	 */
	protected JButton butt;
	/**
	 * The JLabel which displays the name of the viewed data.
	 */
	protected JLabel name;
	/**
	 * The JLabel which displays ths.displayNumber.
	 */
	protected JLabel number;
	/**
	 * A JCombobox where the user can selected tags for the displayed data.
	 */
	protected JComboBox tags;
	/**
	 * The pointer to the MainPanelController.
	 */
	protected MainPanelController cont;
	/**
	 * The tooltip of the DataPanel.
	 */
	protected String toolTip;
	
	/**
	 * This construtor creates an new DataPanel.
	 * He constructs all members, and call the this.makeGUI() methode. 
	 * 
	 * @param name The displayed name for this panel.
	 * @param buttonAction The which where displayed with this.name on this.butt.
	 * @param toolTip The tooltip string.
	 * @param index The index.
	 * @param mode The mode of the represented data.
	 * @param cont The link to the MainPanelController.
	 */
	public DataPanel(String name,String buttonAction,String toolTip, int index, int mode, MainPanelController cont)
	{
		this.name = new JLabel(name);
		this.butt = new JButton(buttonAction+name);
		this.number = new JLabel("Number: "+(index+1));
		this.index = index;
		this.displayNumber = index+1;
		this.mode = mode;
		this.cont = cont;
		this.toolTip = toolTip;
		this.makeGUI();
	}

	protected void makeGUI()
	{
		this.butt.addActionListener(this);
		setLayout(new GridLayout(2,3));
		add(name);
		add(new JPanel());
		add(number);
		add(new JPanel());
		butt.setToolTipText(this.toolTip);
		add(butt);
		add(new JPanel());
		JPopupMenu menu = new JPopupMenu();
		JMenuItem remove = new JMenuItem("Remove");
		remove.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				cont.remove(index,mode);	
			}
		});
		menu.add(remove);
		setComponentPopupMenu(menu);
	}
	public int getNumber()
	{
		return this.displayNumber;
	}
	public int getIndex()
	{
		return this.index;
	}
	public String getName()
	{
		return this.name.getText();
	}
	public int getMode()
	{
		return this.mode;
	}
	public void setNumber(int number)
	{
		this.number.setText("Number: "+number);
		this.displayNumber = number;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}
	public void setName(String name)
	{
		this.name.setText(name);
	}
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	protected void remove()
	{
		this.cont.remove(index,mode);
	}
}
