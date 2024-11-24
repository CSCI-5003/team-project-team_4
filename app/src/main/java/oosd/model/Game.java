package oosd.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

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

    public GameDifficulty getGameDifficulty() {
        return this.gameDifficulty;
    }

    public HashMap<String, List<String[]>> getWordDictionary() {
        return this.dictionary;
    }
}
