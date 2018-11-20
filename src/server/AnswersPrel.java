package server;

public class AnswersPrel {
    private String answer;
    private boolean correctAnswer;

    public AnswersPrel(String answer, boolean correctAnswer) {
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }
}
