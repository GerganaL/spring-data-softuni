package course.springdata.cardealer.domain.repository;

import course.springdata.cardealer.domain.enitity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository  extends JpaRepository<Supplier, Long> {
}
