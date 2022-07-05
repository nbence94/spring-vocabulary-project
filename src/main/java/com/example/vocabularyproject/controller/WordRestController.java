package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordRestController {

    @Autowired
    WordService wordService;

    @GetMapping("/words")
    public List<Words> getAllWords() {
        return wordService.getWords();
    }

    @PostMapping("/word/add")
    public String addWord(@RequestBody Words word) {
        return wordService.addWord(word);
    }

    @PutMapping("/word/{id}/update")
    public String updateWord(@RequestBody Words word) {
        return wordService.updateWord(word);
    }

    @DeleteMapping("/word/{id}/delete")
    public String deleteWord(@PathVariable Integer id) {
        return wordService.deleteWord(id);
    }


}
