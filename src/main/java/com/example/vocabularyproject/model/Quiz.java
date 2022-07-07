package com.example.vocabularyproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class Quiz {

    private String word1;
    private String word2;
    private Integer score;

}
