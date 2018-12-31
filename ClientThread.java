import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

//This class looks for input from the keyboard
public class ClientThread extends Thread {

	private BufferedReader in;
	private ClientGUI clientGUI;

	
	//The two constructors follow the same format for connectivity
	//This constructor does not run the GUI and thus only has one parameter
	public ClientThread(Socket clientSocket) {
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));		
		}
		catch(IOException e) {
			//e.printStackTrace();
		}
	}

	//This runs an instance of the GUI every time a client connects
	//And has two parameters
	public ClientThread(Socket clientSocket, ClientGUI clientGUI) {
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		this.clientGUI = clientGUI;
	}

	public void run(){	
		String input = "";
		//Ensures that there is input whether or not the GUI is run
		//Hence, receiveMessage is called if clientGUI is running and it isn't if it is null
		try {
			while((input = in.readLine()) != null) {
				if(clientGUI != null) {
					clientGUI.receiveMessage("You:" + input);					
				}
				else {
					System.out.println("You: " + input);
				} 
			}
		} 
		catch(SocketException e) {
			System.out.println("Server has shut. You have been removed.");
			System.exit(0);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
};

