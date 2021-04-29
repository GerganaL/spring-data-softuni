package com.softuni.springintroex.services;





import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

public interface BookService {

    void seedBooks() throws IOException;
    void printAllBooksByAgeRestriction(String ageRestriction);
    void printAllBooksByEditionTypeAndCopiesLessThan();
    void printAllBooksByPriceInBounds();
    void printAllBooksByNotInYear(String year);
    void printAllBooksBeforeDate(String date);
    void printAllBooksWithAuthorLastNameStartingWith(String start);
    void printCountOfBookWithTitleLengthBiggerThan(int length);
    BookInfo findBookByTitle(String title);
    void printUpdatedCopiesCount(String date, int copies);
}
