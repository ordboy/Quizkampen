package server;

import Client.Protocol;
import com.sun.security.ntlm.NTLMException;
import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListener extends Thread {
    protected int port = 55555;
    ServerSocket serverSocket;
    Protocol match = new Protocol();

    public ServerListener() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket_1 = serverSocket.accept();

                ObjectOutputStream Player1Out = new ObjectOutputStream(clientSocket_1.getOutputStream());
                ObjectInputStream Player1In = new ObjectInputStream(clientSocket_1.getInputStream());
                Player1Out.writeObject(match);

                Socket clientSocket_2 = serverSocket.accept();
                ObjectOutputStream Player2Out = new ObjectOutputStream(clientSocket_2.getOutputStream());
                ObjectInputStream Player2In = new ObjectInputStream(clientSocket_2.getInputStream());
                Player2Out.writeObject(match);

                Server server = new Server(clientSocket_1, clientSocket_2);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            catch (NTLMException e) { // ingen aning vad det är???
                e.printStackTrace();
            }
        }
    }

}
