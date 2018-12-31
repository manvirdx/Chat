import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;


//This class has not been fully implemented

public class ServerGUI extends JFrame {

	private JPanel serverFrame;
	private JTextField msgBox;

	/**
	 * Launch the application.
	 */
	public ServerGUI() {
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

	/**
	 * Create the frame.
	 */
	public void console() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		serverFrame = new JPanel();
		serverFrame.setBackground(Color.RED);
		serverFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		serverFrame.setLayout(null);
		setContentPane(serverFrame);

		JTextArea serverChatBoard = new JTextArea();
		serverChatBoard.setBounds(88, 13, 512, 244);
		serverFrame.add(serverChatBoard);

		JButton exitButton = new JButton("EXIT");
		exitButton.setBounds(273, 287, 127, 47);
		serverFrame.add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JLabel changePortLabel = new JLabel("Change port");
		changePortLabel.setForeground(Color.WHITE);
		changePortLabel.setBounds(124, 363, 79, 16);
		serverFrame.add(changePortLabel);

		JTextField portBox = new JTextField();
		portBox.setBounds(204, 363, 268, 22);
		portBox.setColumns(10);
		serverFrame.add(portBox);

		JButton bindPortButton = new JButton("BIND PORT");
		bindPortButton.setBounds(489, 359, 97, 25);
		serverFrame.add(bindPortButton);

	}

}
