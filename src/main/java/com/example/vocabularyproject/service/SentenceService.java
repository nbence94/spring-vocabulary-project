package com.example.vocabularyproject.service;

import com.example.vocabularyproject.model.Words;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SentenceService {

    @Autowired
    WordService wordService;

    public List<Words> getAmountOfWords(Integer vocabularyId, Integer amount) {

        List<Words> list = wordService.getWordsByVocabulary(vocabularyId);
        List<Words> resultList = new ArrayList<>();


        Random rnd = new Random();
        int index;

        for(int i = 0; i < amount; i++) {
            index = rnd.nextInt(list.size());

            while(resultList.contains(list.get(index)))
                index = rnd.nextInt(list.size());

            resultList.add(list.get(index));
        }

        return resultList;
    }

    public int getStatus(Integer vocabularyId, Integer amount, int max) {
        if(amount == null || amount < 1) {
            return 2;
        }

        if(amount > max) {
            return 3;
        }

        List<Words> list = wordService.getWordsByVocabulary(vocabularyId);

        if(amount > list.size()) {
            return 4;
        }

        return 0;
    }

}
