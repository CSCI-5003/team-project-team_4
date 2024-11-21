package oosd.controller;

import java.awt.event.ActionListener;

import oosd.model.GameDifficulty;
import oosd.view.DifficultyGUI;
import oosd.view.EndGame;
import oosd.view.GameBoardGUI;
import oosd.view.HighScores;
import oosd.view.Instructions;
import oosd.view.MenuGUI;

import java.awt.event.ActionEvent;

public class Controller implements ActionListener {

    private GameBoardGUI gameBoard;
    private MenuGUI menu;
    private HighScores highScores;
    private EndGame endGame;
    private DifficultyGUI difficulty;
    private Instructions instructions;

    public Controller(MenuGUI menu) {
        
        this.menu = new MenuGUI();
        this.gameBoard = new GameBoardGUI(GameDifficulty.MEDIUM); 
        this.highScores = new HighScores();
        //this.endGame = new EndGame();
        this.difficulty = new DifficultyGUI();
        this.instructions = new Instructions();

        this.gameBoard.setVisible(false);
        this.highScores.setVisible(false);
        //this.endGame.setVisible(false);
        this.difficulty.setVisible(false);
        this.instructions.setVisible(false);
        
        this.menu.getPlayNowBut().addActionListener(this);
        this.menu.gethighScoresBut().addActionListener(this);
        this.menu.getHowToBut().addActionListener(this);

        this.gameBoard.getSubmitBut().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("Button clicked: " + e.getActionCommand());  
        
        switch (e.getActionCommand()) {
            case "Play Now":
                handlePlayNowBut();
                break;
            case "High Scores":
                handleHighScores();
                break;
            case "How To Play":
                handleHowToBut();
                break;
            case "Easy":
                handleEasy();
                break;
            case "Medium":
                handleMedium();
                break;
            case "Hard":
                handleHard();
                break;
            case "Return to Menu":
                handleReturnButton();
                break;
            case "Submit":
                handleSubmit();
                break;
            default:
                break;
        }
    }

    // return buttons
    private void handleReturnButton() {
        this.menu.setVisible(true);
        this.difficulty.setVisible(false);
        this.gameBoard.setVisible(false);
        //this.endGame.setVisible(false);
        this.instructions.setVisible(false);
        this.highScores.setVisible(false);   
    }

    // menu buttons
    private void handlePlayNowBut() {
        menu.setVisible(false);
        this.difficulty.setVisible(true);

        this.difficulty.getEasyBut().addActionListener(this);
        this.difficulty.getMediumBut().addActionListener(this);
        this.difficulty.getHardBut().addActionListener(this);
        this.difficulty.getReturnBut().addActionListener(this);
    }

    private void handleHowToBut() {
        menu.setVisible(false);
        this.instructions.setVisible(true);
        this.instructions.getReturnBut().addActionListener(this);
    }

    private void handleHighScores() {
        menu.setVisible(false);
        this.highScores.setVisible(true);
        this.highScores.getReturnBut().addActionListener(this);
    }

    // difficulty buttons
    private void handleEasy() {
        difficulty.setVisible(false);
        GameBoardGUI gameBoard = new GameBoardGUI(GameDifficulty.EASY);
        this.gameBoard = gameBoard;
        this.gameBoard.setVisible(true);

        this.gameBoard.getSubmitBut().addActionListener(this);
        this.gameBoard.getReturnBut().addActionListener(this);
    }

    private void handleMedium() {
        difficulty.setVisible(false);
        GameBoardGUI gameBoard = new GameBoardGUI(GameDifficulty.MEDIUM);
        this.gameBoard = gameBoard;
        this.gameBoard.setVisible(true);

        this.gameBoard.getSubmitBut().addActionListener(this);
        this.gameBoard.getReturnBut().addActionListener(this);
    }

    private void handleHard() {
        difficulty.setVisible(false);
        this.gameBoard.setVisible(true);
        GameBoardGUI gameBoard = new GameBoardGUI(GameDifficulty.HARD);
        this.gameBoard = gameBoard;
        this.gameBoard.setVisible(true);

        this.gameBoard.getSubmitBut().addActionListener(this);
        this.gameBoard.getReturnBut().addActionListener(this);
    }

    // Game Board Buttons
    private void handleSubmit() {

    }

    private void handleWordButtonClick() {

    }
}
