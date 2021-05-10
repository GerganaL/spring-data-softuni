package com.example.demo.cardealer.domain.repository;

import com.example.demo.cardealer.domain.enitity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository  extends JpaRepository<Supplier, Long> {
}
