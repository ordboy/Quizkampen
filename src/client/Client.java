package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements ActionListener {
    InetAddress host = InetAddress.getLoopbackAddress();
    Socket socket;

    public Client() {
        try {
            
            String input;
            socket = new Socket(host, 55555);
            BufferedReader inputline = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputline = new PrintWriter(socket.getOutputStream(),true);
            Out out = new Out();
            out.setOut(outputline);
            out.start();
            In in = new In();
            in.setIn(inputline);
            in.start();
//            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
class Out extends Thread{
    Scanner scan = new Scanner(System.in);
    PrintWriter out;
    public void setOut(PrintWriter out){
        this.out = out;
    }
    @Override
    public void run(){
        String userinput = "start";
        
        while(true){
//                input=inputline.readLirne();
//                System.out.println(input);
            out.println(userinput);
            out.flush();
            userinput = scan.nextLine();
            
            
            
            }
    }
}
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
