package server;


import java.io.*;
import server.Question;

import java.io.Serializable;


public class Protocol implements Serializable {
    
    final int PLAYER1_CAT = 0;
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
    Protocol(){
        this.state = 0;
//        p1Go(null);
//        p2Go(null);
    }
    int state;
    
   

    public String pGo(String in){
        if(state == 0 ){
            ++state;
            return "p1välj kat 1,2,3,4";
        }
        if(state == 1){
            
            state++;
            return "p2vänta ya";
        }
        if(state == 2){
            
            state++;
            return "fråga p1";
        }
        if(state == 3){
            
            state++;
            return "fråga p2";
        }
        if(state == 4){
            
            state++;
            return "p1vänta ya";
        }
        if(state == 5){

            state++;
            return "p2välj kat";
        }

        else{
            return null;
        }
    }
    
    
    //ublic void setTotalQsInRond(int allQuestions) {
    //
    //   this.allQuestions = allQuestions;
    //   tempQuestions = new Question[this.allQuestions];
    //   opponentsAnswers = new boolean[this.allQuestions];
    //   for (int i = 0; i < this.allQuestions; i++) {
    //       tempQuestions[i] = new Question();
    //       opponentsAnswers[i] = false;
    //   }
    //



    


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
    public void play(){
        
    }
}
