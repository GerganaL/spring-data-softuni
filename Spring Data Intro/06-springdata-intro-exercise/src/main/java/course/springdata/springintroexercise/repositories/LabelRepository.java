package course.springdata.springintroexercise.repositories;


import course.springdata.springintroexercise.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {

    Optional<Label> findByTitle(String s);

}
