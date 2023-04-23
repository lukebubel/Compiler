/*
import java.util.Stack;
import java.util.Scanner;
public class Compiler {
     public static void main(String[] args) {
        String input;
        Scanner myObj = new Scanner(System.in);
        System.out.println("input athermatic expression");
        
        input = myObj.nextLine();
       // String input = "3 + 4 * 8 / ( 1 - 5 ) ^ 9 ^ 3";
        String[] tokens = input.split("\\s+");
        Stack<String> output = new Stack<String>();
        Stack<String> operators = new Stack<String>();

        for (String token : tokens) {
            if (isNumber(token)) {
                System.out.print("isNumber(token)\t");
                output.push(token);
            } else if (isOperator(token)) {
                while (!operators.empty() && precedence(token) <= precedence(operators.peek())) {
                    output.push(operators.pop());
                }
                System.out.println("isOperator(token)\t");
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
                System.out.println("token.equals(\"(\"))\t");
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    output.push(operators.pop());
                    System.out.println("token.equals(\")\"\t");
                }
                operators.pop();
            }
        }

        while (!operators.empty()) {
            output.push(operators.pop());
        }

        Stack<Double> values = new Stack<Double>();

        for (String token : output) {
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                double b = values.pop();
                double a = values.pop();
                switch (token) {
                    case "+":
                        values.push(a + b);
                        break;
                    case "-":
                        values.push(a - b);
                        break;
                    case "*":
                        values.push(a * b);
                        break;
                    case "/":
                        values.push(a / b);
                        break;
                }
            }
        }

        System.out.println(values.pop());
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return 0;
        }
    }
}
*/
// import necessary libraries
import java.util.Stack;
import java.util.Scanner;

// declare the Compiler class
public class Compiler {
    // define the main method
    public static void main(String[] args) {
        // declare and initialize variables
        String input;  // stores the user input
        Scanner myObj = new Scanner(System.in);  // initialize a new Scanner object
        System.out.println("input athermatic expression");  // prompt the user for input
        
        // read the user input
        input = myObj.nextLine();
        
        // tokenize the input and store the tokens in an array
        String[] tokens = input.split("\\s+");
        
        // initialize two stacks to store output and operators
        Stack<String> output = new Stack<String>();
        Stack<String> operators = new Stack<String>();
        
        // iterate over the tokens array
        for (String token : tokens) {
            // if the token is a number, push it to the output stack
            if (isNumber(token)) {
                System.out.print("isNumber(token)\t");  // debug message
                output.push(token);
            // if the token is an operator, handle it accordingly
            } else if (isOperator(token)) {
                // while there are operators on the stack and the current operator has lower or equal precedence
                while (!operators.empty() && precedence(token) <= precedence(operators.peek())) {
                    // pop operators from the stack and push them to the output stack
                    output.push(operators.pop());
                }
                System.out.println("isOperator(token)\t");  // debug message
                // push the current operator to the operator stack
                operators.push(token);
            // if the token is a left parenthesis, push it to the operator stack
            } else if (token.equals("(")) {
                operators.push(token);
                System.out.println("token.equals(\"(\"))\t");  // debug message
            // if the token is a right parenthesis, handle it accordingly
            } else if (token.equals(")")) {
                // while the top of the operator stack is not a left parenthesis
                while (!operators.peek().equals("(")) {
                    // pop operators from the stack and push them to the output stack
                    output.push(operators.pop());
                    System.out.println("token.equals(\")\"\t");  // debug message
                }
                // pop the left parenthesis from the operator stack
                operators.pop();
            }
        }
        
        // after all tokens have been processed, pop any remaining operators from the operator stack to the output stack
        while (!operators.empty()) {
            output.push(operators.pop());
        }
        
        // initialize a new stack to store the numerical values
        Stack<Double> values = new Stack<Double>();
        
        // iterate over the tokens in the output stack
        for (String token : output) {
            // if the token is a number, push its double value to the values stack
            if (isNumber(token)) {
                values.push(Double.parseDouble(token));
            // if the token is an operator, perform the corresponding operation on the top two values in the values stack
            } else if (isOperator(token)) {
                double b = values.pop();
                double a = values.pop();
                switch (token) {
                    case "+":
                        values.push(a + b);
                        break;
                    case "-":
                        values.push(a - b);
                        break;
                    case "*":
                        values.push(a * b);
                        break;
                    case "/":
                        values.push(a / b);
                        break;
                }
            }
        }
        
        // print the final result, which should be the only element left in
