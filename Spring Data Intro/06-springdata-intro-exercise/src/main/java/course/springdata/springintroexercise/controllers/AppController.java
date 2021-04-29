package course.springdata.springintroexercise.controllers;

import course.springdata.springintroexercise.entities.Book;
import course.springdata.springintroexercise.entities.Ingredient;
import course.springdata.springintroexercise.entities.Label;
import course.springdata.springintroexercise.entities.Shampoo;
import course.springdata.springintroexercise.repositories.IngredientRepository;
import course.springdata.springintroexercise.repositories.LabelRepository;
import course.springdata.springintroexercise.repositories.ShampooRepository;
import course.springdata.springintroexercise.services.AuthorService;
import course.springdata.springintroexercise.services.BookService;
import course.springdata.springintroexercise.services.CategoryService;
import course.springdata.springintroexercise.utils.PrintUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static course.springdata.springintroexercise.entities.Size.MEDIUM;


@Controller
public class AppController implements CommandLineRunner {


    private final ShampooRepository shampooRepo;
    private final LabelRepository labelRepo;
    private final IngredientRepository ingredientRepo;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, ShampooRepository shampooRepo, LabelRepository labelRepo, IngredientRepository ingredientRepo) {

        this.shampooRepo = shampooRepo;
        this.labelRepo = labelRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Fetch Shampoos by Size
//        shampooRepo.findBySizeOrderById(MEDIUM).forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");
//
//        // Fetch Shampoos by Size
//        shampooRepo.findBySizeOrLabelOrderByPriceDesc(MEDIUM, labelRepo.findByTitle("Vital").get())
//                .forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");
//
//        // Fetch Shampoos by Price greater than or equal
//        shampooRepo.findByPriceGreaterThanEqual(7)
//                .forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");
//
//        // Fetch Shampoos by Price between min and max
//        shampooRepo.findByPriceBetween(5, 8).forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");

//        // Select Ingredients by Names
//        ingredientRepo.findByNameIn(Set.of("Lavender", "Herbs", "Apple")).forEach(PrintUtil::printIngredient);
//        System.out.println("-".repeat(80) + "\n");

//        // Fetch Shampoos by Price between min and max
//        double maxPrice = 8.50;
//        System.out.printf("Number of shampoos with price less than %5.2f is: %d",
//                maxPrice,
//                shampooRepo.countShampoosByPriceLessThan(maxPrice));

//        // Fetch Shampoos by Ingredients in list
//        shampooRepo.findWithIngredientsIn(Set.of("Berry", "Mineral-Collagen")).forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");

//        // Fetch Shampoos by Ingredients in list
//        shampooRepo.findByCountOfIngredientsLowerThan(3).forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(170) + "\n");

//        // Delete ingredients by name
//        String nameToDelete = "Macadamia Oil";
//        Ingredient ingredientToDelete = ingredientRepo.findByName(nameToDelete).get();
//        List<Shampoo> shampoosByIngredient = shampooRepo.findByIngredient(ingredientToDelete);
//        shampoosByIngredient.forEach(PrintUtil::printShampoo);
//        shampoosByIngredient.forEach(shampoo -> shampoo.getIngredients().remove(ingredientToDelete));
//        System.out.printf("Number of deleted ingredients with name='%s' is: %d%n",
//                nameToDelete, ingredientRepo.deleteAllByName(nameToDelete));
//        System.out.println("-".repeat(180) + "\n");
//        shampooRepo.findAll().forEach(PrintUtil::printShampoo);

      //   Increase price of ingredinets in list by percentage
        ingredientRepo.findAll().forEach(PrintUtil::printIngredient);
        System.out.println("-".repeat(80) + "\n");

        System.out.printf("Number of ingredients updated: %d\n\nAfter update:\n",
                ingredientRepo.updatePriceOfIngredientsInList(Set.of("Lavender", "Herbs", "Apple"), 1.2));

        ingredientRepo.findAll().forEach(PrintUtil::printIngredient);
        System.out.println("-".repeat(80) + "\n");


        // 1.Get all books after the year 2000. Print only their titles.
//        List<Book> books = this.bookService.getAllBooksAfter2000();
//        System.out.println();

        //ex 2
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
//        LocalDate date = LocalDate.parse("01/01/1990",formatter);
//        this.authorService.getAuthorsWithBooksBefore1990(date).forEach(System.out::println);

        //Ex 3
//        this.authorService.findAllAuthorsByCountOfBooks().forEach(a-> {
//            System.out.printf("%s %s %d%n",a.getFirstName(),a.getLastName(),a.getBooks().size());
//        });

        //Ex 4

    }
}
