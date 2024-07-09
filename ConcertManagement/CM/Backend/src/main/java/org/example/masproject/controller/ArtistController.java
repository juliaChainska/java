package org.example.masproject.controller;

import org.example.masproject.dtos.ArtistResponseDTO;
import org.example.masproject.model.Artist;
import org.example.masproject.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;


    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        return artistService.getArtistById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


//    //2 metoda
//    @GetMapping("/{concertId}/artists")
//    public List<Artist> getArtistsByConcertId2(@PathVariable Long concertId) {
//        return artistService.getArtistsByConcertId(concertId);
//    }


    @GetMapping("/{concertId}/artists")
    public List<ArtistResponseDTO> getArtistsByConcertId2(@PathVariable Long concertId) {
        return artistService.getArtistsByConcertId3(concertId);

    }


}
