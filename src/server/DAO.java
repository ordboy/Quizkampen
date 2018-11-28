package server;
//dsf
import model.Category;
import model.Question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DAO {
        //Lista av fråga som är dynamisk man kan lägga till flera frågor och kategorier och GUIn ska fungera
    public DAO(){
        initDAO();
    }

	private List<Category> category = new ArrayList<>();

    private void initDAO(){
        category.add( //skapar kategorier
                new Category("Art Questions", Arrays.asList( //skapar en arraylista av frågan och svaralt samt rätta svars alt
                        new Question("Under vilket årtionde dog Picasso?", "1910", "1930","1960","1973", 4),
                        new Question("Vilket år föddes den svenska konstnären Lars-Göran Abrahamsson?", "1720", "1950","1855","1965", 3),
                        new Question("Vilket år föddes konstnären Ludwig van Beethoven?", "1671", "1770 ", "1920", "1891", 2),
                        new Question("Vilket år föddes konstnären Ludwig van Beethoven?", "1678", "1770 ", "1920", "1891", 1)
                        ))
        );
        category.add(
                new Category("Car Questions", Arrays.asList(
                        new Question("Vilket land kommer bilmärket Mazda från?", "Tyskland", "China","Sydkorea","Japan", 4),
                        new Question("Vilket land kommer bilmärket Mercedes från?", "Tjeckien", "Polen","Tyskland","Grekland", 3),
                        new Question("Du ökar hastigheten från 30 km/h till 90 km/h. Hur påverkas bromssträckan?", "Den blir 9 gånger längre", "Den blir 3 gånger längre.", "Den blir 2 gånger längre.", "Den förblir oförändrad.", 2),
                        new Question("När gick vi över till högertrafik?", "1967", "1947", "1900", "1000", 1)
                ))
        );
        category.add(
                new Category("History Questions", Arrays.asList(
                        new Question("Vilket år bröt den ryska revolutionen ut?", "1922", "1730","1860","1910", 1),
                        new Question("Vilket år avslutades det Amerikanska inbördeskriget?", "1925", "1865","1868","1970", 2),
                        new Question("Vilket år dog Albert Einstein?", "1935","1945","1955","1965", 3),
                        new Question("Vilket Apollo-uppdrag landade de första människorna på månen?", "Apollo 7","Apollo 9","Apollo 12","Apollo 11", 4)
                ))
        );
        category.add(
                new Category("Geography Questions", Arrays.asList(
                        new Question("I vilket land ligger staden Aten i?", "Grekland", "Frankrike","USA","Turkiet", 1),
                        new Question("Vilket land är världens minsta land?", "Vatikanstaten", "Nord-Korea","Malta","Maldiverna", 1),
                        new Question("Vad hette staden som var tänkt att vara Sveriges reservhuvudstad?", "Göteborg", "Karlsborg", "Lindsberg", "Kirona", 2),
                        new Question("Vad heter Italiens största och mest aktiva vulkan?", "Pantelleria", "Etna", "Vesuvius", "Italian", 4)

               
                
                ))
        );
    }

	//private String[] categoryChoice = new String[3];

    public List<Question> getQuestionFromCat(String catName){
        // hämtar kategoriera -> filterar ut den valda -> distinct ser till att kategorin som hämtas har ett unik category name -> limit ser till att bara en returneras
        List<Category> category1 = category.stream().filter( cat -> cat.getName().equalsIgnoreCase(catName)).distinct().limit(1).collect(toList());

        return category1.get(0).getQuestions();
    }

   // public String[] getCategories() { // gör en array av de olika kategorierna
   //     categoryChoice = category.stream().map(Category::getName).toArray(String[]::new);
	//	return categoryChoice;
    //}

}
