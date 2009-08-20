package blacklinen.msf.jusbs.data;

import java.io.IOException;

public interface DataController
{
	public void add(String[] arr);
	public void remove(int index);
	public void write(String toFile) throws IOException;
	public void load(String toFile) throws IOException;
	public void exec(int index) throws Exception;
}
