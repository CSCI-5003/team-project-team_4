package oosd.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Random;

import oosd.view.WordGrid;
import oosd.view.GameBoardGUI;
import oosd.view.Word;

public class Game {
    private GameDifficulty gameDifficulty;
    private HashMap<String, List<String[]>> dictionary = new HashMap<>();

    public Game(GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;

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

    private String getIndividualWord( List<String[]> colorList, int i, int randomIntInRange) {
        String[] groupStringArray = colorList.get(randomIntInRange);
        String individualString = groupStringArray[i + 4];
        return individualString;
    }

    private void randomizeWords(Word[] words, WordDifficulty wordDifficulty) {
        Random random = new Random();
        //System.out.println(words[0].getText());
        List<String[]> yellowList = dictionary.get("Yellow");
        List<String[]> greenList = dictionary.get("Green");
        List<String[]> blueList = dictionary.get("Blue");
        List<String[]> purpleList = dictionary.get("Purple");

        int yellowIntInRange = random.nextInt(yellowList.size());
        int greenIntInRange = random.nextInt(greenList.size());
        int blueIntInRange = random.nextInt(blueList.size());
        int purpleIntInRange = random.nextInt(purpleList.size());
        for (int i = 0; i < 4; i++) {
            //System.out.println("setting text in getWords");
            
            String individualString = "not initialized";
            switch (wordDifficulty) {
                case WordDifficulty.YELLOW:
                    individualString = getIndividualWord(yellowList, i, yellowIntInRange);
                    System.out.println("Yellow Group: " + individualString);
                    break;
                case WordDifficulty.GREEN:
                    individualString = getIndividualWord(greenList, i, greenIntInRange);
                    System.out.println("Green Group: " + individualString);
                    break;
                case WordDifficulty.BLUE:
                    individualString = getIndividualWord(blueList, i, blueIntInRange);
                    System.out.println("Blue Group: " + individualString);
                    break;
                case WordDifficulty.PURPLE:
                    individualString = getIndividualWord(purpleList, i, purpleIntInRange);
                    System.out.println("Purple Group: " + individualString);
                    break;
            }
            words[i].updateText(individualString);
            //System.out.println(words[i].getText());
        }
    }

    public WordGrid makeGrid(Word[] wordArray) {
        WordGroup[] wordGroups = new WordGroup[4];
        int wordCount = 0;
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
            Word[] wordArraySubset = new Word[4];
            for (int j = 0; j < 4; j++) {
                wordArraySubset[j] = wordArray[wordCount];
                //System.out.println(wordArray[wordCount].getText());
                wordCount++;
            }
            randomizeWords(wordArraySubset, wordDifficulty);
            WordGroup wordGroup = new WordGroup(wordArraySubset, wordDifficulty);
            wordGroups[i] = wordGroup;
        }
        WordGrid wordGrid = new WordGrid(wordGroups);
        //System.out.println("returning the wordGrid that was made");
        return wordGrid;
    }
}
