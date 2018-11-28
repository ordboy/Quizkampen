package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import model.*;


public class Client implements ActionListener {

	GUI gui = new GUI();

	private ObjectOutputStream out;
	private ObjectInputStream in;

	private int port = 55555;
	private String ip = "127.0.0.1";
	private Socket connection;

	String fromServer;
	Question question;
	String[] categories;
       
	boolean ready = false;
	boolean inGame = true;

	private Object objFromServer;

	private Client() {

		setupConnection();
		setupWriter();
		setupReader();

		awaitingOpponent();
		InGame();

	}

	private void setupConnection() {
		try {
			connection = new Socket(ip, port);
		} catch (UnknownHostException e) {
			System.out.println("Could not find host...");
		} catch (IOException e) {
			System.out.println("No connection to server...");
		}
	}
	private void setupWriter() {

		try {
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
		} catch (IOException e) {
			System.out.println("Could not set up OutputStream...");
		}
	}

	private void setupReader() {

		try {
			in = new ObjectInputStream(connection.getInputStream());
		} catch (IOException e) {
			System.out.println("Could not set up InputStream...");
		}
	}

	private void awaitingOpponent() {
		while (true) {
			try {
				fromServer = (String) in.readObject();
				if(fromServer.equals("player1")) {
					gui.setTitle("Spelare 1");
					break;
				}
				else if(fromServer.equals("player2")) {
					gui.setTitle("Spelare 2");
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				break;
			}
		}
	}


	private void InGame() {

		while(inGame) {
			try {
				objFromServer = in.readObject();
				if (objFromServer instanceof model.WaitModel) {
					model.WaitModel wm = (model.WaitModel) objFromServer; //String ("wait") + score efter varje rond
					if (wm.getStatus().equalsIgnoreCase("wait")) {
						gui.waitingForOpponent(wm.getScore());
					}
				}
				else if (objFromServer instanceof String[]) {			//Categories
					gui.setupCategoryGUI((String[])objFromServer,this);
					awaitReady();
					gui.removeCategoryGUI();
				}
				else if (objFromServer instanceof Question) {			//Question
                                                                       
					setupQuestion();
					awaitReady();
					gui.removeQuestionGUI();
				}
				else if (objFromServer instanceof int[]) { 			//score
                                    
					int[] scores =  (int[]) objFromServer;
					gui.setupScoreGUI(scores[0], scores[1]);
					gui.getExit().addActionListener(this);
				}
				else if (objFromServer == null) {
					System.out.println("objFromServer is null...");
				}
                                
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}

		}

	}

	private void setupQuestion() {

		question = (model.Question) objFromServer;

		gui.getOkButton().addActionListener(this);
		gui.getPanel3().setVisible(false);
		gui.getAnswerAlt();

		gui.getLabel1().setText(question.getQuestion());
		gui.getPanel1().revalidate();

		gui.setupQuestionGUI(question,this);


		gui.revalidate();
		ready = false;
	}

	private void awaitReady() {
		while(!ready) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			out.writeObject("ready");
			out.flush();
		} catch (IOException e) {
			System.out.println("Could not send ready...");
		}
		ready = false;
	}


	public static void main(String[] args) {
		new Client();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// if source is a button do following
		if (gui.getCatAlt().contains((e.getSource()))) {
			for (JButton b : gui.getCatAlt()) {
				if (e.getSource() == b) {
					sendTextOfButton(b);
				}
			}
			ready = true;
		}
		else if(gui.getAnswerAlt().contains((e.getSource()))) {

			for (JButton b : gui.getAnswerAlt()) {
				if (b.getText().equals(question.getCorrectAnswer())) {
					b.setBackground(Color.GREEN);
					gui.getPanel2().repaint();
				}
				if (e.getSource() == b) {
					if (!b.getText().equals(question.getCorrectAnswer())) {
						b.setBackground(Color.RED);
						gui.getPanel2().repaint();
					}
					sendTextOfButton(b);
					for (JButton bA : gui.getAnswerAlt()) {
						bA.removeActionListener(this);
					}
					gui.getPanel3().setVisible(true);
					gui.getPanel3().revalidate();
				}
			}
		}
		if (e.getSource() == gui.getOkButton()) {
			gui.getPanel3().setVisible(false);
			gui.getPanel3().revalidate();
			ready = true;
		}
		if (e.getSource() == gui.getExit()) {
			System.exit(0);
		}
	}


	private void sendTextOfButton(JButton b) {

		try {
			out.writeObject(b.getText()); out.flush();
		} catch (IOException e1) {
			System.out.println("Could not send out text of button...");
		}
	}
}
