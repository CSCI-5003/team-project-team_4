package oosd.view;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;

import javax.swing.JButton;

import oosd.model.WordDifficulty;

public class WordButton extends JButton{
    private String text;
    private WordDifficulty group;

    public WordButton(String defaultText) {
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

    @Override
    protected void paintComponent(Graphics g) {
        // Custom painting to prevent ellipsis
        super.paintComponent(g);

        FontMetrics fm = g.getFontMetrics(getFont());
        String text = getText();
        int textWidth = fm.stringWidth(text);
        int buttonWidth = getWidth() - getInsets().left - getInsets().right;

        if (textWidth > buttonWidth) {
            // Reduce font size dynamically to fit the text
            Font font = getFont();
            while (textWidth > buttonWidth && font.getSize() > 1) {
                font = font.deriveFont((float) font.getSize() - 1);
                g.setFont(font);
                fm = g.getFontMetrics(font);
                textWidth = fm.stringWidth(text);
            }
            setFont(font);
        }
    }
}
