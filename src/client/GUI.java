package client;


//import client.Client;
//import server.Question;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Question;

public class GUI extends JFrame implements ActionListener{

    private JPanel panel1 = new JPanel();			//panel1 har en JLabel (dvs bara text)
    private JPanel panel2 = new JPanel();			//panel2 har svars- eller kategoriknappar
    private JPanel panel3 = new JPanel();			//panel3 har okButton eller Avsluta

    private JLabel label1 = new JLabel("Väntar på att spel ska starta");

    private JButton okButton = new JButton("Tryck för att gå vidare");
    private JButton avsluta = new JButton("Avsluta");

    private List<JButton> svarsAlternativ = new ArrayList<>();	//Lista med alla svarsknappar
    private List<JButton> kategoriAlternativ= new ArrayList<>();		//Lista med alla kategoriknappar

    public GUI(){

        setLayout(new GridLayout(3, 1));

        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 15));
        panel1.add(label1);
        panel1.setBackground(new java.awt.Color(153, 255, 153));
        panel3.setBackground(new java.awt.Color(153, 255, 153));
        panel2.setBackground(Color.pink);
        panel2.setForeground(Color.pink);
        
        this.setBackground(new java.awt.Color(153, 255, 153));
        
        add(panel1); panel1.setVisible(true);
        add(panel2); panel2.setVisible(false);
        add(panel3); panel3.setVisible(false);
        
        int sizeHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.5);
        int sizeWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3);

        setSize(sizeWidth, sizeHeight);

        int locHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
        int locWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
        setLocation(locWidth, locHeight);
        setVisible(true);
    }

    public void waitingForOpponent(String score) {
        
        label1.setText("Motståndare spelar : " + "du har " + score + " poäng");
        
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
            b.setFont(new Font("Arial", Font.BOLD, 15));
            b.setText(categories[i]);
            b.addActionListener(client);
            kategoriAlternativ.add(b);
            panel2.add(b);
        }

        revalidate();
    }
    public void removeCategoryGUI() {

        for (JButton b : kategoriAlternativ) {
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
        List<String> shuffledList = Arrays.asList(question.getAnswers());
        Collections.shuffle(shuffledList);

        for (int i=0; i < shuffledList.size(); i++) {
            JButton b = new JButton();
            b.setFont(new Font("Arial", Font.BOLD, 15));
            b.setText(shuffledList.get(i));
            b.addActionListener(client);
            b.setBackground(null);

            svarsAlternativ.add(b);
            panel2.add (b);
        }

        revalidate();
    }
    public void removeQuestionGUI() {

        for (JButton b : svarsAlternativ) {
            panel2.remove(b);
        }

        panel2.revalidate();
        panel2.repaint();
    }


    public void setupScoreGUI(int i1, int i2){

        panel2.setVisible(false);
        panel3.remove(okButton);
        panel3.add(avsluta);
        panel3.setVisible(true);

        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label1.setText("Spelare ett: " + i1 + " poäng  -  Spelare två: " + i2 + " poäng");
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

    public JButton getAvsluta() {
        return avsluta;
    }

    public List<JButton> getKategoriAlternativ() {
        return kategoriAlternativ;
    }

    public List<JButton> getSvarsAlternativ() {
        return svarsAlternativ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("should not be here");
    }
}
