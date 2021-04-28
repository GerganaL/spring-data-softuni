package course.springdata.springintroexercise.repositories;

import course.springdata.springintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findAuthorByCountOfBooks();

    @Query(value = "SELECT authors.id, authors.first_name, authors.last_name\n" +
            "  FROM (SELECT a.id, a.first_name, a.last_name, count(b.id) AS count\n" +
            "        FROM authors AS a\n" +
            "          INNER JOIN books AS b\n" +
            "            ON a.id = b.author_id\n" +
            "           AND b.release_date < :date\n" +
            "        GROUP BY a.id, a.first_name, a.last_name\n" +
            "       HAVING count(b.id) > 0) AS authors\n" +
            "ORDER BY authors.count DESC;\n", nativeQuery = true )
    List<Author> findAllByReleaseDateAfter(LocalDate date);
}
