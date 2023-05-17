package com.rws.workshop.hangman.web;

import com.rws.workshop.hangman.persistence.StaticWordsRepository;
import com.rws.workshop.hangman.persistence.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
public class HangmanController {
    public static final Random RANDOM = new Random();

    private String currentWord = null;

    @Autowired
//    private WordsRepository wordsRepository;
    private StaticWordsRepository wordsRepository;


    private String getWordFromDb() {
        if (currentWord == null) {
            var wordsFromDb = wordsRepository.findAll();
            int index = RANDOM.nextInt(wordsFromDb.size());
            currentWord = wordsFromDb.get(index).getWord();
        }
        return currentWord;
    }

    @GetMapping("/guess")
    public GuessResponse handleGuess(@RequestParam("guessedLetter") String guessedLetter) {

        // Get the word for the game (generate or retrieve from a data source)
//        String word = generateRandomWord();
        String word = getWordFromDb();

        // retrieve initial length of the word
        if (guessedLetter == null || guessedLetter.isBlank()) {
            return new GuessResponse(Collections.emptyList(), word.length());
        }
        // Retrieve the guessed letter from the request
        char letter = guessedLetter.charAt(0);


        // Perform the letter matching logic and return the indices
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                indices.add(i);
            }
        }
        return new GuessResponse(indices, word.length());
    }
}