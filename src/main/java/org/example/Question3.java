package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */

//TODO: After being done with the questions, add the though process that's on the paper
public class Question3  {   //Nested HTML (Stack)

    /*
filename: name of the file to test.
*/
    public static boolean validate(String filename) throws FileNotFoundException
    {
        Stack<String> tagStack = new Stack<>();
        File file = new File(filename);
        Scanner keyboard = new Scanner(file);

        //read all those tags, store them and then check for the closing tag
        while(keyboard.hasNext()){
            String tag = keyboard.next();
            if(tag.startsWith("</")){
                if(tagStack.isEmpty() || !tagStack.pop().equals(tag.substring(2, tag.length() - 1))){
                    keyboard.close();
                    return false;
                }
            }
            //
            else if(tag.startsWith("<") && tag.endsWith(">") && !tag.equals("<br>")){
                tagStack.push(tag.substring(1, tag.length()-1));
            }
        }
        keyboard.close();
        return tagStack.isEmpty();
    }

    /*
        This function tests the files in the files array to see if
         they are valid.
         tags_valid.txt should return true;
         tags_invalid.txt should output as invalid;


     */
    public static void main(String[] args) throws FileNotFoundException {
        String[] files = {"tags_valid.txt", "tags_invalid.txt"};
        for(String fName: files) {
            System.out.print(fName +": ");
            if (validate(fName)) {
                System.out.println("This file is valid");
            } else {
                System.out.println("This file is invalid");
            }
        }
    }



}


