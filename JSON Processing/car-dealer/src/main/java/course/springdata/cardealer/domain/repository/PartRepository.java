package course.springdata.cardealer.domain.repository;

import course.springdata.cardealer.domain.enitity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
