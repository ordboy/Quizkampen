package server;




import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListener {
  
    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(55555);
        System.out.println(" Server is Runni");
        
        try {
            while (true) {
                Protocol game = new Protocol();
//                Game game = new Game();
                PlayerHandler player1 
                        = new PlayerHandler(listener.accept(), 1, game);
                PlayerHandler player2 
                        = new PlayerHandler(listener.accept(), 2, game);
                player1.setOpponent(player2);
                player2.setOpponent(player1);
//                game.setCurrentPlayer(player1);
                player1.start();
                player2.start();
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}