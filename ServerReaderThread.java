import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerReaderThread extends Thread {

	//This class checks keyboard input from the server console

	private ChatServer server;

	public ServerReaderThread(ChatServer server) {
		this.server = server;
	}

	public void run() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			//Server will shut cleanly when EXIT is inputted into the console
			try {
				String chatInput;
				chatInput = in.readLine();

				//Calls the removeAllClients to disconnect all clients from the chat
				//Closes the BufferedReader to prevent clients from still looking for the server
				if(chatInput.equals("EXIT")) {
					System.out.println("Chat closed");
					server.removeAllClients();
					in.close();
					System.exit(0);
				}
				else if(chatInput.contains("-csp")) {

				}
				else {
					System.out.println("Server: " + chatInput);
				}
			} 
			catch(IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
