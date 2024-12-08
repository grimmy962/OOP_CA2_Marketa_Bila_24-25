package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */

/*
add(element): Adds an element to the rear of the queue. If the queue is full, it throws an exception.
offer(element): Adds an element to the rear of the queue. If the queue is full, it returns false.
remove(): Removes and returns the element at the front of the queue. If the queue is empty, it throws an exception.
poll(): Removes and returns the element at the front of the queue. If the queue is empty, it returns null.
element(): Returns the element at the front of the queue without removing it. If the queue is empty, it throws an exception.
peek(): Returns the element at the front of the queue without removing it. If the queue is empty, it returns null.
 */
public class Question7  // Shares Tax Calculations (Queue)
{

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */
    public static void main(String[] args) {
        Queue<Block> stockQueue = new LinkedList<>();
        Scanner keyboard = new Scanner(System.in);
        String command="";
        System.out.println("Enter command: \n'buy qty price', 'sell qty', 'quit'");

        do {
            System.out.print(">");
            command = keyboard.next();

            if(command.equalsIgnoreCase("buy"))
            {
                int qty = keyboard.nextInt();
                double price = keyboard.nextDouble();
                stockQueue.add(new Block(qty, price));
                System.out.println("Bought "+qty+"shares at $"+price+"each");
            }
            else if(command.equals("sell"))
            {
                if(stockQueue.isEmpty()){
                    System.out.println("No shares to sell");
                    continue;
                }

                int qtyToSell = keyboard.nextInt();
                double sellingPrice = keyboard.nextDouble();
                double totalGain = 0;
                double taxRate = 0.33;

                //gainBeforeTax=qtyToSell*sellingPrice;
                //gainAfterTax=gainBeforeTax*tax;

                while(qtyToSell > 0 && !stockQueue.isEmpty()){
                    Block block = stockQueue.peek();

                    if(block.quantity<=qtyToSell){
                        qtyToSell -=block.quantity;
                        totalGain +=block.quantity * (sellingPrice- block.price);
                        stockQueue.poll();
                    }
                    else {
                        totalGain += qtyToSell * (sellingPrice - block.price);
                        block.quantity -=qtyToSell;
                        qtyToSell=0;
                    }
                }

                if(qtyToSell >0){
                    System.out.println("Not enough shares to sell");
                }
                else{
                    double tax = totalGain * taxRate;
                    double gainAfterTax = totalGain - tax;
                    System.out.println("Total gain before tax: "+ totalGain);
                    System.out.println("Tax(33%): "+ tax);
                    System.out.println("Total gain after tax: " + gainAfterTax);
                }
            }
            else if(!command.equalsIgnoreCase("quit")){
                System.out.println("Invalid command");
            }

        }
            while(!command.equalsIgnoreCase("quit"));
            System.out.println("Simulation has ended");
            keyboard.close();
    }
}