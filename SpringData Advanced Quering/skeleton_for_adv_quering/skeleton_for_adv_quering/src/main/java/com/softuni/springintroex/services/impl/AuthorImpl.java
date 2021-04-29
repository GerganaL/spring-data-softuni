package com.softuni.springintroex.services.impl;


import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthorImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    public AuthorImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {

        if(this.authorRepository.count() != 0){
            return;
        }
        String [] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHOR_FILE_PATH);

        Arrays.stream(fileContent).forEach(r -> {
            String[] params = r.split("\\s+");
            Author author = new Author(params[0],params[1]);
            this.authorRepository.saveAndFlush(author);
        });
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return authorRepository.findAuthorByCountOfBooks();
    }

    @Override
    public List<String> getAuthorsWithBooksBefore1990(LocalDate date) {
        return this.authorRepository
                .findAllByReleaseDateAfter(date)
                .stream()
                .map(author -> String.format("%s %s", author.getFirstName(), author.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public void printAllAAuthorsEndingWithString(String end) {
        this.authorRepository.findAllByFirstNameEndingWith(end).forEach(author -> System.out.printf("%s %s%n", author.getFirstName(), author.getLastName()));
    }

    @Override
    public void printAllAuthorsByBookCopies() {
        List<Author> authors = this.authorRepository.findAll();
        Map<String, Integer> authorCopies = new HashMap<>();
        authors.forEach(a -> {
         int copies = a.getBooks().stream().mapToInt(Book::getCopies).sum();
         authorCopies.put(a.getFirstName() + " " + a.getLastName(),copies);
        });

//        Map.Entry<String, Integer> stringIntegerEntry = authorCopies.entrySet().stream().min((a, b) -> b.getValue().compareTo(a.getValue())).orElseThrow();
//        System.out.println(stringIntegerEntry.getValue());

        authorCopies.entrySet().stream()
                .sorted((a,b) -> Integer.compare(b.getValue(),a.getValue()))
                .forEach(a -> System.out.printf("%s %d%n",a.getKey(),a.getValue()));
    }
}
