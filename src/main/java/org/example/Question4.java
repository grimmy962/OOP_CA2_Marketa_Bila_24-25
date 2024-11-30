package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */

public class Question4  // Flood Fill (Stack, 2D Array)
{
    public static void main(String[] args) {
        start();
    }
    public static void start()
    {
        //set up valunbles
        int[][] arr = floodFillStart();
        Scanner keyboard = new Scanner(System.in);
        int rowStart = 0;
        int colStart = 0;

        //ask the user for an input
        System.out.println("Please enter the starting row: 0 - 9");
        rowStart= keyboard.nextInt();

        System.out.println("Please enter the starting column: 0 - 9");
        colStart = keyboard.nextInt();

        floodFill(rowStart, colStart, arr);
        System.out.println("The final filled 2d array:");
        display(arr);
    }

    /*
        Starter function to create the 2D array and populate it with zeros
     */

    //make the 2d array, put everything at 0 to change it later
    public static int[][]  floodFillStart() {
        Scanner kb = new Scanner(System.in);
        int[][] arr = new int[10][10];
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                arr[x][y] = 0;
            }
        }
        return arr;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                System.out.printf("%4d", arr[x][y]);
            }
            System.out.println();
        }
    }
    private static void floodFill(int r, int c, int[][] arr)
    {
        Stack<Cell> stack = new Stack<>();
        stack.push(new Cell(r,c));
        int fillValue = 1;

        while(!stack.isEmpty()){
            Cell current = stack.pop();

            //check if the the cell is filled or not
            if(current.row < 0 || current.row >=10 || current.col < 0 || current.col >=10 || arr[current.row][current.col] !=0){
                continue;
            }

            //something to fill the cell and add a number to it
            arr[current.row][current.col] = fillValue++;

            //add movement based on the location, t
            stack.push(new Cell(current.row -1, current.col));
            stack.push(new Cell(current.row +1, current.col));
            stack.push(new Cell(current.row, current.col -1));
            stack.push(new Cell(current.row, current.col +1));
        }
    }

}
