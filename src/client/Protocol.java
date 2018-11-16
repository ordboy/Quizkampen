package client;

public class Protocol {
    private static final int PLAYER1_WAITING = 0;
    private static final int PLAYER2_CONNECTED = 1;
    private static final int CHOOSE_CATAGORY = 2;
    private static final int START_ROUND = 3;
    private static final int ANSWER_QUESTIONS = 4;
    private static final int NUMBER_OF_ROUNDS = 2;

    private int state = PLAYER1_WAITING;
    private int currentRound = 0;



    public String processInput(String theInput) {
        String theOutput = null;

        if (state == PLAYER1_WAITING) {
            theOutput = "waiting for player 2";
            state = PLAYER2_CONNECTED;
        }
        else if (state == PLAYER2_CONNECTED) {
             state = CHOOSE_CATAGORY;
        }
        else if (state == CHOOSE_CATAGORY) {
            state = START_ROUND;
        }
        else if (state == START_ROUND) {
            if (currentRound == (NUMBER_OF_ROUNDS - 1))
                currentRound = 0;
            else
                currentRound++;
            state = ANSWER_QUESTIONS;
        }
        else if (state == ANSWER_QUESTIONS) {
            state = CHOOSE_CATAGORY;
        }
        else if (state == CHOOSE_CATAGORY) {
            state = START_ROUND;
        }
        else if (state == START_ROUND) {
            if (currentRound == (NUMBER_OF_ROUNDS - 1))
                currentRound = 0;
            else
                currentRound++;
            state = ANSWER_QUESTIONS;
        }

        return theOutput;
    }

}
