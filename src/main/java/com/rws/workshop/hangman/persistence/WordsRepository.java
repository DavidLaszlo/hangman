package com.rws.workshop.hangman.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<WordsEntity, Long> {
}