package course.springdata.springintroexercise.services;


import course.springdata.springintroexercise.entities.Author;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(Long id);
    List<Author> findAllAuthorsByCountOfBooks();
    List<String> getAuthorsWithBooksBefore1990(LocalDate date);
}
