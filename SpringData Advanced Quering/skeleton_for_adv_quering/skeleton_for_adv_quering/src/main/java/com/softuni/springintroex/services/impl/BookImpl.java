package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookImpl implements BookService {
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    public BookImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = this.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], dateTimeFormatter);
                    int copies = Integer.parseInt(params[2]);
                    BigDecimal price = new BigDecimal(params[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
                    String title = this.getTitle(params);
                    Set<Category> categorySet = this.getRandomCategory();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categorySet);
                    System.out.println();
                    bookRepository.saveAndFlush(book);
                });
    }

    @Override
    public void printAllBooksByAgeRestriction(String ageRestriction) {
        this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase())).forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllBooksByEditionTypeAndCopiesLessThan() {
        this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD,5000).forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllBooksByPriceInBounds() {
        this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                .forEach(b -> System.out.printf("%s - $%s%n",b.getTitle(),b.getPrice()));
    }

    @Override
    public void printAllBooksByNotInYear(String year) {
        this.bookRepository.findAllByReleaseDateNotInYear(year).forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllBooksBeforeDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date,dtf);

        this.bookRepository.findAllByReleaseDateLessThan(localDate).forEach(b -> System.out.printf("%s %s %s%n",b.getTitle(),b.getEditionType(),b.getPrice()));
    }

    @Override
    public void printAllBooksWithAuthorLastNameStartingWith(String start) {
        this.bookRepository.findAllByAuthorLastNameStartingWith(start).forEach(b -> System.out.printf("%s (%s %s)%n",b.getTitle(),b.getAuthor().getFirstName(),b.getAuthor().getLastName()));
    }

    @Override
    public void printCountOfBookWithTitleLengthBiggerThan(int length) {
        System.out.println(this.bookRepository.getNumberOfBooksWithTitleLength(length));
    }

    @Override
    public BookInfo findBookByTitle(String title) {
        Book byTitle = this.bookRepository.findByTitle(title);
        BookInfo bookInfo = new BookInfo(byTitle.getTitle(),byTitle.getPrice(),byTitle.getCopies());
        return bookInfo;
    }

    @Override
    public void printUpdatedCopiesCount(String date, int copies) {
        DateTimeFormatter dfs = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate localDate = LocalDate.parse(date,dfs);
        int updatedRows = this.bookRepository.updateCopies(copies, localDate);

        System.out.println(updatedRows * copies);
    }


    private Set<Category> getRandomCategory() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < params.length; i++) {
            sb.append(params[i]).append(" ");
        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }
}
