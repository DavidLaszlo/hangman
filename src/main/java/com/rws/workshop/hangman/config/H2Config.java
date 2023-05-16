package com.rws.workshop.hangman.config;

import com.rws.workshop.hangman.persistence.WordsEntity;
import com.rws.workshop.hangman.persistence.WordsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {
    @Bean
    public CommandLineRunner initializeDatabase(WordsRepository wordsRepository) {
        return args -> {
            // Create the schema and tables
            wordsRepository.count(); // This triggers the creation of the schema and tables

            // Create and save sample entities
            WordsEntity entity1 = new WordsEntity();
            entity1.setWord("apple");
            wordsRepository.save(entity1);

            WordsEntity entity2 = new WordsEntity();
            entity2.setWord("banana");
            wordsRepository.save(entity2);

            // ... add more entities if needed
        };
    }
}
