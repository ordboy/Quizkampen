package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DAO {

    public DAO(){
        initDAO();
    }

	private List<Category> category = new ArrayList<>();

    private void initDAO(){
        category.add(
new Category("Art Questions", Arrays.asList(
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

	private String[] categoryChoice = new String[3];

    public List<Question> getQuestionFromCat(String catName){

        List<Category> category1 = category.stream().filter( cat -> cat.getName().equalsIgnoreCase(catName)).distinct().limit(1).collect(toList()); // distinct unik category name och limit , returnera bara en
        return category1.get(0).getQuestions();
    }

    public String[] getCategories() {
        categoryChoice = category.stream().map(Category::getName).toArray(String[]::new);
		return categoryChoice;
    }

}
