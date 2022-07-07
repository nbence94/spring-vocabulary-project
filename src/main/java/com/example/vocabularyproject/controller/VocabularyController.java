package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Quiz;
import com.example.vocabularyproject.model.Vocabularies;
import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.service.QuizService;
import com.example.vocabularyproject.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
public class VocabularyController {

    @Autowired
    VocabularyService vocabularyService;

    @Autowired
    QuizService quizService;

    @RequestMapping("/index")
    public String getVocabularies(Model model) {
        model.addAttribute("vocabulariesList", vocabularyService.getAllVocabularies());
        model.addAttribute("vocabulary", new Vocabularies());
        return "index";
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

    @RequestMapping("/quiz/{id}")
    public ModelAndView openQuiz(@PathVariable(name = "id") Integer vocabularyId) {
        ModelAndView mav = new ModelAndView("quiz");

        mav.addObject("vocId", vocabularyId);
        mav.addObject("randomWord", quizService.getRandomEnglishWord(vocabularyId));

        Quiz quiz = new Quiz();
        Random rnd = new Random();
        int random =  rnd.nextInt(50 - 1) + 1;

        if(random % 2 == 0) {
            quiz.setWord1(quizService.getRandomEnglishWord(vocabularyId));
        } else {
            quiz.setWord1(quizService.getRandomHungarianWord(vocabularyId));
        }

        mav.addObject("quizWord", quiz);

        return mav;
    }

}
