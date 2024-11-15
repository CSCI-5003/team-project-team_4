package oosd.view;
import javax.swing.JPanel;

import java.util.ArrayList;

import oosd.model.WordGroup;

public class WordGrid extends JPanel {
    
    private Word[] buttons = new Word[16];
    private WordGroup[] wordGroups;
    private ArrayList<WordGroup> alreadyGuessed;
    
    public WordGrid(WordGroup[] wordGroups) {

        this.wordGroups = wordGroups;
        alreadyGuessed = new ArrayList<WordGroup>();

    }

    public WordGroup[] getWordGroups() {
        return this.wordGroups;
    }

    public ArrayList<WordGroup> getAlreadyGuessed() {
        return this.alreadyGuessed;
    }

    public void addGuess(WordGroup guess) {
        alreadyGuessed.add(guess);
    }
}
