package com.example.vocabularyproject.service;

import com.example.vocabularyproject.model.Vocabularies;
import com.example.vocabularyproject.repository.VocabularyRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VocabularyService {

    @Autowired
    VocabularyRepository repository;
    LocalDateTime dateTime;

    public String createVocabulary(Vocabularies vocabulary) {
        String name = vocabulary.getName();
        dateTime = LocalDateTime.now();

        //Check that it's individual
        if(repository.findVocabularyByName(name).isPresent()) {
            return dateTime + " - A(z) " + name + " nevű szótár már létezik!";
        }

        //Success
        vocabulary.setCreated(dateTime.toString());
        vocabulary.setUpdated(dateTime.toString());
        repository.save(vocabulary);
        return dateTime + " - Sikeresen rögzítetted a(z) " + name + " szótárat";
    }


    public String updateVocabulary(Vocabularies vocabulary) {
        String name = vocabulary.getName();
        dateTime = LocalDateTime.now();
        Optional<Vocabularies> existing = repository.findVocabularyByName(name);

        //Check that it's individual
        if(existing.isPresent() && vocabulary.getId() != existing.get().getId()) {
            return dateTime + " - A(z) " + name + " nevű szótár már létezik!";
        }

        //Dates update is not allowed
        if(vocabulary.getUpdated() != null || vocabulary.getCreated() != null) {
            return dateTime + " - A dátumok módosítása nem engedélyezett!";
        }

        //Success
        existing.ifPresent(vocabularies -> vocabulary.setCreated(vocabularies.getCreated()));
        vocabulary.setUpdated(dateTime.toString());
        repository.save(vocabulary);
        return dateTime + " - Sikeresen módosítottad a(z) " + name + " szótárat";
    }

    public String deleteVocabulary(int id) {
        if(!repository.findById(id).isPresent()) {
            return getDateTime() + " - Nincs ilyen azonosítójú Szótár";
        }

        if(id < 1) {
            return getDateTime() + " - Sikertelen törlés";
        }
        repository.deleteById(id);
        return getDateTime() + " - Sikeresen törölted a(z) " + id + " azonosítójú szótárat!";
    }

    public List<Vocabularies> getAllVocabularies() {
        return repository.findAll();
    }

    private String getDateTime() {
        return LocalDateTime.now().toString();
    }
}
