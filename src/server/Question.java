package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Question implements Serializable {

    protected String question;
    protected List<AnswersPrel> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public void setCorrectAnswers(String answer, boolean isTrue) {
        answers.add(new AnswersPrel(correctAnswer, isTrue));
    }
    public void setAnswers(String answer, boolean isTrue) {
        answers.add(new AnswersPrel(answer, isTrue));
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionQ() {
        return question;
    }
}
