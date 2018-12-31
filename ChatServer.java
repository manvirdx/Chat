import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	public ArrayList<ServerThread> clients = new ArrayList<ServerThread>();

	ServerSocket server = null;
	int id = 0;	
	int portNumber = 14001;
	//	private ServerGUI serverGUI;
	private ServerReaderThread srt;
	ServerThread serverThread;

	//String args is passed as a parameter
	//This allows extra parts of the string to be entered when the program is started
	public ChatServer(String[] args) {
		//Allows the user to change the port if -csp is in the string
		for(int i = 0; i < args.length; i++) {
			try {
				if(args[i].equals("-csp")) {
					portNumber = Integer.parseInt(args[i+1]);
				}
			}
			catch(Exception e) {
				System.err.println("Invalid port number. Try again.");
			}
		}
		//Attempts to set with the new port number, if any
		//The port has to be in bounds, otherwise throws an exception
		try {
			server = new ServerSocket(portNumber);
		} 
		catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Allows the client to exit cleanly if they exit
	//This removes the client based on their index in the arraylist
	public void removeClient(int ID) {
		for(int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getID() == ID) {
				this.clients.remove(i);		
			}
		}
		System.out.println("Client has left the chat.");
	}

	//When an exit command is entered on the server console, all clients are removed
	//This iterates through the index of the arraylist and removes every client
	public void removeAllClients() {
		for(int i = 0; i < clients.size(); i++) {
			try {
				this.clients.get(i).getSocket().close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			this.clients.remove(i);
		}
	}

	//Basic accessor method
	public ArrayList<ServerThread> getClients() {
		return clients;
	}

	public void go() {
		//Runs an instance of ServerReaderThread to allow the server to input
		srt = new ServerReaderThread(this);
		srt.start();
		//Allows the server to connect using TCP/IP
		try {
			while(true) {
				//Establishes connection between server and client
				//Runs serverThread with an argument for the client, the socket and the class itself
				//A serverThread is then stored in the list and started, id increases by 1 every time a client is added 
				Socket s = server.accept();	
				System.out.println("Client connected from " + s.getLocalAddress().getHostName());
				serverThread = new ServerThread(id, s, this);
				clients.add(serverThread);
				serverThread.start();
				id++;
			}
		}		
		catch(Exception e) {
			System.err.println("Error in the programme. Start again.");
		}
	}

	public static void main(String[] args) throws IOException {
		new ChatServer(args).go();
	}
}
