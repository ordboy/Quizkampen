/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author sarko
 */
import client.GUI;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class GUIQuestion extends JPanel implements ActionListener{
	int correctAns;
	GUI quiz;	
	int selected;
	boolean used;
	//frågor
	JPanel qPanel=new JPanel();
	//svar
	JPanel aPanel=new JPanel();
	JButton[] responses;
	ButtonGroup group=new ButtonGroup();
	//bottom
	JPanel botPanel=new JPanel();
//	JButton next=new JButton("Next");
	JButton finish=new JButton("Finish");
	
	/*public static void main(String args[]){
		JFrame frame=new JFrame("RadioButton Test");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		String[] answers={"wrong1","right","wrong2"};
		frame.add(new GUIQuestion("vilken är rätt?",answers,1));
		
		frame.setVisible(true);
	}*/
	
	public GUIQuestion(String q, String[] options, int ans, GUI quiz){
		this.quiz=quiz;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		correctAns=ans;
		//frågor
		qPanel.add(new JLabel(q));
		add(qPanel);
		//svar
		responses=new JButton[options.length];
		for(int i=0;i<options.length;i++){
			responses[i]=new JButton(options[i]);
			responses[i].addActionListener(this);
			group.add(responses[i]);
			aPanel.add(responses[i]);
                       
		}
		add(aPanel);
		//bottom
//		next.addActionListener(this);
		finish.addActionListener(this);
//		botPanel.add(next);
		botPanel.add(finish);
		add(botPanel);
	
}
	
        @Override
	public void actionPerformed(ActionEvent e){
            JButton tmp;
                tmp = (JButton) e.getSource();
                if(tmp.equals(tmp)){
                    tmp.setBackground(Color.green);
                }
//                else{
//                    tmp.setBackground(Color.red);
                    
                tmp.setOpaque(true);
                this.repaint();
                this.revalidate();
		Object src=e.getSource();
		//option & next button
		if(!src.equals(tmp)){
		} else {
                    showResult();
                    if(selected==correctAns){
                        used=true;
                        quiz.next();
                    }
            }
		//finish button
		if(src.equals(finish)){
			quiz.showSummary();
		}
		//through buttons
		for(int i=0;i<responses.length;i++){
			if(src==responses[i]){
				selected=i;
			}
		}
	}
	
	public void showResult(){
		String text=responses[selected].getText();
		quiz.total++;
		if(selected==correctAns){
			JOptionPane.showMessageDialog(null,text+" är rätt!\nGood Job!");
		}else{
			quiz.wrongs++;
			JOptionPane.showMessageDialog(null,text+" är fel.\nFörsök igen!");
		}
	}
}