package course.springdata.springintroexercise.services.impl;

import course.springdata.springintroexercise.constants.GlobalConstants;
import course.springdata.springintroexercise.entities.Author;
import course.springdata.springintroexercise.repositories.AuthorRepository;
import course.springdata.springintroexercise.services.AuthorService;
import course.springdata.springintroexercise.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
