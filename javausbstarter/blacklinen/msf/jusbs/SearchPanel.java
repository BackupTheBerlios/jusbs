/**
 * 
 */
package blacklinen.msf.jusbs;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author blacklinen
 *
 */
@SuppressWarnings("serial")
public class SearchPanel extends JPanel 
{
	public SearchPanel()
	{
		super();
		setLayout(new FlowLayout());
		JLabel search = new JLabel("Search: ");
		add(search);
		final JTextField string = new JTextField(15);
		string.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) 
			{
				try
				{
					URI uri = new URI("http://search.wikia.com/search.html#"+string.getText().trim());
	            	Desktop desktop = Desktop.getDesktop();
					desktop.browse(uri);
				}
				catch(Exception ioe)
				{
					System.out.println(ioe.getMessage());
				}
			}
		});
		string.setToolTipText("Search with WikiSearch");
		add(string);
		JLabel icon = null;
		try
		{
		icon = new JLabel(new ImageIcon(new URL("http://search.wikia.com/kt_files/front-logo.png")));
		}
		catch(Exception ioe)
		{
			System.out.println(ioe.getMessage());
			icon.setText("Icon not found");
		}
		Icon ico = icon.getIcon();
		if(ico.getIconHeight() < 1 && icon.getWidth() < 1)
		{
			icon.setText("Search with WikiSearch");
		}
		icon.setToolTipText("Go to WikiSearch Website");
		icon.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent arg0) 
			{
				try
				{
					URI uri = new URI("http://search.wikia.com");
	            	Desktop desktop = Desktop.getDesktop();
					desktop.browse(uri);
				}
				catch(Exception ioe)
				{
					System.out.println(ioe.getMessage());
				}
			}
			public void mouseEntered(MouseEvent arg0) 
			{
			}

			public void mouseExited(MouseEvent arg0) 
			{	
			}

			public void mousePressed(MouseEvent arg0) 
			{
			}

			public void mouseReleased(MouseEvent arg0) 
			{	
			}
		});
		add(icon);
	}
}
