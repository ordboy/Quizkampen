package client;



import java.util.List;

public class Category {
    private String Name;
    private List<Question> Questions;
    static final long serialVersionUID = 42L;

    public Category(String name, List<Question> questions){
        this.Name = name;
        this.Questions = questions;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        Questions = questions;
    }
}
