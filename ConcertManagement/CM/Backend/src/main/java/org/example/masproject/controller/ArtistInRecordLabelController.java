package org.example.masproject.controller;

import org.example.masproject.model.ArtistInRecordLabel;
import org.example.masproject.service.ArtistInRecordLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artist-in-record-labels")
public class ArtistInRecordLabelController {

    @Autowired
    private ArtistInRecordLabelService artistInRecordLabelService;

    @GetMapping
    public List<ArtistInRecordLabel> getAllArtistInRecordLabels() {
        return artistInRecordLabelService.getAllArtistInRecordLabels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistInRecordLabel> getArtistInRecordLabelById(@PathVariable Long id) {
        ArtistInRecordLabel artistInRecordLabel = artistInRecordLabelService.getArtistInRecordLabelById(id);
        if (artistInRecordLabel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistInRecordLabel);
    }

}
