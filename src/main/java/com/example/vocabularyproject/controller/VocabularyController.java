package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Vocabularies;
import com.example.vocabularyproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VocabularyController {

    @Autowired
    VocabularyService vocabularyService;

    @RequestMapping("/index")
    public void getVocabularies(Model model) {
        model.addAttribute("vocabulariesList", vocabularyService.getAllVocabularies());
        model.addAttribute("vocabulary", new Vocabularies());
    }

    @PostMapping("/index/create")
    public String createVocabulary(@ModelAttribute Vocabularies vocabularies) {
        vocabularyService.createVocabulary(vocabularies);
        return "redirect:/index";
    }

    @RequestMapping(value="/index/delete", method = {RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET})
    public String deleteVocabulary(Integer id) {
        vocabularyService.deleteVocabulary(id);
        return "redirect:/index";
    }

}
