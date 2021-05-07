package course.springdata.cardealer.domain.repository;

import course.springdata.cardealer.domain.enitity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
