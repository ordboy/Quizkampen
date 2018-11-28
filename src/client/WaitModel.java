package client;

import java.io.Serializable;


public class WaitModel implements Serializable {
    private String score;
    private static final long serialVersionUID = 42L;

    public WaitModel(String score){
        this.score = score;
    }

    public String getStatus() {
        return "wait";
    }

    public String getScore() {
        return score;
    }

}

