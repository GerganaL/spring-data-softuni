package couurse.springdata.dao;

import couurse.springdata.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label,Long> {
}
