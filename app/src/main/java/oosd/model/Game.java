package oosd.model;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import oosd.view.WordGrid;
import oosd.view.GameBoardGUI;
import oosd.view.Word;

public class Game {
    private GameDifficulty gameDifficulty;
    private GameBoardGUI gameBoardGUI;
    private ArrayList<String[]>[] groupArray = new ArrayList[4];
    String filePath = "app/src/files/WordBank.csv";

    public Game(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.gameBoardGUI = new GameBoardGUI(this);

        for (int i = 0; i < 4; i++) {
            groupArray[i] = new ArrayList<>();
        }

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 2) {
                    switch (nextLine[2].toLowerCase()) {
                        case "yellow":
                            groupArray[0].add(nextLine);
                            break;
                        case "blue":
                            groupArray[1].add(nextLine);
                            break;
                        case "green":
                            groupArray[2].add(nextLine);
                            break;
                        case "purple":
                            groupArray[3].add(nextLine);
                            break;
                    }
                }
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < groupArray.length; i++) {
            System.out.println("Group " + i + ":");
            for (String[] entry : groupArray[i]) {
                for (int j = 0; j < Math.min(3, entry.length); j++) {
                    System.out.print(entry[j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private Word[] getWords() {
        Word[] words = new Word[4];



        return words;
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
            WordGroup wordGroup = new WordGroup(getWords(), wordDifficulty);
            wordGroups[i] = wordGroup;
        }
        
        WordGrid wordGrid = new WordGrid(wordGroups);
        return wordGrid;
    }
}
