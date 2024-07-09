package org.example.masproject.service;

import org.example.masproject.model.RecordLabel;
import org.example.masproject.repository.RecordLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordLabelService {
    @Autowired
    private RecordLabelRepository recordLabelRepository;

    public List<RecordLabel> getAllRecordLabels() {
        return recordLabelRepository.findAll();
    }

    public RecordLabel getRecordLabelById(Long id) {
        return recordLabelRepository.findById(id).orElse(null);
    }







}
