import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

//This class handles multiple client requests
public class ServerThread extends Thread {

	private Socket socket;
	private int id;
	private ChatServer server;
	public String userName;
	
	public ServerThread(int id, Socket socket, ChatServer server) {
		this.id = id;
		this.socket = socket;
		this.server = server;
	}

	public Socket getSocket() {
		return socket;
	}

	public int getID() {
		return id;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while(true) {
				//Receives messages from the client
				String inputLine = in.readLine();
				for(int i = 0; i < inputLine.length(); i++) {
						System.out.println("Client: " + inputLine);
				}
				//Removes client from the chat when EXIT is inputted
				if(inputLine.equals("EXIT")){
					server.removeClient(id);
					in.close();
					break;
				}

				//Prints out the input from the client and displays it on the server console
				for(int i = 0; i < server.getClients().size(); i++) {
					try {
						PrintWriter out = new PrintWriter(server.getClients().get(i).getSocket().getOutputStream(), true);
						out.println(inputLine);
					}
					catch(SocketException e) {
						e.printStackTrace();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				} 
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			socket.close();
		}
		catch(SocketException e) {
			System.out.println("");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
