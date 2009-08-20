package blacklinen.msf.jusbs.data;

import java.io.IOException;

public interface Data 
{
	public void reset();
	public boolean check();
	public void save() throws IOException;
	public void load() throws IOException;
}
