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
            wordsRepository.count();

            // Create and save sample entities
            wordsRepository.save(new WordsEntity("apple"));
            wordsRepository.save(new WordsEntity("banana"));
            wordsRepository.save(new WordsEntity("pineapple"));
            wordsRepository.save(new WordsEntity("orange"));

            // ... add more entities if needed
        };
    }
}
