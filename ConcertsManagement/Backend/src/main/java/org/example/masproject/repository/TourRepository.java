package org.example.masproject.repository;

import org.example.masproject.model.Concert;
import org.example.masproject.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {



}
