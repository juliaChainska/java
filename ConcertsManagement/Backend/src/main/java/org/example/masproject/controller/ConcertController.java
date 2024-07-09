package org.example.masproject.controller;

import org.example.masproject.dtos.ConcertDTO;
import org.example.masproject.model.Artist;
import org.example.masproject.model.Concert;
import org.example.masproject.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/concerts")
public class ConcertController {
    @Autowired
    private ConcertService concertService;

    @GetMapping("/{id}")
    public ResponseEntity<Concert> getConcertById(@PathVariable Long id) {
        return concertService.getConcertById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/all")
    public List<Concert> getAllConcerts() {
        return concertService.getAllConcertsWithArtists();
    }


    @GetMapping()
    public List<ConcertDTO> getConcerts(){
        return concertService.getConcerts();
    }



    }




