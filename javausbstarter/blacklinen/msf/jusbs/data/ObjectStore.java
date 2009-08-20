package blacklinen.msf.jusbs.data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class ObjectStore<T extends ObjectStoreable>
{
	protected ArrayList<T> objects;
	protected PrintWriter writer;
	
	public ObjectStore()
	{
		this.objects = new ArrayList<T>();
	}
	public ObjectStore(ArrayList<T> objects)
	{
		this.objects = objects;
	}
	
	public abstract void load(String fromFile) throws IOException;
	
	public void write(String toFile) throws IOException
	{
		this.writer = new PrintWriter(new FileWriter(toFile));
		this.objects.trimToSize();
		for(ObjectStoreable ost : this.objects)
		{
			writer.println(ost.toSaveString());
		}
		writer.close();
	}
	
	public void add(T obj)
	{
		this.objects.add(obj);
	}
	
	public void remove(int index)
	{
		this.objects.remove(index);
	}
	
	public int getLenght()
	{
		return this.objects.size();
	}
	public T getElement(int index)
	{
		return this.objects.get(index);
	}
	public ArrayList<T> getElements()
	{
		return this.objects;
	}
}
