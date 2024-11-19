package oosd.view;
import javax.swing.JLayeredPane;

import java.util.ArrayList;

public class WordGrid extends JLayeredPane {
    
    private Word[] buttons = new Word[16];
    private WordGroup[] wordGroups;
    private ArrayList<WordGroup> alreadyGuessed;
    private int groupsRemaining;
    private int lives;
    
    public WordGrid(WordGroup[] wordGroups) {

        this.wordGroups = wordGroups;
        alreadyGuessed = new ArrayList<WordGroup>();
        groupsRemaining = 4;
        lives = 4;

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
}
