package com.softuni.springintroex.repositories;


import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    // List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    @Query("SELECT b FROM Book b where substring(b.releaseDate, 0, 4) not like :year")
    Set<Book> findAllByReleaseDateNotInYear(String year);

    Set<Book> findAllByReleaseDateLessThan(LocalDate date);

    Set<Book> findAllByAuthorLastNameStartingWith(String start);

    @Query("SELECT count(b) FROM Book b WHERE length(b.title) > :length ")
    int getNumberOfBooksWithTitleLength(int length);

    //SELECT a.firstName, a.lastName, count(a.books)
    //FROM author a

    Book findByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateCopies(int copies, LocalDate date);

}
