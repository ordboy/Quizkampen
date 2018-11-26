/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.newGUI;

/**
 *
 * @author sarko
 */
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Fysik1 extends JFrame implements ActionListener{
    
    int correctAns;
    int answer;
    boolean option;
    int button = 4;
    JPanel panel;
//    JButton [] allaButton = new JButton [4];
    
      JFrame frame = new JFrame();
      JLabel label;
      JPanel p = new JPanel();
      JButton button1, button2 ,button3 ,button4;
//     TextField text = new TextField("Namn: ", 20);
     
public Fysik1() {
    
       super("Fysik");


       

       panel = new JPanel();
       button1 = new JButton(" "); 
       button2 = new JButton(" ");
       button3 = new JButton(" ");
       button4 = new JButton(" ");
       
       
         
         p.setLayout(new BorderLayout());
         p.add(panel, BorderLayout.SOUTH);
      
         panel.add(button2); 
         panel.add(button1);
         panel.add(button4);
         panel.add(button3);
         

     
     
         button2.addActionListener(this);
         button3.addActionListener(this);
         button1.addActionListener(this);
         button4.addActionListener(this);
         
   
         add(label);
         add(p); 
         pack(); 
         setVisible(true);
         setSize(500,200);
         setLocation(430, 300);
         setDefaultCloseOperation(EXIT_ON_CLOSE); 
    }
     @Override
    public void actionPerformed(ActionEvent e){
        
      
    }
}
