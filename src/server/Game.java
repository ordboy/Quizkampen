/*

 */
package server;

/**
 *
 * @author stevi
 */
public class Game {
    String[] q;
    
    private PlayerHandler currentPlayer;

    /**
     * @return the currentPlayer
     */
    public PlayerHandler getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(PlayerHandler currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
}
