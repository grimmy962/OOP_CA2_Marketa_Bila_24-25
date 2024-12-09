package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.*;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */
public class Question11 {
    public static void main(String[] args) {
        Map<String, List<DistanceTo>> cityGraph = new HashMap<>();

        try {
            File file = new File("city_connections.txt");
            Scanner keyboard = new Scanner(file);

            while (keyboard.hasNextLine()) {
                String line = keyboard.nextLine().trim();
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    String city1 = parts[0];
                    String city2 = parts[1];
                    int distance = Integer.parseInt(parts[2]);

                    if (!cityGraph.containsKey(city1)) {
                        cityGraph.put(city1, new ArrayList<>());
                    }
                    if (!cityGraph.containsKey(city2)) {
                        cityGraph.put(city2, new ArrayList<>());
                    }

                    cityGraph.get(city1).add(new DistanceTo(city2, distance));
                    cityGraph.get(city2).add(new DistanceTo(city1, distance));
                }
            }
            keyboard.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the starting city:");
        String startingCity = keyboard.nextLine().trim();

        //String startingCity = cityGraph.keySet().iterator().next();
        Map<String, Integer> shortestDistances = findShortestPaths(cityGraph, startingCity);

        System.out.println("Shortest distances from " + startingCity + ":");
        for (String city : shortestDistances.keySet()) {
            System.out.println(city + ": " + shortestDistances.get(city));
        }
    }

    private static Map<String, Integer> findShortestPaths(Map<String, List<DistanceTo>> graph, String startCity) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<DistanceTo> pq = new PriorityQueue<>();

        pq.add(new DistanceTo(startCity, 0));

        while (!pq.isEmpty()) {
            DistanceTo current = pq.poll();

            if (distances.containsKey(current.city)) {
                continue;
            }

            distances.put(current.city, current.distance);

            for (DistanceTo neighbor : graph.getOrDefault(current.city, new ArrayList<>())) {
                if (!distances.containsKey(neighbor.city)) {
                    int newDist = current.distance + neighbor.distance;
                    pq.add(new DistanceTo(neighbor.city, newDist));
                }
            }
        }
        return distances;
    }
}