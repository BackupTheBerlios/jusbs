package blacklinen.msf.jusbs.utils.searchPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import blacklinen.msf.jusbs.data.ObjectStore;

public class SearchEngineStore extends ObjectStore<SearchEngine> 
{
	protected BufferedReader reader;
	
	@Override
	public void load(String fromFile) throws IOException 
	{
		reader = new BufferedReader(new FileReader(fromFile));
		String line;
		while((line = reader.readLine())!= null)
		{
			SearchEngine eng = new SearchEngine();
			if(!eng.check(line))
				throw new IOException("SerchEngine list broken");
			eng.loadfromSaveString(line);
			super.objects.add(eng);
		}
		reader.close();
		super.objects.trimToSize();
	}
	
	public String[] getNames()
	{
		super.objects.trimToSize();
		String[] names = new String[super.objects.size()];
		int num = 0;                            
		for(SearchEngine str : super.objects)
		{
			names[num] = str.getName();
			System.out.println(str.getName());
			num++;
		}
		return names;
	}
}
