package course.springdata.cardealer.domain.repository;

import course.springdata.cardealer.domain.enitity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Set<Customer> getAllByOrderByBirthDateAscYoungDriverAsc();
}
