package course.springdata.springintroexercise.controllers;

import course.springdata.springintroexercise.entities.Book;
import course.springdata.springintroexercise.services.AuthorService;
import course.springdata.springintroexercise.services.BookService;
import course.springdata.springintroexercise.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

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
        List<Book> allBooksByAuthor = this.bookService.getAllBooksByAuthor();
        System.out.println();
    }
}
