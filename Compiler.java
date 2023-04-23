
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
