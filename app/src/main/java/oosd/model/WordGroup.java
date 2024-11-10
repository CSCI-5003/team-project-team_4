package oosd.model;

import oosd.view.Word;
import oosd.model.WordDifficulty;

public class WordGroup {
    private Word[] wordList;
    private WordDifficulty difficulty;

    public WordGroup(Word[] wordList, WordDifficulty difficulty) {
        this.wordList = wordList;
        this.difficulty = difficulty;
    }
}
