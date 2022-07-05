package com.example.vocabularyproject.service;

import com.example.vocabularyproject.model.Vocabularies;
import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.repository.VocabularyRepository;
import com.example.vocabularyproject.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    WordRepository repository;

    @Autowired
    VocabularyRepository vocabularyRepository;

    public List<Words> getWords() {
        return repository.findAll();
    }

    public String addWord(Words word) {

        //Check if it has VocabularyID
        if(word.getVocabularyId() < 1) {
            return getDateTime() + " - Nem adtál meg szótár azonosítót!";
        }

        //Check if added Vocabulary exists
        if(!vocabularyRepository.findById(word.getVocabularyId()).isPresent()) {
            return getDateTime() + " - Ilyen szótár nem létezik!";
        }

        //English and Hungarian properties are mandatory
        if(word.getEnglish() == null || word.getHungarian() == null) {
            return getDateTime() + " - Nem adtál meg szót!";
        }

        //Success
        repository.save(word);

        //Connected Vocabulary should be updated
        vocabularyRepository.updateConfirmedAt(word.getVocabularyId(), getDateTime());

        return getDateTime() + " - Sikeresen rögzítetted a(z) " + word.getHungarian() + " szót";
    }

    public String updateWord(Words word) {
        //Check if is ID given
        if(word.getId() < 1) {
            return getDateTime() + " - Nem adtál meg szó azonosítót! ";
        }

        //Check if it has VocabularyID
        if(word.getVocabularyId() < 1) {
            return getDateTime() + " - Nem adtál meg szótár azonosítót!";
        }

        //English and Hungarian property is mandatory
        if(word.getEnglish() == null || word.getHungarian() == null) {
            return getDateTime() + " - Nem adtál meg szót!";
        }

        //Success
        repository.save(word);
        return getDateTime() + " - Sikeresen frissítetted a(z) " + word.getHungarian() + " szót";
    }

    public String deleteWord(Integer id) {
        if(id < 1) {
            return getDateTime() + " - Nincs ilyen szó!";
        }

        repository.deleteById(id);
        return getDateTime() + " - Sikeresen törölted a(z) " + id + " azonosítójú szót!";
    }

    public List<Words> getWordsByVocabulary(Integer vocabularyId) {
        System.out.println(getDateTime() + " - Szavak lekérdezése.");
        return repository.findAllByVocabularyId(vocabularyId);
    }

    public Optional<Words> getWord(Integer id) {
        System.out.println(getDateTime() + " - " + id + " azonosítójú szó lekérdezése.");
        return repository.findById(id);
    }

    private String getDateTime() {
        return LocalDateTime.now().toString();
    }
}
