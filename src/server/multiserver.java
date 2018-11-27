/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sarko
 */
public class multiserver {
    public static void main(String[] args) {
        
        ServerSocket server = null;
        Socket sock = null;
        System.out.println("Server väntar.....");

        try {
             server = new ServerSocket(4445);
             }
    catch(IOException e){
    e.printStackTrace();
    System.out.println("Server error");

    }
             
            while (true) {
                try{
                sock = server.accept();
                System.out.println("connection Established ");
                ServerThread st=new ServerThread(sock);
                st.start();
            
        }
        catch (Exception e) {
            e.printStackTrace();
        System.out.println("Connection Error");

        }
    }
}

}
class ServerThread extends Thread{  

    String line=null;
    BufferedReader  in = null;
    PrintWriter ut=null;
    Socket s=null;

    public ServerThread(Socket s){
        this.s=s;
    }

    @Override
    public void run() {
    try{
        in= new BufferedReader(new InputStreamReader(s.getInputStream()));
        ut=new PrintWriter(s.getOutputStream());

    }catch(IOException e){
        System.out.println("IO error in server thread");
    }

    try {
        line=in.readLine();
        while(line.compareTo("Quit")!=0){

            ut.println(line);
            ut.flush();
            System.out.println("Svar till Client  :  "+line);
            line=in.readLine();
        }   
    } catch (IOException e) {

        line=this.getName(); //reused String line for getting thread name
        System.out.println("IO Error/ Client "+line+" avslutas abruptly");
    }
    catch(NullPointerException e){
        line=this.getName(); //reused String line for getting thread name
        System.out.println("Client "+line+" Closed");
    }

    finally{    
    try{
        System.out.println("Connection Closing..");
        if (in!=null){
            in.close(); 
            System.out.println(" Socket Input Stream Closed");
        }

        if(ut!=null){
            ut.close();
            System.out.println("Socket Out Closed");
        }
        if (s!=null){
        s.close();
        System.out.println("Socket Closed");
        }

        }
    catch(IOException ie){
        System.out.println("Socket Close Error");
    }
    }//end 
    }
}