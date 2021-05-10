package com.example.demo.cardealer.domain.repository;

import com.example.demo.cardealer.domain.enitity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
