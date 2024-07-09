package org.example.masproject.repository;

import org.example.masproject.model.Artist;
import org.example.masproject.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ConcertRepository extends JpaRepository <Concert, Long> {

    @Query("SELECT c FROM Concert c " +
            "JOIN FETCH c.artists a " +
            "JOIN a.types at")
    List<Concert> findAllWithArtists();



}
