package com.example.vocabularyproject.repository;

import com.example.vocabularyproject.model.Vocabularies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabularies, Integer> {
    Optional<Vocabularies> findVocabularyByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Vocabularies SET updated = ?2 WHERE id = ?1")
    int updateConfirmedAt(Integer id, String updatedAt);

}
