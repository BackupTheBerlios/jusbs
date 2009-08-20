package blacklinen.msf.jusbs.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TagStore extends ObjectStore<Tag>
{
	protected BufferedReader reader;

	@Override
	public void load(String fromFile) throws IOException
	{
		this.reader = new BufferedReader(new FileReader(fromFile));
		String line;
		while((line = reader.readLine())!= null)
		{
			Tag ta = new Tag();
			if(ta.check(line))
			{
				ta.loadfromSaveString(line);
				super.add(ta);
			}
			else
				throw new IOException("Taglist file is broken");
		}
	}

}
