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
        
        while(true){
            String svar;
            try {
                svar = input.readLine();
            
            if(svar.equalsIgnoreCase("ja")){
                output.println("Grattis!");
                output.flush();
            }if (svar.equalsIgnoreCase("nej")){
                output.println("Tråkigt");
                output.flush();
                
            }else{
                output.println("Felaktigt svar");
                output.flush();
            }
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}
