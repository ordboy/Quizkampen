package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    Socket socket;

    public Client() {
        try {
            socket = new Socket("127.0.0.1", 55555);
            ObjectOutputStream out = new ObjectOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }

}