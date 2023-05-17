package com.rws.workshop.hangman.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends JpaRepository<WordsEntity, Long> {
}