package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class QuestionsDatabase {

    String questionsfile = "src\\Documents\\questions";

    public QuestionsDatabase questionList(String questionsfile, boolean answers) throws Exception {
        QuestionsDatabase<Question> tempList = new QuestionsDatabase<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(questionsfile));
            String qString;
            while ((qString = in.readLine()) != null) {
                Question currentQuestion = new Question(qString);
                currentQuestion.setCorrectAnswer(in.readLine(), true);
                for (int i = 0; i < 3; i++) {
                    currentQuestion.setAnswer(in.readLine(), false);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}