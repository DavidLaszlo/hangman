package com.rws.workshop.hangman.persistence;

import jakarta.persistence.*;

@Entity
public class WordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    // Constructors, getters, and setters


    public WordsEntity() {
    }

    public WordsEntity(Long id, String word) {
        this.id = id;
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
