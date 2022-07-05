package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Quiz;
import com.example.vocabularyproject.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/quiz/{vocabularyId}/guess")
    public String guess(@PathVariable(name = "vocabularyId") Integer vocabularyId, Quiz quiz) {
        System.out.println("Szó: " + quiz.getWord2() + " == " + quiz.getWord1());

        if(quizService.checkWords(quiz.getWord1(), quiz.getWord2())) {
            System.out.println("Igen");
        } else {
            System.out.println("Nem");
        }

        String url = "quiz/"+ vocabularyId;
        return "redirect:/" + url;
    }

}
