import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient implements Runnable {

	private String hostname;
	private int port;
	private Socket connectSocket;
	private ClientGUI clientGUI;

	//When invoked, allows the client to connect to an available server
	public ChatClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;

		try {
			connectSocket = new Socket(hostname, port);
		}
		catch(UnknownHostException e) {
			//e.printStackTrace();
		}
		catch(IOException e) {
			//e.printStackTrace();
		}

	}
	//This method runs with the GUI
	//This invokes the constructor from ClientThread that has two arguments
	//However, both run and runWithoutGUI connect the same way
	public void run() {
		clientGUI = new ClientGUI(this);
		try {
			new ClientThread(connectSocket, clientGUI).start();
			new ListenerThread(connectSocket).start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//This method runs without the GUI
	//This invokes the constructor from ClientThread with one argument
	public void runWithoutGUI() {
		try {
			new ClientThread(connectSocket).start();
			new ListenerThread(connectSocket).start();
		}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}

	//Basic accessor method
	public Socket getSocket() {
		return this.connectSocket;
	}

	public static void main(String[] args) {
		//Port and address are set to default values
		int port = 14001;
		String address = "localhost";

		//This asks the user whether or not they would like to use a GUI
		System.out.println("Would you like to use a GUI? Answer YES or NO.");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));;
		String ask = "";
		try {
			ask = in.readLine();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		//-ccp allows the port to be changed
		//-cca allows the address to be changed
		//If these are not used, the server will attempt to connect using default values instead
		try {
			for(int i = 0; i < args.length; i++) {
				//i has to increment for both in order to allow simultaneous change of address and port
				if(args[i].equals("-ccp")) {
					port = Integer.parseInt(args[i+1]);
					i++;
				}
				else if(args[i].equals("-cca")) {
					address = args[i+1];
					i++;
				}
			}
		}
		catch(IllegalArgumentException e) {
			//System.err.println("Invalid parameters.\n");
			System.err.println("Invalid parameters.\n" + "For guidance:\n" + "1) When attempting to bind port number, use -ccp xxxx (port number should between 1 and 65535)");
			System.err.println("2) ");
		}

		//This is placed here so that the connection is not refused when NO is inputted
		//NO runs the runWithoutGUI method that doesn't run any instance of the GUI
		//YES runs the run method that does
		if(ask.equals("YES")) {
			new ChatClient(address,port).run();
		}
		else if(ask.equals("NO")){
			new ChatClient(address,port).runWithoutGUI();
		}

	}
}
