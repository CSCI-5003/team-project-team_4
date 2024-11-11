package oosd.view;

import javax.swing.JButton;

import oosd.model.WordDifficulty;

public class Word extends JButton{
    private String text;
    private WordDifficulty group;

    public Word(String defaultText) {
        this.text = defaultText;
        this.setText(defaultText);
        //System.out.println("updating defaultText: " + this.text);
    }

    public String getText() {
        return this.text;
    }

    public void updateText(String text) {
        this.text = text;
        this.setText(text);
    }
}
