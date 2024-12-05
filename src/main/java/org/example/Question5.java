package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */
public class Question5 {    //Java Identifier Count (Map)

    public static void readFile(String fileName) throws FileNotFoundException {
        Map<String, Integer> identifierCountMap = new HashMap<>();
        Map<String, List<String>> identifierLines = new HashMap<>();
        int lineNum =1;
        Scanner keyboard = new Scanner(new File(fileName));

        while(keyboard.hasNextLine()){
            String line = keyboard.nextLine().trim();
            String[] identifiers = line.split("[^A-Za-z0-9_]+");

            for(String identifier : identifiers){
                if(!identifier.isEmpty()){
                    identifierCountMap.put(identifier, identifierCountMap.getOrDefault(identifier, 0)+1);
                    identifierLines.putIfAbsent(identifier, new ArrayList<>());
                    identifierLines.get(identifier).add(lineNum + ". " + line);
                }
            }

            lineNum++;
        }
        keyboard.close();
        displayResults(identifierCountMap, identifierLines);
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("fileName.txt");
    }

    private static void displayResults (Map<String, Integer> identifierCountMap, Map<String, List<String>> identifierLines) {
        List<String> sortedIdentifiers = new ArrayList<>(identifierCountMap.keySet());
        Collections.sort(sortedIdentifiers);

        for(String identifier : sortedIdentifiers){
            System.out.println(identifier + " " + identifierCountMap.get(identifier));

            for(String line : identifierLines.get(identifier)) {
                System.out.println(" " +line);
            }
        }
    }
}
