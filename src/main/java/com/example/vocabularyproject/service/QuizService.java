package com.example.vocabularyproject.service;

import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.repository.VocabularyRepository;
import com.example.vocabularyproject.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    VocabularyRepository vocabularyRepository;

    @Autowired
    WordService wordService;

    List<Words> listOfWords;


    public String getRandomEnglishWord(Integer vocabularyId) {
        listOfWords = wordService.getWordsByVocabulary(vocabularyId);
        return listOfWords.get(rndIndex(listOfWords.size())).getHungarian();
    }

    public String getRandomHungarianWord(Integer vocabularyId) {
        listOfWords = wordService.getWordsByVocabulary(vocabularyId);
        return listOfWords.get(rndIndex(listOfWords.size())).getHungarian();
    }

    public boolean checkWords(String word1, String word2) {
        Words guessedWord = wordService.findWordByName(word2);

        if(guessedWord == null) {
            System.out.println("Nincs ilyen szó az adatbázisban: " + word2);
            return false;
        }

        //Check the user had to guess english or hungarian word
       if(guessedWord.getEnglish().equals(word1)) {
            if(guessedWord.getHungarian().equals(word2.toLowerCase())) {
                return true;
            }
       } else if(guessedWord.getHungarian().equals(word1)) {
           if(guessedWord.getEnglish().equals(word2.toLowerCase())) {
               return true;
           }
       }


        return true;
    }


    private int rndIndex(int max) {
        Random rnd = new Random();
        return rnd.nextInt(max - 1) + 1;
    }

}
