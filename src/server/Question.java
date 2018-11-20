package server;

import java.util.ArrayList;
import java.util.List;

public class Question {
    String question;
    List<AnswersPrel> answers = new ArrayList<>();

    public Question() {
        this.question = question;
    }



    public void setAnswerPrel(String answer, boolean isTrue) {
        answers.add(new AnswersPrel(answer, isTrue));
    }
}