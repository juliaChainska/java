package org.example.masproject.service;

import org.example.masproject.dtos.ArtistResponseDTO;
import org.example.masproject.dtos.TicketResponseDTO;
import org.example.masproject.model.Artist;
import org.example.masproject.model.Concert;
import org.example.masproject.model.Ticket;
import org.example.masproject.repository.ArtistRepository;
import org.example.masproject.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }


    public List<ArtistResponseDTO> getArtistsByConcertId3(Long concertId) {
        List<Artist> artists = artistRepository.findArtistsByConcertId(concertId);
        List<ArtistResponseDTO> artistResponseDTOList = new ArrayList<>();

        for(Artist a : artists){
            artistResponseDTOList.add(ArtistResponseDTO.builder()
                    .nick(a.getNick())
                    .build());
        }

        return artistResponseDTOList;
    }


}
