/*

 */
package server;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.WaitModel;
import server.Player;

/**
 *
 * @author stevi
 */
public class PlayerHandler {

	private Player p1, p2;
	
	private DAO databas = new DAO();
	private Question question;
	
	Properties p = new Properties();
	
	String category;
	String[] categoryChoices;
	int[] scores = new int[2];
	List<Question> fourQuestions;
	
	
	int rounds = 2;
	int questionsPerRound = 2;
	
	
	public PlayerHandler(Player p1, Player p2) {
		
		this.p1 = p1;
		this.p2 = p2;
		
		
		propertiesLoad();
		
		p1.sendObject("player1");
		p1.sendRoundScoreP1("player1");
		p2.sendObject("player2");
		p2.sendObject(waitModel(p1, p2));

		runGame();
		
		sendScore(p1, p2);	
		
	}

	public void waitModel(Player p1, Player p2){
		String waiting = "waiting";
		p1.getScore();
		p2.getScore();
	}



	private void runGame() {
		
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
	
	
	private void roundStartPlayer1() {
		
		sendCategoryChoices(p1); 
		sendQuestions(p1, category);
		
		p1.sendObject(waitModel(p1, p2););
		sendQuestions(p2);

		
	}
	
	private void roundStartPlayer2() {

		sendCategoryChoices(p2);
		sendQuestions(p2, category);
		
		p2.sendObject("wait");
		sendQuestions(p1);
	}
	
	
	private void sendCategoryChoices(Player p) {
		
		categoryChoices = databas.getCategories();
		p.sendObject(categoryChoices);
		
		category = p.retrieveAnswer();
		awaitReadiness(p);
	}

	private void sendQuestions(Player p) {

		for(int i = 0; i < questionsPerRound; i++) {

			question = fourQuestions.get(i);

			p.sendObject(question);

			if (p.retrieveAnswer().equals(question.getCorrectAnswer())) {
				p.incrementScore();
			}

			awaitReadiness(p);

		}
	}

	private void sendQuestions(Player p, String category) {
		
		fourQuestions = databas.getQuestionFromCat(category);
		
		for(int i = 0; i < questionsPerRound; i++) {
			
			question = fourQuestions.get(i);
			
			p.sendObject(question); 
			
			if (p.retrieveAnswer().equals(question.getCorrectAnswer())) {
				p.incrementScore();
			}
			
			awaitReadiness(p);	
			
		}
	}

//	public void sendRoundScoreP1(Player p1){
//		this.scores[0] = p1.getScore();
//		this.p1.sendObject(scores);
//	}
  //
//	public void sendRoundScoreP2(Player p2){
//		this.scores[1] = p2.getScore();
//		this.p1.sendObject(scores);
//	}
//
	
	private void sendScore(Player p1, Player p2) {
		
		this.scores[0] = p1.getScore();
		this.scores[1] = p2.getScore();
		this.p1.sendObject(scores);
		this.p2.sendObject(scores);
	}
	
	private void awaitReadiness(Player p) {
			
		if (p.retrieveAnswer().equals("ready")) {}
	}
	
	
	public void propertiesLoad() {
		try{
			p.load(new FileInputStream("src/Properties"));
		}
		catch(Exception e){
			System.out.println("Could not find Properties file");
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
