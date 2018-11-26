package client;

import server.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GUI extends JFrame implements ActionListener{

	private JPanel panel1 = new JPanel();			//panel1 har en JLabel (dvs bara text)
	private JPanel panel2 = new JPanel();			//panel2 har svars- eller kategoriknappar
	private JPanel panel3 = new JPanel();			//panel3 har okButton eller Avsluta

	private JLabel label1 = new JLabel("Väntar på motspelare");

	private JButton okButton = new JButton("Nästa");
	private JButton quitButton = new JButton("Avsluta");

	private List<JButton> jbuttonAnswers = new ArrayList<>();	//Lista med alla svarsknappar
	private List<JButton> jbuttonCatagories = new ArrayList<>();		//Lista med alla kategoriknappar

	public GUI(){

		setLayout(new GridLayout(2, 2));

		label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 30));
		panel1.add(label1);

		add(panel1);
		panel1.setVisible(true);
		add(panel2);
		panel2.setVisible(false);
		add(panel3);
		panel3.setVisible(false);

		int sizeHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3);
		int sizeWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3);

		setSize(sizeWidth, sizeHeight);

		int locHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
		int locWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
		setLocation(locWidth, locHeight);
		setVisible(true);
	}

	public void waitingForOtherPlayer() {
		label1.setText("Väntar på Motspelare");
		panel2.setVisible(false);
		panel3.setVisible(false);
		revalidate();
	}


	public void setupCategoryGUI(String[] categories, Client client) {
		label1.setText("Välj kategori");
		panel2.setVisible(true);
		panel3.setVisible(false);

		panel2.setLayout(new FlowLayout());

		for (int i=0; i < categories.length; i++) {
			JButton b = new JButton();
			b.setFont(new Font("Arial", Font.BOLD, 15)); // properties fil?
			b.setText(categories[i]);
			b.addActionListener(client);
			jbuttonCatagories.add(b);
			panel2.add(b);
		}

		revalidate();
	}
	public void removeCategoryGUI() {

		for (JButton b : jbuttonCatagories) {
			panel2.remove(b);
		}

		panel2.revalidate();
		panel2.repaint();
	}

	public void setupQuestionGUI(Question question, Client client) {

		panel2.setVisible(true);
		panel3.setVisible(false);
		panel3.add(okButton);

		panel2.setLayout(new GridLayout(2, 2));

		//shuffle answers
		List<String> randomizeList = Arrays.asList(question.getAnswers());
		Collections.shuffle(randomizeList);

		for (int i=0; i < randomizeList.size(); i++) {
			JButton b = new JButton();
			b.setFont(new Font("Arial", Font.BOLD, 15));
			b.setText(randomizeList.get(i));
			b.addActionListener(client);
			b.setBackground(null);

			jbuttonAnswers.add(b);
			panel2.add (b);
		}

		revalidate();
	}
	public void removeQuestionGUI() {

		for (JButton b : jbuttonAnswers) {
			panel2.remove(b);
		}

		panel2.revalidate();
		panel2.repaint();
	}


	public void setupScoreGUI(int i1, int i2){

		panel2.setVisible(false);
		panel3.remove(okButton);
		panel3.add(quitButton);
		panel3.setVisible(true);

		label1.setFont(new Font("Arial", Font.BOLD, 30));
		label1.setText("Spelare ett: " + i1 + "  -  Spelare två: " + i2);
		revalidate();
		repaint();
	}


	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public List<JButton> getJbuttonCatagories() {
		return jbuttonCatagories;
	}

	public List<JButton> getJbuttonAnswers() {
		return jbuttonAnswers;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("should not be here");
	}
}
