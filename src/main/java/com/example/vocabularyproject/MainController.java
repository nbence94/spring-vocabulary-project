package com.example.vocabularyproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("")
    public String goHome() {
        return "redirect:/index";
    }
}
