package oosd.view;

import java.util.Arrays;

import oosd.model.WordDifficulty;

public class WordGroup {
    private WordButton[] wordList;
    private WordDifficulty difficulty;

    public WordGroup(WordButton[] wordList, WordDifficulty difficulty) {
        if (wordList == null || wordList.length != 4) {
            throw new IllegalArgumentException("WordGroup must contain exactly 4 words.");
        }
        this.wordList = wordList;
        this.difficulty = difficulty;
    }
    

    public WordButton[] getWordList() {
        return this.wordList;
    }

    public WordDifficulty geWordDifficulty() {
        return this.difficulty;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        WordGroup other = (WordGroup) obj;

        // Sort both word lists and compare them for equality
        String[] thisWords = new String[wordList.length];
        String[] otherWords = new String[other.wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            thisWords[i] = wordList[i].getText();
        }
        for (int i = 0; i < other.wordList.length; i++) {
            otherWords[i] = other.wordList[i].getText();
        }

        Arrays.sort(thisWords);
        Arrays.sort(otherWords);

        return Arrays.equals(thisWords, otherWords);
    }

}

