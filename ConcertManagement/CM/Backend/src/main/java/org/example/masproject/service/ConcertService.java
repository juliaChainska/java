package org.example.masproject.service;

import org.example.masproject.dtos.ArtistDTO;
import org.example.masproject.dtos.ConcertDTO;
import org.example.masproject.model.Artist;
import org.example.masproject.model.Concert;
import org.example.masproject.model.Ticket;
import org.example.masproject.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    public Optional<Concert> getConcertById(Long id) {
        return concertRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Concert> getAllConcertsWithArtists() {
        return concertRepository.findAllWithArtists();
    }

    public List<ConcertDTO> getConcerts() {
        List<Concert> concerts = concertRepository.findAllWithArtists();
        List<ConcertDTO> concertDTOList = new ArrayList<>();

        for (Concert c : concerts) {
            List<ArtistDTO> artistDTOList = new ArrayList<>();

            for (Artist a : c.getArtists()) {
                artistDTOList.add(new ArtistDTO(a.getNick(), a.getTypes()));
            }

            concertDTOList.add(ConcertDTO.builder()
                    .title(c.getTitle())
                    .date(c.getDate())
                    .location(c.getLocation())
                    .artists(artistDTOList)
                    .id(c.getId())
                    .price(c.getPrice())
                    .build());
        }

        return concertDTOList;
    }

}
