/*

 */
package client;

/**
 *
 * @author stevi
 */
// Java implementation for multithreaded chat client 
// Save file as Client.java 

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class Client 
{ 
	final static int ServerPort = 1234; 

	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
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
