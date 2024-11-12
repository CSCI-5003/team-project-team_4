package oosd.view;
import javax.swing.JPanel;

import oosd.model.WordGroup;

public class WordGrid extends JPanel {
    
    private Word[] buttons = new Word[16];
    private WordGroup[] wordGroups;
    
    public WordGrid(WordGroup[] wordGroups) {

        this.wordGroups = wordGroups;

    }
}
