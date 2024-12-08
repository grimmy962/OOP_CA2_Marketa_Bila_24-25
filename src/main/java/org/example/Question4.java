package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */
//

public class Question4  // Flood Fill (Stack, 2D Array)
{
    static final int ROWS = 5;
    static final int COLUMNS = 5;

    public static void main(String[] args) {
        start();
    }
    public static void start()
    {


        //set up variables
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

    public static int[][]  floodFillStart() {
        // Starter matrix (2D Array) with 0 representing an empty cell,
        // and -1 representing a wall. Flood fill can not cross through
        // a wall ( and not pass through diagionally).
        //
        int[][] matrix = new int[ROWS][COLUMNS]; // 2D Array of int
        // define values for each row, -1 to prevent change
        matrix[0] = new int[]{ 0, 0, -1, -1, 0};
        matrix[1] = new int[]{ 0, 0, -1, -1, 0};
        matrix[2] = new int[]{-1, 0,  0,  0, 0};
        matrix[3] = new int[]{-1, 0, -1, -1, 0};
        matrix[4] = new int[]{ 0,-1, -1,  0, 0};

        return matrix;
    }
    /*
        Helper function to display the image
     */
    public static void display(int[][] arr)
    {
        for (int x = 0; x < ROWS; x++)
        {
            for (int y = 0; y < COLUMNS; y++)
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

            //check if the cell is filled or not
            if(current.row < 0 || current.row >=5 || current.col < 0 || current.col >=5 || arr[current.row][current.col] !=0){
                continue;
            }

            //something to fill the cell and add a number to it
            arr[current.row][current.col] = fillValue++;

            //if statement for each of the directions and to check which way to go plus if one of the directions was chosen already, go the other direction
            //add movement based on the location, north, south, east, west
            //north
            stack.push(new Cell(current.row -1, current.col));
            //south
            stack.push(new Cell(current.row +1, current.col));
            //west
            stack.push(new Cell(current.row, current.col -1));
            //east
            stack.push(new Cell(current.row, current.col +1));
        }
    }

}
