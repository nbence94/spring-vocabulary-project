package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Vocabularies;
import com.example.vocabularyproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VocabularyController {

    @Autowired
    VocabularyService vocabularyService;

    @PostMapping("/vocabulary/create")
    public String createVocabulary(@RequestBody Vocabularies vocabulary) {
        return vocabularyService.createVocabulary(vocabulary);
    }

    @PutMapping("/vocabulary/{id}/update")
    public String updateVocabulary(@RequestBody Vocabularies vocabulary) {
        return vocabularyService.updateVocabulary(vocabulary);
    }

    @DeleteMapping("/vocabulary/{id}/delete")
    public String deleteVocabulary(@PathVariable Integer id) {
        return vocabularyService.deleteVocabulary(id);
    }

    @RequestMapping("/vocabularies")
    public List<Vocabularies> getVocabularies() {
        return vocabularyService.getAllVocabularies();
    }

}
