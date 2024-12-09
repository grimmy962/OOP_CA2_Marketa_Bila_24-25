package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *  Name: Marketa Bila
 *  Class Group: GD2A
 */
public class Question9 {

    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        String equation;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter equation");
        equation = keyboard.nextLine().trim();

        try{
            List<String> postfix = convertToPostFix(equation);
            double result = evaluatePostFix(postfix);
            System.out.println("The result is: "+result);
        }
        catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        keyboard.close();
    }

    private static List<String> convertToPostFix(String expression) {
        Stack<Character> operators = new Stack<>();
        List<String> postfix = new ArrayList<>();
        String number = "";

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {
                continue;
            }
            if (Character.isDigit(ch) || ch == '.') {
                number += ch;
            } else {
                if (!number.isEmpty()) {
                    postfix.add(number);
                    number = "";
                }

                if (ch == '(') {
                    operators.push(ch);
                } else if (ch == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        postfix.add(String.valueOf(operators.pop()));
                    }
                    if (operators.isEmpty()) {
                        throw new IllegalArgumentException("Mismatched parentheses");
                    }
                    operators.pop();
                } else if (isOperator(ch)) {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                        postfix.add(String.valueOf(operators.pop()));
                    }
                    operators.push(ch);
                } else {
                    throw new IllegalArgumentException("Invalid character: " + ch);
                }
            }
        }

        if (!number.isEmpty()) {
            postfix.add(number);
        }
        while (!operators.isEmpty()) {
            char op = operators.pop();
            if (op == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            postfix.add(String.valueOf(op));
        }
        return postfix;
    }

    private static double evaluatePostFix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();

        for(String token : postfix){
            if(isNumber(token)){
                stack.push(Double.parseDouble(token));
            }
            else if(isOperator(token.charAt(0))){
                if(stack.size()<2){
                    throw new IllegalArgumentException("Invalid expression");
                }

                double b = stack.pop();
                double a = stack.pop();

                switch (token.charAt(0)){
                    case '+' : stack.push(a + b);
                        break;
                    case '-' : stack.push(a - b);
                        break;
                    case '*' : stack.push(a * b);
                        break;
                    case '/':
                        if(b==0) throw new IllegalArgumentException("Division by zero");
                        stack.push(a/b);
                        break;
                }
            }
            else{
                throw new IllegalArgumentException("Unknown token: "+token);
            }
        }
        if(stack.size() !=1){
            throw new IllegalArgumentException("Invalid postfix evaluaiton");
        }
        return stack.pop();
    }


    private static boolean isOperator(char ch){
        return ch == '+' || ch== '-' || ch== '*' || ch =='/';
    }

    private static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private static int precedence(char op){
        if(op=='+' || op== '-')
            return 1;
        if(op =='*'|| op== '/')
            return 2;
        return 0;
    }
}
