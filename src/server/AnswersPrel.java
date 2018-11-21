package server;

public class AnswersPrel {

    private String answer;
    private boolean correctAnswer;

    public AnswersPrel(String answer, boolean correctAnswer) {
        this.setAnswer(answer);
        this.setCorrectAnswer(correctAnswer);
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
