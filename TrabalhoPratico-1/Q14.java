import java.util.*;

public class Q14 {
    
    public static void main(String[] args) {
        while (true) {
            String input = MyIO.readLine();
            if (input.equals("0"))
                break;
            boolean result = evaluateExpression(input);
            System.out.println(result ? "1" : "0");
        }
    }

    public static boolean evaluateExpression(String input) {
        String[] tokens = input.split(" ");
        // pilha criada para lidar com as expressões
        Stack<Boolean> stack = new Stack<>();
        int currentIndex = 1;
        
        while (currentIndex < tokens.length) {
            String token = tokens[currentIndex++];
            if (token.equals("1") || token.equals("0")) {
                boolean value = Integer.parseInt(token) == 1;
                stack.push(value);
            } else if (token.equals("and")) {
                boolean operand2 = stack.pop();
                boolean operand1 = stack.pop();
                stack.push(operand1 && operand2);
            } else if (token.equals("or")) {
                boolean operand2 = stack.pop();
                boolean operand1 = stack.pop();
                stack.push(operand1 || operand2);
            } else if (token.equals("not")) {
                boolean operand = stack.pop();
                stack.push(!operand);
            } else if (token.equals("(")) {
                // Empilha um marcador de parênteses abertos
                stack.push(null);
            } else if (token.equals(")")) {
                // Avalia a expressão dentro dos parênteses
                Stack<Boolean> tempStack = new Stack<>();
                while (stack.peek() != null) {
                    tempStack.push(stack.pop());
                }
                stack.pop(); // Remove o marcador de parênteses abertos
                boolean result = evaluateInsideParentheses(tempStack);
                stack.push(result);
            }
        }

        return stack.pop();
    }

    public static boolean evaluateInsideParentheses(Stack<Boolean> stack) {
        boolean result = stack.pop();
        while (!stack.isEmpty()) {
            result = stack.pop() && result;
        }
        return result;
    }
}
