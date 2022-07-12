package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Vocabularies;
import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VocabularyController {

    @Autowired
    VocabularyService vocabularyService;

    @Autowired
    QuizController quiz;

    @Autowired
    SentenceController sentence;

    @RequestMapping("/index")
    public String getVocabularies(Model model) {
        model.addAttribute("vocabulariesList", vocabularyService.getAllVocabularies());
        model.addAttribute("vocabulary", new Vocabularies());

        //If we quit the quiz, set these to 0
        quiz.setScore(0);
        quiz.setTurn(0);
        quiz.setMsgType(-1);

        sentence.setMsgType(-1);

        return "index";
    }

    @PostMapping("/index/create")
    public String createVocabulary(@ModelAttribute Vocabularies vocabularies) {
        vocabularyService.createVocabulary(vocabularies);
        return "redirect:/index";
    }

    @RequestMapping(value = "/index/delete", method = {RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.GET})
    public String deleteVocabulary(Integer id) {
        vocabularyService.deleteVocabulary(id);
        return "redirect:/index";
    }

    @RequestMapping("/vocabulary/{id}")
    public ModelAndView openVocabulary(@PathVariable(name = "id") Integer vocabularyId) {
        ModelAndView mav = new ModelAndView("vocabulary");

        //Give the Vocabluary's words
        List<Words> wordList = vocabularyService.getWordsByVocabulary(vocabularyId);
        mav.addObject("listOfWords", wordList);

        //The word's modell needed
        mav.addObject("word", new Words());

        //Vocabulary's id also needed
        mav.addObject("vocId", vocabularyId);

        return mav;
    }

}
