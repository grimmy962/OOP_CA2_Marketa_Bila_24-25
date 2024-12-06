package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */

public class Question6      // Flight take-off (Queue)
{
    public static void simulateAirport(){
        Scanner keyboard = new Scanner(System.in);
        Queue<String> landingQueue = new LinkedList<>();
        Queue<String> takeOffQueue = new LinkedList<>();
        String command;

        System.out.println("Please enter command: \n'takeoff flightCode', 'land flightCode', 'next', 'quit' ");
        while(true){
            System.out.println("> ");
            command = keyboard.nextLine().trim();

            if(command.equalsIgnoreCase("quit")){
                System.out.println("Simulation has ended");
                break;
            }

            else if(command.startsWith("takeoff")) {
                String flightCode = command.substring(8).trim();
                if (!flightCode.isEmpty()) {
                    takeOffQueue.add(flightCode);
                    System.out.println(flightCode + " queued for takeoff");
                } else {
                    System.out.println("Invalid command");
                }
            }
            else if(command.startsWith("land")){
                String flightCode = command.substring(5).trim();
                if(!flightCode.isEmpty()){
                    landingQueue.add(flightCode);
                    System.out.println(flightCode + "queued for landing");
                }
                else{
                    System.out.println("Invalid command.");
                }
            }
            else if(command.equalsIgnoreCase("next")){
                if(!landingQueue.isEmpty()){
                    System.out.println("Landing: " + landingQueue.poll());
                }
                else if(!takeOffQueue.isEmpty()){
                    System.out.println("Take off: " + takeOffQueue.poll());
                }
                else{
                    System.out.println("No flights waiting for to land or take off");
                }
                }
            else{
                System.out.println("Invalid command");
            }
            }
        keyboard.close();
        }


    public static void main(String[] args)
    {
        simulateAirport();
    }
}
