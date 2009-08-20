/**
 * 
 */
package blacklinen.msf.jusbs;

import blacklinen.msf.jusbs.dialogs.CloseDialog;

/**
 * This class is only intent to detect the operating system and
 * reads the commandline options. And creates afetr ist a new instance of the Controller.
 * 
 * @author blacklinen
 *
 */
public class StartJUSBS 
{

	/**
	 *Creates a new instance of the Controller
	 * 
	 * @param args The commandline options.
	 */
	public static void main(String[] args) 
	{
		int mode = 3;
		Controller cont = new Controller();
		if(args.length != 0)
		{
			if(args.length > 1)
				StartJUSBS.printError();
			else if(args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("-help"))
				StartJUSBS.printHelp();
			else if(args[0].contains("-mode:"))
			{
				if(args[0].trim().equalsIgnoreCase("-mode:linux"))
				mode = Controller.LINUX_MODE;
				else if(args[0].trim().equalsIgnoreCase("-mode:windows"))
				mode = Controller.WINDOWS_MODE;
				else
					StartJUSBS.printError();
			}
		}
		try
		{
			if(mode == 3)
			{
				System.out.println("Autodetect mode");
				System.out.println("Operatingsystem: "+System.getProperty("os.name"));
				if(System.getProperty("os.name").toLowerCase().contains("linux"))
				{
					System.out.println("Starting in linuxmode");
					cont.start(Controller.LINUX_MODE);
				}
				else if(System.getProperty("os.name").toLowerCase().contains("windows"))
				{
					System.out.println("Starting in windowsmode");
					cont.start(Controller.WINDOWS_MODE);
				}
				else
					cont.start(mode);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if(cont.getMainWindow() != null)
			{
				CloseDialog ed = new CloseDialog(cont);
				ed.showDialog(e);
			}
			else
				System.exit(1);
		}
	}
	
	/**
	 *  Prints the help to commandline.
	 */
	public static void printHelp()
	{
		System.out.println("Help");
		System.out.println("Posible parameters are:");
		System.out.println("'-mode:linux' to start JavaUSBStarter in linuxmode.");
		System.out.println("'-mode:windows' to start JavaUSBStarter in windowsmode.");
		System.out.println("'-h' or '-help' to print this help.");
		System.exit(0);
	}
	/**
	 * Prints an error message and calls the StartJUSBS.printHelp Methode.
	 */
	public static void printError()
	{
		System.out.println("Unvalid parameter, maybe to much or wrong parameter");
		StartJUSBS.printHelp();
	}
}