package client;


import server.Question;

import java.io.Serializable;


public class Protocol implements Serializable {


    int state;
    final int PLAYER1_WAITING = 0;
    final int PLAYER2_CONNECTED = 1;
    final int CHOOSE_CATAGORY = 2;
    final int START_ROUND = 3;
    final int ANSWER_QUESTIONS = 4;
    final int RESULT_SCREEN = 5;



    Question[] tempQuestions;
    boolean[] opponentsAnswers;
    int roundCounter;

    protected int scorePlayerOne;
    protected int scorePlayerTwo;
    private int allQuestions;

    public Protocol() {
        roundCounter = 0;
        state = PLAYER1_WAITING;
    }


    public void setTotalQsInRond(int allQuestions) {
        this.allQuestions = allQuestions;
        tempQuestions = new Question[this.allQuestions];
        opponentsAnswers = new boolean[this.allQuestions];
        for (int i = 0; i < this.allQuestions; i++) {
            tempQuestions[i] = new Question();
            opponentsAnswers[i] = false;
        }
    }



    public void clearOpponentAnswers() {
        for (int i = 0; i < opponentsAnswers.length; i++) {
            opponentsAnswers[i] = false;
        }
    }



    public void setScoreUserOne(int score){
        this.scorePlayerOne = score;
    }
    public void setScorePlayerTwo(int score){
        this.scorePlayerTwo = score;
    }
    public int getScorePlayerOne(){
        return this.scorePlayerOne;
    }
    public int getScorePlayerTwo(){
        return this.scorePlayerTwo;
    }
}
