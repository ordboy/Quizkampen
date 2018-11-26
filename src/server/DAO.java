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
                new Category("Kategori 1", Arrays.asList(
                        new Question("Fråga 1?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 1),
                        new Question("Fråga 2?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 2),
                        new Question("Fråga 3?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 3),
                        new Question("Fråga 4?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 4)
                        ))
        );
        category.add(
                new Category("Kategori 2", Arrays.asList(
                        new Question("Fråga 1?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 1),
                        new Question("Fråga 2?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 2),
                        new Question("Fråga 3?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 3),
                        new Question("Fråga 4?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 4)
                ))
        );
        category.add(
                new Category("Kategori 3", Arrays.asList(
                        new Question("Fråga 1?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 1),
                        new Question("Fråga 2?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 2),
                        new Question("Fråga 3?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 3),
                        new Question("Fråga 4?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 4)
                ))
        );
        category.add(
                new Category("Kategori 4", Arrays.asList(
                        new Question("Fråga 1?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 1),
                        new Question("Fråga 2?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 2),
                        new Question("Fråga 3?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 3),
                        new Question("Fråga 4?", "Svar 1", "Svar 2", "Svar 3", "Svar 4", 4)
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
