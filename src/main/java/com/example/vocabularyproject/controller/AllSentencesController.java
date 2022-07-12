package com.example.vocabularyproject.controller;

import com.example.vocabularyproject.model.Message;
import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AllSentencesController {

    @Autowired
    SentenceService service;

    private final int MAX = 10;
    int msgType = -1;
    // -1:none, 0:success, 1:info, 2:null, 3:too high
    String alertMessage;

    List<Words> randomWordList;

    @RequestMapping("/sentences")
    public String openSentence(HttpSession session, Model model) {

        //Give values to HTML
        if(randomWordList != null) {
            model.addAttribute("wordList", randomWordList);
        }

        //Display messages
        if(msgType > 1) session.setAttribute("alertMessage", new Message(alertMessage, "danger"));

        return "all_sentences";
    }

    @PostMapping("/sentences/get")
    public String getWords(@RequestParam(value= "myAmount", required = false) Integer amount) {

        int status = service.getStatus(amount, MAX);

        switch (status) {

            case 0:
                randomWordList = service.getAmountOfWords(amount);
                msgType = 0;
                break;

            case 2:
                msgType = 2;
                alertMessage = "Adj meg egy számot!";
                break;

            case 3:
                msgType = 3;
                alertMessage = "A megengedett szám, ami adható: " + MAX;
                break;

            case 4:
                msgType = 4;
                alertMessage = "A megadott érték meghaladja a szótár szavainak számát!";
                break;

            default:
                msgType = -1;

        }


        return "redirect:/sentences";
    }

}
