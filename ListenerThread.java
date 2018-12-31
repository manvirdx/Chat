import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ListenerThread extends Thread {

	private BufferedReader in;
	private Socket listenerSocket;

	public ListenerThread(Socket listenerSocket) {
		try {
			in = new BufferedReader(new InputStreamReader(listenerSocket.getInputStream()));
			this.listenerSocket = listenerSocket;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		PrintWriter out;
		try {
			out = new PrintWriter(listenerSocket.getOutputStream(), true);

			//Listens for keyboard input
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String chatInput;
			while(true) {
				try {
					chatInput = stdIn.readLine();
					out.println(chatInput);

					//Checks input for exit
					//Removes the user and closes the socket to ensure an exception is thrown
					if(chatInput.equals("EXIT")) {
						System.out.println("You have left the chat");
						System.exit(0);
						listenerSocket.close();
					}
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
};

