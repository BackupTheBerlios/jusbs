package blacklinen.msf.jusbs.help;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;

@SuppressWarnings("serial")
public class HelpComponent extends JEditorPane
{
	
	public HelpComponent()
	{
		super("text/html", "");
		setEditable(false);
		//edit.setText(content);
	}
	
	public void load(String path)
	{
		try
		{
			read(new FileReader(path), new HTMLDocument());
		}
		catch(IOException ioe)
		{
			setText("Can not load the HTML-Help file.<br /> Details: "+ioe.getLocalizedMessage());
		}
	}
}
