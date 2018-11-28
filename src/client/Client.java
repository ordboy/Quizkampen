package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import client.GUI;
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
					gui.setTitle("Spelare ett");
					break;
				}
				else if(fromServer.equals("player2")) {
					gui.setTitle("Spelare två");
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
					model.WaitModel wm = (model.WaitModel) objFromServer; //String ("wait")
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
				else if (objFromServer instanceof int[]) {
                                    
					int[] scores =  (int[]) objFromServer;
					gui.setupScoreGUI(scores[0], scores[1]);
					gui.getAvsluta().addActionListener(this);
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
		gui.getSvarsAlternativ();

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
				System.out.println("Sleep interrupted for while(!ready)...");
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
		if (gui.getKategoriAlternativ().contains((e.getSource()))) {
			for (JButton b : gui.getKategoriAlternativ()) {
				if (e.getSource() == b) {
					sendTextOfButton(b);
				}
			}
			ready = true;
		}
		else if(gui.getSvarsAlternativ().contains((e.getSource()))) {

			for (JButton b : gui.getSvarsAlternativ()) {
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
					for (JButton bA : gui.getSvarsAlternativ()) {
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
		if (e.getSource() == gui.getAvsluta()) {
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
