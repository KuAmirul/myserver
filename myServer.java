import java.net.*;
import java.io.*;
import java.util.*;

public class MyServer
{
	public static void main(String [] args)
	{
		try
		{
			//init server socket class to make new server application
			ServerSocket server = new ServerSocket(8888);
			//create socket to est connection
			Socket socket = null;
			//est the connection
			socket=server.accept();
			//define buffered reader for incoming msg
			BufferedReader reader = new BufferedReader(new InputStreamReader (socket.getInputStream()));
			//define printstream for outgoing msg
			PrintStream printer = new PrintStream(socket.getOutputStream());
			
			String line;
			//keep reading line of msg from the remote machine and then display 
			while((line = reader.readLine())!=null)
			{
				if(line.compareTo("tutup")==0)
				{
					printer.println("Closing connection...........");
					reader.close();
					printer.close();
					socket.close();
					break;
				}
				
				System.out.println(line);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}	
