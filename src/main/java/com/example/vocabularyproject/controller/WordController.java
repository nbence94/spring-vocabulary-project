package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class WordController {

    @Autowired
    WordService wordService;

    @PostMapping("/vocabulary/{vocabularyId}/add")
    public String addWord(@ModelAttribute Words word, @PathVariable(name = "vocabularyId") Integer vocabularyId) {
        word.setVocabularyId(vocabularyId);
        wordService.addWord(word);

        String url = "vocabulary/" + vocabularyId;
        return "redirect:/" + url;
    }

    @RequestMapping(value="/vocabulary/{vocabularyId}/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteWord(Integer id,@PathVariable(name = "vocabularyId") Integer vocabularyId) {
        wordService.deleteWord(id);

        String url = "vocabulary/" + vocabularyId;
        return "redirect:/" + url;
    }

    @GetMapping("/vocabulary/{vocabularyId}/select")
    @ResponseBody
    public Optional<Words> getAWord(Integer id) {
        return wordService.getWord(id);
    }

    @RequestMapping(value = "/vocabulary/{vocabularyId}/update", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET})
    public String editHero(Words word, @PathVariable(name ="vocabularyId") Integer vocabularyId) {
        wordService.updateWord(word);

        String url = "vocabulary/" + vocabularyId;
        return "redirect:/" + url;
    }


}
