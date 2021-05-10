package com.example.demo.cardealer.domain.repository;


import com.example.demo.cardealer.domain.enitity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Set<Customer> getAllByOrderByBirthDateAscYoungDriverAsc();

    @Query("SELECT c from Customer c ORDER BY c.birthDate, c.youngDriver DESC ")
    Set<Customer> findAllAndSort();
}
