/*

 */
package client;

/**
 *
 * @author stevi
 */
// Java implementation for multithreaded chat client 
// Save file as Client.java 

import java.awt.*;
import static java.awt.BorderLayout.*;
import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
import javax.swing.*;

public class Client extends JFrame{ 
	final static int ServerPort = 1234; 
        
        Client(){
            JPanel pane = new JPanel();
            JPanel pane2 = new JPanel();
            
            JButton button1 = new JButton("Submit");
            
            JTextField txt = new JTextField("username");
            
            JLabel label = new JLabel("Skriv in användarnamn");
            
            pane.setLayout(new BorderLayout());
            pane2.add(label);
            
            pane.add(txt, NORTH);
            pane.add(button1,SOUTH);
            
            //actionlistener gets text from textfield
            button1.addActionListener(l->{
                String user = txt.getText();
                txt.setText("");
            });
            
            setLayout(new BorderLayout());
            add(pane2,CENTER);
            add(pane,SOUTH);
            
            pack();
            this.setVisible(true);
            this.setSize(700, 700);
            this.setLocation(750, 350);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);         
            
            }

	public static void main(String args[]) throws UnknownHostException, IOException { 
                Client c = new Client();
		Scanner scn = new Scanner(System.in); 
		
		// getting localhost ip 
		InetAddress ip = InetAddress.getLoopbackAddress();
		
		// establish the connection 
		Socket socket = new Socket(ip, ServerPort); 
		
		// obtaining input and out streams 
		DataInputStream inputstream = new DataInputStream(socket.getInputStream()); 
		DataOutputStream outputstream = new DataOutputStream(socket.getOutputStream()); 
                
                
		// sendMessage thread 
		Thread sendMessage = new Thread(new Runnable() 
		{ 
			@Override
			public void run() { 
				while (true) { 

					// read the message to deliver. 
					String message = scn.nextLine(); 
					
					try { 
						// write on the output stream 
						outputstream.writeUTF(message); 
					} catch (IOException e) { 
						e.printStackTrace(); 
					} 
				} 
			} 
		}); 
		
		// readMessage thread 
		Thread readMessage = new Thread(new Runnable() 
		{ 
			@Override
			public void run() { 

				while (true) { 
					try { 
						// read the message sent to this client 
						String message = inputstream.readUTF(); 
						System.out.println(message); 
					} catch (IOException e) { 

						e.printStackTrace(); 
					} 
				} 
			} 
		}); 

		sendMessage.start(); 
		readMessage.start(); 

	} 
        
} 
