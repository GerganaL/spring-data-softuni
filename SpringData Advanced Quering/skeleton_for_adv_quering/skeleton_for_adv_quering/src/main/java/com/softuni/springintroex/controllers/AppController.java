package com.softuni.springintroex.controllers;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;


@Controller
public class AppController implements CommandLineRunner {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
      //  authorService.seedAuthors();
      //  categoryService.seedCategories();
      //  bookService.seedBooks();


//1.	Books Titles by Age Restriction
       // this.bookService.printAllBooksByAgeRestriction(reader.readLine());

//2.	Golden Books
        //this.bookService.printAllBooksByEditionTypeAndCopiesLessThan();

//3.	Books by Price
       // this.bookService.printAllBooksByPriceInBounds();

//4.	Not Released Books
        //this.bookService.printAllBooksByNotInYear(reader.readLine());

//5.	Books Released Before Date
        //this.bookService.printAllBooksBeforeDate(reader.readLine());
//6.	Authors Search
        //this.authorService.printAllAAuthorsEndingWithString(reader.readLine());

//8.	Book Titles Search
        //this.bookService.printAllBooksWithAuthorLastNameStartingWith(reader.readLine());

//9.	Count Books
        //this.bookService.printCountOfBookWithTitleLengthBiggerThan(Integer.parseInt(reader.readLine()));

//10.	Total Book Copies
        //this.authorService.printAllAuthorsByBookCopies();

//11.	Reduced Book
//        BookInfo bookByTitle = this.bookService.findBookByTitle(reader.readLine());
//        System.out.println(bookByTitle.getTitle());
//        System.out.println(bookByTitle.getCopies());
//        System.out.println(bookByTitle.getPrice());

//12.	* Increase Book Copies
        this.bookService.printUpdatedCopiesCount(reader.readLine(),Integer.parseInt(reader.readLine()));

    }
}
