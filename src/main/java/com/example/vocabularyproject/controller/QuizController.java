package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Message;
import com.example.vocabularyproject.model.Quiz;
import com.example.vocabularyproject.service.QuizService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@Setter
public class QuizController {

    @Autowired
    QuizService quizService;

    int score = 0;
    int turn = 0;

    int mistake = -1;
    String mistakeMessage = "Helytelen!";

    @RequestMapping("/quiz/{id}")
    public ModelAndView openQuiz(@PathVariable(name = "id") Integer vocabularyId, HttpSession session) {
        ModelAndView mav = new ModelAndView("quiz");

        mav.addObject("vocId", vocabularyId);

        //Random szó kiválasztása
        Quiz quiz = new Quiz();
        Random rnd = new Random();
        int random =  rnd.nextInt(50 - 1) + 1;

        if(random % 2 == 0) {
            quiz.setWord1(quizService.getRandomEnglishWord(vocabularyId));
        } else {
            quiz.setWord1(quizService.getRandomHungarianWord(vocabularyId));
        }
        mav.addObject("quizWord", quiz);

        //Statisztika kiírása (Összes szó és elért pontok)
        mav.addObject("score", score);
        mav.addObject("turn", turn);

        //Hibaüzenet megjelenítése
        if(mistake == 1) session.setAttribute("message", new Message(mistakeMessage, "danger"));
        else if(mistake == 0) session.setAttribute("message", new Message("Helyes!","success"));

        return mav;
    }

    @PostMapping("/quiz/{vocabularyId}/guess")
    public String guess(@PathVariable(name = "vocabularyId") Integer vocabularyId, Quiz quiz) {

        if(quizService.checkWords(quiz.getWord1().trim(), quiz.getWord2().trim())) {
            score++;
            mistake = 0;
        } else {
            mistake = 1;
            mistakeMessage = "Nem jó! Helyesen: " + quizService.wordPair(quiz.getWord1().trim());
        }
        turn++;

        String url = "quiz/"+ vocabularyId;
        return "redirect:/" + url;
    }

}
