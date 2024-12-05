package oosd.model;

import java.util.Arrays;

public class WordGroup {
    private String[] wordList;
    private WordDifficulty difficulty;
    private String category;

    public WordGroup(String[] wordList, WordDifficulty difficulty, String category) {
        if (wordList == null || wordList.length != 4) {
            throw new IllegalArgumentException("WordGroup must contain exactly 4 words.");
        }
        this.wordList = wordList;
        this.difficulty = difficulty;
        this.category = category;
    }

    public String[] getWordList() {
        return this.wordList;
    }

    public WordDifficulty getWordDifficulty() {
        return this.difficulty;
    }

    public String getCategory() {
        return category;
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

        // Sort both word lists and compare them
        String[] thisWords = wordList;
        String[] otherWords = new String[other.wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            thisWords[i] = wordList[i];
            System.out.println("this words i: " + thisWords[i]);
        }
        for (int i = 0; i < other.wordList.length; i++) {
            otherWords[i] = other.wordList[i];
        }

        Arrays.sort(thisWords);
        Arrays.sort(otherWords);

        return Arrays.equals(thisWords, otherWords);
    }

}

