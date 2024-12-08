package org.example;

import java.util.*;
/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */
public class Question8 { // Multi-company (Queue)
    /*
   Will repeatedly ask the user to enter the commands in the format
   buy company qty price
   or
   sell company qty price
   or
   quit
    */
    public static void main(String[] args) {
        Map<String, Queue<Block>> stockMap = new HashMap<>();
        Scanner keyboard = new Scanner(System.in);
        String command = "";

        System.out.println("Enter command: \n'buy symbol qty price', 'sell symbol qty price', 'quit'");
        do {
            System.out.print("> ");
            command = keyboard.next();

            if (command.equalsIgnoreCase("buy")) {
                String company = keyboard.next();
                int qty = keyboard.nextInt();
                double price = keyboard.nextDouble();

                stockMap.putIfAbsent(company, new LinkedList<>());
                stockMap.get(company).add(new Block(qty, price));
                System.out.printf("Bought %d shares of %s at $%.2f each%n", qty, company, price);
            }
            else if (command.equalsIgnoreCase("sell")) {
                String company = keyboard.next();
                int qtyToSell = keyboard.nextInt();
                double sellingPrice = keyboard.nextDouble();


                if (!stockMap.containsKey(company) || stockMap.get(company).isEmpty()) {
                    System.out.printf("No shares available for %s to sell.%n", company);
                    continue;
                }

                Queue<Block> stockQueue = stockMap.get(company);
                double totalGain = 0;
                double taxRate = 0.33;


                while (qtyToSell > 0 && !stockQueue.isEmpty()) {
                    Block block = stockQueue.peek();

                    if (block.quantity <= qtyToSell) {
                        qtyToSell -= block.quantity;
                        totalGain += block.quantity * (sellingPrice - block.price);
                        stockQueue.poll();
                    }
                    else { // Sell part of the block
                        totalGain += qtyToSell * (sellingPrice - block.price);
                        block.quantity -= qtyToSell;
                        qtyToSell = 0;
                    }
                }


                if (qtyToSell > 0) {
                    System.out.printf("Not enough shares of %s to sell. Remaining unsold: %d%n", company, qtyToSell);
                }
                else {
                    double tax = totalGain * taxRate;
                    double gainAfterTax = totalGain - tax;

                    System.out.printf("Total gain before tax for %s: $%.2f%n", company, totalGain);
                    System.out.printf("Tax for %s: $%.2f%n", company, tax);
                    System.out.printf("Total gain after tax for %s: $%.2f%n", company, gainAfterTax);
                }
            }
            else if (!command.equalsIgnoreCase("quit")) {
                System.out.println("Invalid command. Please try again.");
            }
        }
        while (!command.equalsIgnoreCase("quit"));

        System.out.println("Simulation has ended.");
        keyboard.close();
    }
}