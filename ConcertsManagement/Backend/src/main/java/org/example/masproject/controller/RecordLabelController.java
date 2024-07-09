package org.example.masproject.controller;

import org.example.masproject.model.Artist;
import org.example.masproject.model.ArtistInRecordLabel;
import org.example.masproject.model.RecordLabel;
import org.example.masproject.service.RecordLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/record-labels")
public class RecordLabelController {
    @Autowired
    private RecordLabelService recordLabelService;

    @GetMapping
    public List<RecordLabel> getAllRecordLabels() {
        return recordLabelService.getAllRecordLabels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordLabel> getRecordLabelById(@PathVariable Long id) {
        RecordLabel recordLabel = recordLabelService.getRecordLabelById(id);
        if (recordLabel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recordLabel);
    }

    @GetMapping("/{id}/artists")
    public ResponseEntity<Set<Artist>> getArtistsByRecordLabel(@PathVariable Long id) {
        RecordLabel recordLabel = recordLabelService.getRecordLabelById(id);
        if (recordLabel == null) {
            return ResponseEntity.notFound().build();
        }
        Set<Artist> artists = recordLabel.getAgreements().stream()
                .map(ArtistInRecordLabel::getArtist)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(artists);
    }
}
