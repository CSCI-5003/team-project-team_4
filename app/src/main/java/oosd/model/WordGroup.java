package oosd.model;

import java.util.Arrays;

public class WordGroup {
    private String[] wordList;
    private WordDifficulty difficulty;

    public WordGroup(String[] wordList, WordDifficulty difficulty) {
        if (wordList == null || wordList.length != 4) {
            throw new IllegalArgumentException("WordGroup must contain exactly 4 words.");
        }
        //System.out.println("WORD LIST ZERO IS " + wordList[0]);
        this.wordList = wordList;
        this.difficulty = difficulty;
    }

    public String[] getWordList() {
        return this.wordList;
    }

    public WordDifficulty getWordDifficulty() {
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
        String[] thisWords = wordList;
        String[] otherWords = new String[other.wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            thisWords[i] = wordList[i];
        }
        for (int i = 0; i < other.wordList.length; i++) {
            otherWords[i] = other.wordList[i];
        }

        Arrays.sort(thisWords);
        Arrays.sort(otherWords);

        return Arrays.equals(thisWords, otherWords);
    }

}

