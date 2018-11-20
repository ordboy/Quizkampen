package client;

import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    InetAddress host = InetAddress.getLoopbackAddress();
    Socket socket;

    public Client() {
        try {
            socket = new Socket(host, 55555);
            BufferedReader inputline = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputline = new PrintWriter(socket.getOutputStream(),true);
            String input;
//            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Scanner scan = new Scanner(System.in);
            while((input=inputline.readLine()) !=null){
                System.out.println(input);
            String userinput = scan.nextLine();
            outputline.println(userinput);
            outputline.flush();
            
            
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }

}
