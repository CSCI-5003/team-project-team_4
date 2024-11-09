package oosd.model;

import oosd.view.WordGrid;
import oosd.view.GameBoardGUI;

public class Game {
    private GameDifficulty gameDifficulty;
    private GameBoardGUI gameBoardGUI;

    public Game(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.gameBoardGUI = new GameBoardGUI(this);
    }

    public WordGrid makeGrid() {
        WordGroup[] wordGroups = new WordGroup[4];
        for (int i = 0; i < 4; i++) {
            WordDifficulty wordDifficulty = WordDifficulty.YELLOW;
            switch (gameDifficulty) {
                case EASY:
                    switch (i) {
                        case 0:
                            wordDifficulty = WordDifficulty.YELLOW;
                            break;
                        case 1:
                        case 2:
                            wordDifficulty = WordDifficulty.GREEN;
                            break;
                        case 3:
                            wordDifficulty = WordDifficulty.BLUE;
                            break;
                    }
                    break;
                case MEDIUM:
                    switch (i) {
                        case 0:
                            wordDifficulty = WordDifficulty.YELLOW;
                            break;
                        case 1:
                            wordDifficulty = WordDifficulty.GREEN;
                            break;
                        case 2:
                            wordDifficulty = WordDifficulty.BLUE;
                            break;
                        case 3:
                            wordDifficulty = WordDifficulty.PURPLE;
                            break;
                    }
                    break;
                case HARD:
                    switch (i) {
                        case 0:
                            wordDifficulty = WordDifficulty.GREEN;
                            break;
                        case 1:
                        case 2:
                            wordDifficulty = WordDifficulty.BLUE;
                            break;
                        case 3:
                            wordDifficulty = WordDifficulty.PURPLE;
                            break;
                    }
                    break;
            }
            WordGroup wordGroup = new WordGroup(null, wordDifficulty);
            wordGroups[i] = wordGroup;
        }
        
        WordGrid wordGrid = new WordGrid(wordGroups);
        return wordGrid;
    }
}
