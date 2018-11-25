package server;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class QuestionsDatabase {



    protected String Catagory1 = "Sport";
    protected String Catagory2 = "Geografi";
    protected String Catagory3 = "Historia";
    protected String Catagory4 = "cat4";

    protected Question question1 = new Question("Q1", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question2 = new Question("Q2", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question3 = new Question("Q3", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question4 = new Question("Q4", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question5 = new Question("Q5", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question6 = new Question("Q1", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question7 = new Question("Q2", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question8 = new Question("Q3", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question9 = new Question("Q4", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question10 = new Question("Q5", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question11 = new Question("Q1", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question12 = new Question("Q2", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question13 = new Question("Q3", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question14 = new Question("Q4", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question15 = new Question("Q5", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);
    protected Question question16 = new Question("Q5", "Answer1",
            "Answer2", "Answer3", "Answer4", 1);

    List<String> categories = new ArrayList<>();
    String[] categoryChoice = new String[4];

    public List<Question> getQuestionFromCat(String n){

        ArrayList <Question> CatagoryQuestions = new ArrayList<>();

        n = n.toLowerCase();

        if(n.matches("Sport")){
            CatagoryQuestions.add(question1);
            CatagoryQuestions.add(question2);
            CatagoryQuestions.add(question3);
            CatagoryQuestions.add(question1);
            Collections.shuffle(CatagoryQuestions);
        } else if(n.matches("Geografi")){
            CatagoryQuestions.add(question5);
            CatagoryQuestions.add(question6);
            CatagoryQuestions.add(question7);
            CatagoryQuestions.add(question8);
            Collections.shuffle(CatagoryQuestions);
        } else if(n.matches("Historiak")){
            CatagoryQuestions.add(question9);
            CatagoryQuestions.add(question10);
            CatagoryQuestions.add(question11);
            CatagoryQuestions.add(question12);
            Collections.shuffle(CatagoryQuestions);
        } else if(n.matches("cat4")){
            CatagoryQuestions.add(question13);
            CatagoryQuestions.add(question14);
            CatagoryQuestions.add(question15);
            CatagoryQuestions.add(question16);
            Collections.shuffle(CatagoryQuestions);
        }
        else {
            System.out.println(n + " was not found in Database");
        }

        return CatagoryQuestions;
    }

    public Question getQuestion() {

        List<Question> Question = getQuestionFromCat("???");
        Collections.shuffle(Question);

        return Question.get(0);
    }

    public String[] getCategories() {

        categories.add(Catagory1);
        categories.add(Catagory2);
        categories.add(Catagory3);
        categories.add(Catagory4);

        Collections.shuffle(categories);

        for(int i = 0; i < 3; i++) {
            categoryChoice[i] = categories.get(i);
        }

        return categoryChoice;
    }


    public static void main(String[] args) {
        QuestionsDatabase qdb = new QuestionsDatabase();
    }
}
