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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class newframe extends JFrame implements ActionListener{

public static void main(String[] args) {
newframe frameTabel = new newframe();
}
        
        JPanel p ;
        JLabel welcome;
        JPanel panel;
        JButton val1, val2 ,val3 ,val4;

        newframe(){

        super("Option" );
        String usernamn;
        
        p = new JPanel();

        welcome = new JLabel("Welcome " 
                                + System.getProperty("user.name").toUpperCase()
                                + " to QUIZKAMPEN "+ "\nvälja ett alternativ nedan");

        val1 = new JButton(" Fysik "); 
        val2 = new JButton("Matte ");
        val3 = new JButton(" Språk");
        val4 = new JButton(" aa");
           
        panel = new JPanel();
        panel.add(welcome);
        panel.add(val1); 
        panel.add(val2);
        panel.add(val3);
        panel.add(val4);
         
        p.setLayout(new BorderLayout());
        p.add(panel, BorderLayout.CENTER);

         val1.addActionListener((ActionListener) this);
         val2.addActionListener((ActionListener) this);
         val3.addActionListener((ActionListener) this);
         val4.addActionListener((ActionListener) this);

     
       
        pack(); 
        add(p);
        setSize(400,100);
        setLocation(500,280);

        
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
}
public void actionPerformed(ActionEvent ee){
    
          if (ee.getSource()==val1){
              Fysik1 f= new Fysik1();
          
          }

}
}
