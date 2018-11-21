/*

 */
package server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stevi
 */
public class PlayerHandler extends Thread {
    
    int player;
    PlayerHandler opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    Game game;
    
    //constructor
    public PlayerHandler(Socket socket, int player, Game game){
        this.game = game;
        this.player = player;
        this.socket = socket;
        try{
            input = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Welcome player: " + player);
            output.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setOpponent( PlayerHandler opponent){
        this.opponent = opponent;
    }
    public void run(){
        output.println("har du skitit på dig?");
        System.out.println("skickat fråga");
        output.flush();
        
//        if(player == 1)
//            output.println("svara");
        String svar;
        try {
        while((svar = input.readLine().trim())!=null){

            if(svar.equalsIgnoreCase("ja")){
                output.println("Grattis!");  
                break;
            }
            else if (svar.equalsIgnoreCase("nej")){
                output.println("Tråkigt");                
                break;
            }
            
        }    
            }catch(Exception e){
                e.printStackTrace();
            }
        

    }

}
