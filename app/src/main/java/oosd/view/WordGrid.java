package oosd.view;
import javax.swing.JLayeredPane;

import oosd.model.WordGroup;

import java.util.ArrayList;

public class WordGrid extends JLayeredPane {
    
    private WordButton[] buttons;
    private WordGroup[] wordGroups;
    private ArrayList<WordGroup> alreadyGuessed;
    private int groupsRemaining;
    private int lives;
    
    public WordGrid(WordGroup[] wordGroups, WordButton[] wordButtons) {
        this.wordGroups = wordGroups;
        alreadyGuessed = new ArrayList<WordGroup>();
        groupsRemaining = 4;
        lives = 4;
        this.buttons = wordButtons;

        for (int i = 0; i < 16; i++) {
            this.add(buttons[i]);
        }
    }

    public WordGroup[] getWordGroups() {
        return this.wordGroups;
    }

    public ArrayList<WordGroup> getAlreadyGuessed() {
        return this.alreadyGuessed;
    }

    public int getGroupsRemaining() {
        return this.groupsRemaining;
    }

    public int getLives() {
        return this.lives;
    }

    public void decrementLives() {
        this.lives--;
    }

    public void decrementGroupsRemaining() {
        this.groupsRemaining--;
    }

    public void addGuess(WordGroup guess) {
        alreadyGuessed.add(guess);
    }

    public WordButton[] getWordButtons() {
        return this.buttons;
    }

    
}
