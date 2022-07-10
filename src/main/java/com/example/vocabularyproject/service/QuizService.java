package com.example.vocabularyproject.service;

import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    WordService wordService;

    List<Words> listOfWords;

    public String getRandomEnglishWord(Integer vocabularyId) {
        listOfWords = wordService.getWordsByVocabulary(vocabularyId);
        return listOfWords.get(rndIndex(listOfWords.size())).getEnglish();
    }

    public String getRandomHungarianWord(Integer vocabularyId) {
        listOfWords = wordService.getWordsByVocabulary(vocabularyId);
        return listOfWords.get(rndIndex(listOfWords.size())).getHungarian();
    }

    public boolean checkWords(String word1, String word2) {
        Words guessedWord = wordService.findWordByEnglish(word2.trim());
        if(guessedWord == null) {
            guessedWord = wordService.findWordByHungarian(word2);

            if(guessedWord == null) {
                return false;
            }
        }

        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        String en = guessedWord.getEnglish().toLowerCase();
        String hun = guessedWord.getHungarian().toLowerCase();

        //Check the user had to guess english or hungarian word
       if(word1.equals(en)) {
           return word2.equals(hun);
       } else if(word1.equals(hun)) {
           return word2.equals(en);
       }

        return false;
    }

    public String wordPair(String word) {
        Words guessedWord = wordService.findWordByEnglish(word.trim());
        if(guessedWord == null) {
            guessedWord = wordService.findWordByHungarian(word);

            if(guessedWord == null) {
                return "Nincs találat";
            }
        }

        return guessedWord.getEnglish() + " = " + guessedWord.getHungarian();
    }

    public String getPair(String word) {
        Words guessedWord = wordService.findWordByEnglish(word.trim());
        if(guessedWord == null) {
            guessedWord = wordService.findWordByHungarian(word);

            if(guessedWord == null) {
                return "Nincs találat";
            }
            return guessedWord.getEnglish();
        }

        return guessedWord.getHungarian();
    }


    public String giveHint(String word) {
        Words guessedWord = wordService.findWordByEnglish(word.trim());
        if(guessedWord != null) {
            return givePiece(guessedWord.getHungarian());
        }
        else {
            guessedWord = wordService.findWordByHungarian(word.trim());

            if(guessedWord != null) {
                return givePiece(guessedWord.getEnglish());
            }
        }

        return "Nincs találat";
    }

    private String givePiece(String chosenWord) {
        StringBuilder result = new StringBuilder();

        Random rnd = new Random();

        for(int i = 0; i < chosenWord.length(); i++) {
            int chance = rnd.nextInt(50 - 1) + 1;

            if(chance % 2 == 0) {
                 result.append(chosenWord.charAt(i));
            } else {
                result.append("_");
            }
        }

        return result.toString();
    }

    private int rndIndex(int max) {
        Random rnd = new Random();
        return rnd.nextInt(max);
    }

}
