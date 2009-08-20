package blacklinen.msf.jusbs.data;

public class Tag implements ObjectStoreable
{
	protected String tag;
	
	public Tag()
	{
		this.tag = new String();
	}
	
	@Override
	public boolean check(String line)
	{
		if(line.length() > 0)
			return true;
		else 
			return false;
	}

	@Override
	public void loadfromSaveString(String line)
	{
		this.tag = new String(line);
	}

	@Override
	public String toSaveString()
	{
		return new String(this.tag);
	}

	public String getTag()
	{
		return new String(tag);
	}
	
	public void setTag(String newTag)
	{
		this.tag = new String(newTag);
	}
}
