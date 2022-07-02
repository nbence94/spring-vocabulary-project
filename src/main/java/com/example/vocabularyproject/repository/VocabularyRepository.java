package com.example.vocabularyproject.repository;

import com.example.vocabularyproject.model.Vocabularies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabularies, Integer> {
    Optional<Vocabularies> findVocabularyByName(String name);
}
