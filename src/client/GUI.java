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
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.util.Random;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

public class GUI extends JFrame{
	JPanel p=new JPanel();
	CardLayout cards=new CardLayout();
	int numQs;
	int wrongs=0;
	int total=0;
	
	String[][] answers={
		{"Portugisiska", "spanska", "Italienska"," English"},
		{"24","32","36","64"},
		{"1935","1945","1955","1965"},
		{"Jorden"," Jupiter "," Mars "," Uranus"},
		{"Thomas Edison","Louis Pasteur","Alexander Fleming","George Orwell"},
		{"Sann","Falsk"},
		{"Sann","Falsk"},
		{"Brand","mörker","vind"," Spindlar"},
		{"Volleyball","Basketball","Hockey","Fotball"},
		{"Italien ","Spanien","France"," Schweiz"},
		{"Sann","Falsk"},
		{"Apollo 7","Apollo 9","Apollo 11","Apollo 13"},
		{"Slovakien","Ukraina", "Ungern"," Ryssland"},
		{"Sann","Falsk"},
		{"Britney Spears","Scarlett Johansson","Mila Kunis","Angelina Jolie"},
		{"Steve Rogers","Chris Hemsworth","Tony Stark","Toby Jenkins"},
		{"Labrosse","Orleans","Sandillon","Attray"},
	};
	
	GUIQuestion questions[]={
		
		new GUIQuestion(
			"Vilket språk talas i Brasilien?",
			answers[0],
			0,this
		),
		new GUIQuestion(
			"Hur många svarta rutor finns på ett schackbräde?",
			answers[1],
			1,this
		),
		new GUIQuestion(
			"Vilket år dog Albert Einstein??",
			answers[2],
			2,this
		),
		new GUIQuestion(
			"Vilket är den största planet i solsystemet?",
			answers[3],
			1,this
		),
		new GUIQuestion(
			"Vem uppfann Penicillin?",
			answers[4],
			2,this
		),
		new GUIQuestion(
			"Sant om falskt: Volleyboll uppfanns som ett spel för affärsmän.",
			answers[5],
			0,this
		),
		new GUIQuestion(
			"Sant om falskt: Bara amerikaner och sovjeter har gått på månen.",
			answers[6],
			1,this
		),
		new GUIQuestion(
			"Anemofobi är rädslan för vad?",
			answers[7],
			2,this
		),
		new GUIQuestion(
			"Vilket spel spelas med fem spelare på vardera sidan?",
			answers[8],
			1,this
		),
		new GUIQuestion(
			"Vilket av följande länder är inlands?",
			answers[9],
			3,this
		),
		new GUIQuestion(
			"Sant eller falskt: Den Oscar-vinnande filmen A Beautiful Mind (2001) handlar om schizofreni.",
			answers[10],
			0,this
		),
		new GUIQuestion(
			"Vilket Apollo-uppdrag landade de första människorna på månen?",
			answers[11],
			2,this
		),
		new GUIQuestion(
			"Reaktorn på platsen för kärnkraftsolyckan i Tjernobyl är nu i vilket land?",
			answers[12],
			1,this
		),
		new GUIQuestion(
			"Sant eller falskt: Tangonen härstammar i Argentina.",
			answers[13],
			0,this
		),
		new GUIQuestion(
			"Vem spelar Lara Croft i Tomb Raider-serien av filmer?",
			answers[14],
			3,this
		),
		new GUIQuestion(
			"Vad är Iron Mans riktiga namn?",
			answers[15],
			2,this
		)
		,
		new GUIQuestion(
			"Joan of Arc är också känd som Maid of Where?",
			answers[16],
			1,this
		)
	};

	public static void main(String args[]){
        GUI quiz = new GUI();
	}
	
	public GUI(){
		super("Corevia");
		setResizable(true);
		setSize(650,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p.setLayout(cards);
		numQs=questions.length;
		for(int i=0;i<numQs;i++){
			p.add(questions[i],"q"+i);
		}
		Random r=new Random();
		int i=r.nextInt(numQs);
		cards.show(p,"q"+i);
		add(p);
		setVisible(true);
	}
	
	public void next(){
		if((total-wrongs)==numQs){
			showSummary();
		}else{
			Random r=new Random();
			boolean found=false;
			int i=0;
			while(!found){
				i=r.nextInt(numQs);
				if(!questions[i].used){
					found=true;
				}
			}
			cards.show(p,"q"+i);
		}
	}
	
	public void showSummary(){
		JOptionPane.showMessageDialog(null,"Här är din sammanfattning:"+
			"\nYou answered "+wrongs+ " fel svar" +
			"\nYou answered "+(total-wrongs)+ " rätt" +
			"\nGiving a correct answer chance: \t\t"+(int)(((float)(total-wrongs)/total)*100)+"%"
		);
		System.exit(0);
	}
}
