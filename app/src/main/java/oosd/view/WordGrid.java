package oosd.view;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.ArrayList;

import oosd.view.Word;
import oosd.model.WordGroup;

public class WordGrid extends JPanel {
    
    private Word[] buttons = new Word[16];
    private WordGroup[] wordGroups;
    
    public WordGrid(WordGroup[] wordGroups) {

        this.wordGroups = wordGroups;

    }
}
