package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Message;
import com.example.vocabularyproject.model.Quiz;
import com.example.vocabularyproject.service.QuizService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    int msgType = -1;
    // -1:none, 0:success, 1:mistake, 2:info
    String alertMessage = "Helytelen!";
    String hintWord;

    @RequestMapping("/quiz/{id}")
    public ModelAndView openQuiz(@PathVariable(name = "id") Integer vocabularyId, HttpSession session) {
        ModelAndView mav = new ModelAndView("quiz");

        mav.addObject("vocId", vocabularyId);

        //Random szó kiválasztása
        Quiz quiz = new Quiz();
        if(msgType != 2) {
            Random rnd = new Random();
            int random = rnd.nextInt(50 - 1) + 1;

            if (random % 2 == 0) {
                quiz.setWord1(quizService.getRandomEnglishWord(vocabularyId));
            } else {
                quiz.setWord1(quizService.getRandomHungarianWord(vocabularyId));
            }

        } else quiz.setWord1(quizService.getPair(hintWord));

        mav.addObject("quizWord", quiz);

        //Display data (Összes szó és elért pontok)
        mav.addObject("score", score);
        mav.addObject("turn", turn);

        //Display messages
        if(msgType == 0) session.setAttribute("message", new Message("Helyes!","success"));
        else if(msgType == 1) session.setAttribute("message", new Message(alertMessage, "danger"));
        else if(msgType == 2) session.setAttribute("message", new Message(alertMessage,"primary "));


        return mav;
    }

    //@PostMapping("/quiz/{vocabularyId}/guess")
    @RequestMapping(value = "/quiz/{vocabularyId}/guess", method = RequestMethod.POST, params = "submit")
    public String guess(@PathVariable(name = "vocabularyId") Integer vocabularyId, Quiz quiz) {

        if(quizService.checkWords(quiz.getWord1().trim(), quiz.getWord2().trim())) {
            score++;
            msgType = 0;
        } else {
            msgType = 1;
            alertMessage = "Nem jó! Helyesen: " + quizService.wordPair(quiz.getWord1().trim());
        }
        turn++;

        String url = "quiz/"+ vocabularyId;
        return "redirect:/" + url;
    }

    //@PostMapping("/quiz/{vocabularyId}/guess")
    @RequestMapping(value = "/quiz/{vocabularyId}/guess", method = RequestMethod.POST, params = "hint")
    public String hint(@PathVariable(name = "vocabularyId") Integer vocabularyId, Quiz quiz) {

        msgType = 2;
        alertMessage = quizService.giveHint(quiz.getWord1());
        hintWord = quizService.getPair(quiz.getWord1());

        String url = "quiz/"+ vocabularyId;
        return "redirect:/" + url;
    }
}
