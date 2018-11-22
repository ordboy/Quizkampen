package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDatabase {

    String questionsfile = "src\\Documents\\questions";

    public void questionList(String questionsfile, boolean answers) throws Exception {
        List<Question> tempList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(questionsfile));
            String qString;
            while ((qString = in.readLine()) != null) {
                Question currentQuestion = new Question(qString);
                currentQuestion.setCorrectAnswers(in.readLine(), true);
                for (int i = 0; i < 3; i++) {
                    currentQuestion.setAnswers(in.readLine(), false);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
