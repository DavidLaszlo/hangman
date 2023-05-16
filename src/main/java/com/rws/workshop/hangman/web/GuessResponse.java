package com.rws.workshop.hangman.web;

import java.util.List;

public class GuessResponse {
    List<Integer> indices;
    int wordLength;

    public GuessResponse() {
    }

    public GuessResponse(List<Integer> indices, int wordLength) {
        this.indices = indices;
        this.wordLength = wordLength;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }
}
