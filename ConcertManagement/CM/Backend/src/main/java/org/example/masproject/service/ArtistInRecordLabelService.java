package org.example.masproject.service;

import org.example.masproject.model.ArtistInRecordLabel;
import org.example.masproject.repository.ArtistInRecordLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistInRecordLabelService {
    @Autowired
    private ArtistInRecordLabelRepository artistInRecordLabelRepository;
    public List<ArtistInRecordLabel> getAllArtistInRecordLabels() {
        return artistInRecordLabelRepository.findAll();
    }

    public ArtistInRecordLabel getArtistInRecordLabelById(Long id) {
        return artistInRecordLabelRepository.findById(id).orElse(null);
    }

}
