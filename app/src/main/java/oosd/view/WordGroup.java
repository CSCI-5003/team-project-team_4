package oosd.view;

import oosd.model.WordDifficulty;

public class WordGroup {
    private Word[] wordList;
    private WordDifficulty difficulty;

    public WordGroup(Word[] wordList, WordDifficulty difficulty) {
        this.wordList = wordList;
        this.difficulty = difficulty;
    }

    public Word[] getWordList() {
        return this.wordList;
    }

    public WordDifficulty geWordDifficulty() {
        return this.difficulty;
    }
}
