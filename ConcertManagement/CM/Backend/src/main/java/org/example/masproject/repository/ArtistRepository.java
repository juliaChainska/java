package org.example.masproject.repository;

import org.example.masproject.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

        @Query("SELECT a FROM Artist a JOIN FETCH a.concerts c WHERE c.id = :concertId")
        List<Artist> findArtistsByConcertId(@Param("concertId") Long concertId);



}
