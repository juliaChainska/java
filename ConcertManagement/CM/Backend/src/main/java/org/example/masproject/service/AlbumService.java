package org.example.masproject.service;

import org.example.masproject.model.Album;
import org.example.masproject.repository.AlbumRepository;
import org.example.masproject.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAlbumsByArtistId(Long artistId) {
        return albumRepository.findByArtistId(artistId);
    }


}
