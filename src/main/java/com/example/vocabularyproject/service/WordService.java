package com.example.vocabularyproject.service;

import com.example.vocabularyproject.model.Words;
import com.example.vocabularyproject.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordService {

    @Autowired
    WordRepository repository;

}
