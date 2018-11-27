package server;

import java.io.Serializable;


public class WaitModel implements Serializable {
    private String score;

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