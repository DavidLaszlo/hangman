package com.rws.workshop.hangman.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StaticWordsRepository {

    private final List<String> wordList = List.of("pineapple", "banana", "apple", "blueberry");

    public List<WordsEntity> findAll(){
        return wordList.stream().map(WordsEntity::new).collect(Collectors.toList());
    }
}