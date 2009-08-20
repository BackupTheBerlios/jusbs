package blacklinen.msf.jusbs.data.settings;

import java.io.IOException;

import javax.swing.JPanel;

@SuppressWarnings("serial")
abstract class SettingsPanel extends JPanel
{
	public abstract void setSettings() throws IOException;
}
