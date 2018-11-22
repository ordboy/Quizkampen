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
    Protocol game;

    //constructor
    public PlayerHandler(Socket socket, int player, Protocol game) {
//        pro
        this.game = game;
        this.player = player;
        this.socket = socket;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Welcome player: " + player);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOpponent(PlayerHandler opponent) {
        this.opponent = opponent;
    }

    public void run() {
        try {
            String svar;
            while (true) {
                svar = input.readLine();
                if (player == 1) {
                    output.println(game.pGo(svar));
                }
                    if (player == 2) {
                        output.println(game.pGo(svar));

                        output.flush();
                    }

                }


            } catch (IOException ex) {
            Logger.getLogger(PlayerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
}
