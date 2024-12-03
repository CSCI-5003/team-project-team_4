package oosd.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;

import oosd.view.WordButton;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Game {
    private ArrayList<Observer> observers = new ArrayList<>();
    private GameDifficulty gameDifficulty;
    private HashMap<String, List<String[]>> dictionary = new HashMap<>();
    private WordGroup[] wordGroups;
    private int lives;
    private int groupsRemaining;

    public Game(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        this.lives = 4;
        this.groupsRemaining = 4;

        String filePath = "src/files/WordBank.csv";

        List<String[]> yellowLines = new ArrayList<>();
        List<String[]> greenLines = new ArrayList<>();
        List<String[]> blueLines = new ArrayList<>();
        List<String[]> purpleLines = new ArrayList<>();
        
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator('\t').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath)).withCSVParser(parser).build();

            String[] line;
            int lineNumber = 0;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                lineNumber++;
                
                
                String color = line[2].trim(); 
                if (line.length > 2) {
                    if (color.equalsIgnoreCase("Yellow")) {
                        yellowLines.add(line);
                    } else if (color.equalsIgnoreCase("Green")) {
                        greenLines.add(line);
                    } else if (color.equalsIgnoreCase("Blue")) {
                        blueLines.add(line);
                    } else if (color.equalsIgnoreCase("Purple")) {
                        purpleLines.add(line);
                    }
                } else {
                    System.out.println("Skipping line " + lineNumber + " due to insufficient columns.");
                }
                //System.out.println("Yellow Lines:");
                //printLines(yellowLines);
                dictionary.put("Yellow", yellowLines);

                //System.out.println("\nGreen Lines:");
                //printLines(greenLines);
                dictionary.put("Green", greenLines);

                //System.out.println("\nBlue Lines:");
                //printLines(blueLines);
                dictionary.put("Blue", blueLines);

                //System.out.println("\nPurple Lines:");
                //printLines(purpleLines);
                dictionary.put("Purple", purpleLines);
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static void printLines(List<String[]> lines) {
        for (String[] line : lines) {
            System.out.println(String.join(", ", line));
        }
    }

    

    public void checkGuess(WordGroup inputWordGroup) {
        WordGroup[] wordGroups = this.wordGroups;
        String[] inputWordList = inputWordGroup.getWordList();

        int matchCount = 0;
        int bestMatchCount = 0;
        WordGroup correctWords = null;
        
        
        //WordButton[] gridWordList = this.wordGrid.getWordButtons();
        
        
        for (int i = 0; i < inputWordList.length; i++) {

            for (int j = 0; j < wordGroups.length; j++) {
                String[] groupStringList = wordGroups[j].getWordList();

                HashMap<String, Integer> countMap = new HashMap<>();

                for (String word : inputWordList) {
                    countMap.put(word, countMap.getOrDefault(word, 0) + 1);
                }

                matchCount = 0;

                for (String word : groupStringList) {
                    if (countMap.containsKey(word) && countMap.get(word) > 0) {
                        matchCount++;
                        countMap.put(word, countMap.get(word) - 1); // Decrease count for matched item
                    }
                }

                if (matchCount > bestMatchCount) {
                    bestMatchCount = matchCount;
                    correctWords = wordGroups[j];
                }
                    
                }
            }

        
        notifyObservers(bestMatchCount, correctWords);
    }

    public void setWordGroups(WordGroup[] wordGroups) {
        this.wordGroups = wordGroups;
    }

    public void decrementLives() {
        this.lives--;
    }

    public void decrementGroupsRemaining() {
        this.groupsRemaining--;
    }

    public int getLives() {
        return this.lives;
    }

    public int getGroupsRemaining() {
        return this.groupsRemaining;
    }

    public GameDifficulty getGameDifficulty() {
        return this.gameDifficulty;
    }

    public HashMap<String, List<String[]>> getWordDictionary() {
        return this.dictionary;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(int matchCount, WordGroup correctWords) {
        for (Observer observer : observers) {
            observer.update(matchCount, correctWords);
            //System.out.println("Observer list: " + observer);
        }
    }
}
