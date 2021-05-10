package com.example.demo.cardealer.domain.repository;


import com.example.demo.cardealer.domain.enitity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car,Long> {

    Set<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);


}
