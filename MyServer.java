import java.net.*;
import java.io.*;
import java.util.*;

public class MyServer
{	
	public static void main(String [] args)
	{
		try
		{	//intentiate the Server Socket class to make a server application. the 8888 is the port number
			ServerSocket server = new ServerSocket(8888);
			//Create a socket to establish a connection
			Socket socket = null;
			while(true)
			{
				//establish the connection
				socket = server.accept();//ready to accept anything
				Connection conn = new Connection(socket);
				conn.start();
				
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}


class Connection extends Thread
{
	private Socket socket;
	private BufferedReader reader;	
	private PrintStream printer;
	private String source;
	private int countAbuses;

	public Connection(Socket soc)
	{
		try
		{   
			socket = soc;
			source = new String(socket.getInetAddress().getHostName());
			//define a buffered reader for the incoming messages
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//define a printstream for the outgoign messages
			printer = new PrintStream(socket.getOutputStream());

		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void run()
	{
		try
		{
				String line;
				//Keep reading lines of messgaes from the remote machine and display them
				while ((line = reader.readLine())!=null)//line = reader.readLine(); --> accept one line only
				{	
					if (line.compareTo("Exit")==0 || countAbuses==10)
					{	printer.println("Closing your connection..");
						reader.close();
						printer.close();
						socket.close();
						break;
					}				
					System.out.println(source + ": "+ line);
				}

		} catch(Exception ex)
		  {
			ex.printStackTrace();
		  }
	}
}

			




