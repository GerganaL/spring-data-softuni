package com.example.demo.cardealer.domain.repository;

import com.example.demo.cardealer.domain.enitity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
