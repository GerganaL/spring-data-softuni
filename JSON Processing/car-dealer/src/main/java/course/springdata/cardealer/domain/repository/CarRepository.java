package course.springdata.cardealer.domain.repository;

import course.springdata.cardealer.domain.enitity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car,Long> {

    Set<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
}
