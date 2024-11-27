package org.example;

import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */

//TODO: 1) Make two Stacks: for driveway and street (both integer).
//      2) 3 input options: Positive number (to add car), Negative (to get rid of it), 0 (to stop simulation).
//      3) After each operation, print out the current state of the stack

//things to remember: push() = place the element at the top of the stack,
//                    peek() = to fetch or retrieve the first element present at the top (doesn't get deleted from the stack)
//                    pop() = to remove element from the top
//                    empty() = returns true if nothing is on the top of the stack
//                    search() = determines if the object exists in the stack, if yes returns position from the top, else returns -1
public class Question2  // Car Parking - Stack
{
    public static void runSimulation()
    {
    //add scanner to read user's input
    Scanner keyboard = new Scanner(System.in);

    //make the two stacks for driveway and street
    Stack<Integer> driveway = new Stack<>();
    Stack<Integer> street = new Stack<>();

    //ask user for input
    System.out.println("Please enter one of these commands: positive number to park, negative to retrieve, 0 to stop");

        //consider all the inputs, print the state
        while(true) {
            int command = keyboard.nextInt();

            //0 to stop simulation
            if(command == 0){
                System.out.println("Simulation stopped.");
                break;
            }

            if(command > 0){
                //add a car to the driveway
                driveway.push(command);
                System.out.println("Car "+command + "parked in the driveway.");
            }

            else{
                //remove a car from the driveway
                int carToRetrieve = -command;

                if(driveway.contains(carToRetrieve)){
                    //move the cars that are blocking the desired car to the street
                    while(driveway.peek() != carToRetrieve) {
                        int movedCar = driveway.pop();
                        street.push(movedCar);
                        System.out.println("Car " + movedCar + "moved to the street.");
                    }

                    //remove the car we want
                    driveway.pop();
                    System.out.println("Car "+carToRetrieve+"retrieved from the driveway.");

                    //move the cars that are on the street to the driveway
                    while(!street.isEmpty()){
                        int movedCar = street.pop();
                        driveway.push(movedCar);
                        System.out.println("Car " + movedCar+"move dback to the driveway.");
                    }
                }

            else{
                    System.out.println("Car "+ carToRetrieve+ "is not in the driveway.");
                }
            }
            //current state of the driveway
            System.out.println("Current state of the driveway: "+driveway);
    }
        keyboard.close();
}


    public static void main(String[] args) {
        runSimulation();
    }
}
