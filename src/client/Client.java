package client;

import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client{
    InetAddress host = InetAddress.getLoopbackAddress();
    Socket socket;

    public Client() {
        try {
            Scanner scan = new Scanner(System.in);
            String input;
            socket = new Socket(host, 55555);
            BufferedReader inputline = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputline = new PrintWriter(socket.getOutputStream(),true);
            In in = new In();
            in.setIn(inputline);
            in.start();
//            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            while(true){
//                input=inputline.readLine();
//                System.out.println(input);
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
//class Out extends Thread{
//    PrintWriter
//}
class In extends Thread{
    BufferedReader in;
    public void setIn(BufferedReader in){
        this.in = in;
    }
    @Override
    public void run(){
        String input="";
        while(true){
            
            try {
                input = this.in.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(input);
        }
    }
}
