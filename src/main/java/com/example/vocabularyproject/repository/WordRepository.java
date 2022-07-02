package com.example.vocabularyproject.repository;

import com.example.vocabularyproject.model.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Words, Integer> {
}
