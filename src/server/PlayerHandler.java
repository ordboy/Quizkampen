/*

 */
package server;

import model.WaitModel;
import model.Question;
import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author stevi
 */
public class PlayerHandler {

	private Player p1, p2;
	
	private DAO dao = new DAO();
	private Question question;
	
	Properties p = new Properties();
	
	String category;
	String[] categoryChoices;
	int[] scores = new int[2];
	List<Question> fourQuestions;
	
	
	int rounds = 2;
	int questionsPerRound = 2;
	
	
	public PlayerHandler(Player p1, Player p2) {
//		
		this.p1 = p1;
		this.p2 = p2;
		
		
		propertiesLoad();
		
		p1.sendObject("player1");
		p2.sendObject("player2");
		p2.sendObject(new WaitModel(String.valueOf(p2.getScore())));

		runGame(); // här sätts spelet igång egentligen
		
		sendScore(p1, p2);	// efter spelet är slut skickas scores
//                
		
	}

	public void waitModel(Player p1, Player p2){
		String waiting = "waiting";
		p1.getScore();
		p2.getScore();
	}



	private void runGame() {     // Bestämmer hur ronder ska startas beroende på jämt eller ojämt antal
		
		if (rounds > 1 && rounds % 2 == 0) {
			for (int i = 0; i < rounds/2; i++) {
				roundStartPlayer1();
				roundStartPlayer2();
			}
		}
		else if (rounds > 2 && rounds % 2 != 0) {
			for (int i = 0; i < (rounds - 1) / 2; i++) {
				roundStartPlayer1();
				roundStartPlayer2();
			}
			roundStartPlayer1();
		}
		else {
			roundStartPlayer1();
			roundStartPlayer2();
		}
	}
	
	
	private void roundStartPlayer1() { // skickar kategori alternativ, hämtar frågor i vald kategori, visar score och skickar samma frågor till andra spelaren
		
		sendCategoryChoices(p1); 
		sendQuestions(p1, category);

		p1.sendObject(new WaitModel(String.valueOf(p1.getScore())));
		sendQuestions(p2);

		
	}
	
	private void roundStartPlayer2() { // efter spelare 2 har svarat på frågor upprepas metoden ovan i omvänd ordning

		sendCategoryChoices(p2);
		sendQuestions(p2, category);

		p2.sendObject(new WaitModel(String.valueOf(p2.getScore())));
		sendQuestions(p1);
	}
	
	
	private void sendCategoryChoices(Player p) {    //  Hämtar frågor i form av Array, skickar denna till clientsidan, tar emot svar som processeras
		
		categoryChoices = dao.getCategories();
		p.sendObject(categoryChoices);
		category = p.checkClickedButton();
		          
		
		waitingForAnswerClick(p);
	}

	private void sendQuestions(Player p) {    //  här skickas frågorna en och en till spelaren som inte har fått välja kategori, då är redan frågorna satta i frågelistan
                    
		for(int i = 0; i < questionsPerRound; i++) {

			question = fourQuestions.get(i);

			p.sendObject(question);

			if (p.checkClickedButton().equals(question.getCorrectAnswer())) {
				p.scoreCounter();
			}

			waitingForAnswerClick(p);

		}
	}

	private void sendQuestions(Player p, String category) {      // här sätts frågorna i frågelistan efter att en spelare har valt kategori, sen skickas frågorna en och en
           
		         
		fourQuestions = dao.getQuestionFromCat(category);
		for(int i = 0; i < questionsPerRound; i++) {
			
			question = fourQuestions.get(i);
			
			p.sendObject(question); 
			                
			if (p.checkClickedButton().equals(question.getCorrectAnswer())) {
				p.scoreCounter();
			}
			
			waitingForAnswerClick(p);
			
		}
	}

	
	private void sendScore(Player p1, Player p2) { // här skickas slutresultatet till båda spelare
		
		this.scores[0] = p1.getScore();
		this.scores[1] = p2.getScore();
		this.p1.sendObject(scores);
		this.p2.sendObject(scores);
	}
	
	private void waitingForAnswerClick(Player p) { //finns i Player kollar om spelaren har klickat på ett svars alt.
			
		if (p.checkClickedButton().equals("ready")) {}
	}
	
	
	public void propertiesLoad() {
		try{
			p.load(new FileInputStream("src/Properties"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		String roundsString = p.getProperty("rounds", "2");
		String questionsPerRoundString = p.getProperty("questionsPerRound","4");
		
		rounds = Integer.parseInt(roundsString);
		questionsPerRound = Integer.parseInt(questionsPerRoundString);
		
		if (questionsPerRound > 6 || questionsPerRound < 1) {
			questionsPerRound = 2;
		}
		
	}
}
