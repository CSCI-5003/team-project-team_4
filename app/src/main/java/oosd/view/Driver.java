package oosd.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Driver {

    private static MenuGUI menu;
    private static DifficultyGUI difficulty;
    //private static GameBoardGUI game;
    private static HighScores score;
    private static Instructions howTo;

    public static void main(String[] args) {

        //  Main Menu
        ActionListener playGameListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);       
                
                BackListener backListener = new BackListener(); 
                difficulty = new DifficultyGUI(backListener);
                backListener.setNewFrame(menu);
                backListener.setOldFrame(difficulty);
                
                difficulty.setVisible(true);
            }
        };

        ActionListener highScoreListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                BackListener backListener = new BackListener(); 
                score = new HighScores(backListener);
                backListener.setNewFrame(menu);
                backListener.setOldFrame(score);
            }
        };

        ActionListener howToListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                BackListener backListener = new BackListener(); 
                howTo = new Instructions(backListener);
                backListener.setNewFrame(menu);
                backListener.setOldFrame(howTo);
            }
        };

        menu = new MenuGUI(playGameListener, highScoreListener, howToListener);

    }
    
}
