package blacklinen.msf.jusbs.data;

public interface ObjectStoreable 
{
	public abstract boolean check(String line);
	public String toSaveString();
	public void loadfromSaveString(String line);
}
