import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

//Not all functionality has been implemented in this class

public class ClientGUI extends JFrame {

	private JPanel clientFrame;
	private ChatClient client;
	private JTextArea clientChatBoard;
	private JTextField msgBox;

	public ClientGUI(ChatClient client) {
		this.client = client;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					console();
					setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//	public void sendMessage() {
	//		client.sendToGUI();
	//	}

	public void receiveMessage(String receivedInput) {
		clientChatBoard.setText(receivedInput);
	}

	public void console() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		clientFrame = new JPanel();
		clientFrame.setBackground(Color.BLUE);
		clientFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(clientFrame);
		clientFrame.setLayout(null);

		clientChatBoard = new JTextArea();
		clientChatBoard.setBounds(88, 13, 512, 244);
		clientFrame.add(clientChatBoard);

		msgBox = new JTextField();
		msgBox.setBounds(88, 355, 512, 85);
		clientFrame.add(msgBox);
		msgBox.setColumns(10);

		JButton sendButton = new JButton("SEND");
		sendButton.setBounds(88, 278, 129, 53);
		clientFrame.add(sendButton);
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton clearButton = new JButton("CLEAR");
		clearButton.setBounds(272, 278, 129, 53);
		clientFrame.add(clearButton);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgBox.setText("");
			}
		});		

		JButton exitButton = new JButton("EXIT");
		exitButton.setBounds(471, 278, 129, 53);
		clientFrame.add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		JLabel changePort = new JLabel("Change port");
		changePort.setForeground(Color.WHITE);
		changePort.setBounds(105, 469, 79, 16);
		clientFrame.add(changePort);

		JLabel changeAddress = new JLabel("Change address");
		changeAddress.setForeground(Color.WHITE);
		changeAddress.setBounds(88, 504, 102, 16);
		clientFrame.add(changeAddress);

		JTextField portBox = new JTextField();
		portBox = new JTextField();
		portBox.setColumns(10);
		portBox.setBounds(189, 466, 258, 22);
		clientFrame.add(portBox);

		JTextField addressBox = new JTextField();
		addressBox = new JTextField();
		addressBox.setColumns(10);
		addressBox.setBounds(189, 501, 258, 22);
		clientFrame.add(addressBox);

		JButton bindPortButton = new JButton("BIND PORT");
		bindPortButton.setBounds(470, 465, 97, 25);
		clientFrame.add(bindPortButton);
		bindPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});	

		JButton bindAddressButton = new JButton("BIND IP");
		bindAddressButton.setBounds(470, 500, 97, 25);
		clientFrame.add(bindAddressButton);
		bindAddressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});	
	}
}


