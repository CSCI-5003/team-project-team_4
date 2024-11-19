package oosd;

import oosd.controller.Controller;
import oosd.view.MenuGUI;

public class App {

    private static Controller controller;
    private static MenuGUI menu;
    //private static DifficultyGUI difficulty;
    //private static GameBoardGUI gameBoard;
    //private static HighScores highScores;
    //private static Instructions instructions;
    //private static EndGame endGame;

    public static void main(String[] args) {
        controller = new Controller(menu);
    }
    
}