package com.rws.workshop.hangman.web;

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
    private final String[] wordList = {"representative",
            "discrimination",
            "responsibility",
            "superintendent",
            "infrastructure",
            "extraterrestrial",
            "characteristic",
            "constitutional",
            "administration",
            "correspondence",
            "rehabilitation",
            "recommendation",
            "disappointment",
            "identification"};

    private String currentWord = null;

    private String generateRandomWord() {
        if (currentWord == null) {
            Random random = new Random();
            int index = random.nextInt(wordList.length);
            currentWord = wordList[index];
        }
        return currentWord;
    }

    @GetMapping("/guess")
    public GuessResponse handleGuess(@RequestParam("guessedLetter") String guessedLetter) {

        // Get the word for the game (generate or retrieve from a data source)
        String word = generateRandomWord();

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