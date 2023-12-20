import java.util.*;

public class Q05 {
    
    public static void main(String[] args) {
        while(true){
        String input = MyIO.readLine();
        if(input.equals("0"))
            break;
        boolean result = evaluateExpression(input);
        System.out.println(result ? "1" : "0");
        }
    }

    public static boolean evaluateExpression(String input) {
        String[] tokens = input.split(" ");
        int n = Integer.parseInt(tokens[0]);
        //pilha criada para liader com as expressões
        Stack<Boolean> stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            boolean value = Integer.parseInt(tokens[i]) == 1;
            stack.push(value);
        }

        for (int i = n + 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("and")) {
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
                boolean result = tempStack.pop();
                while (!tempStack.isEmpty()) {
                    result = tempStack.pop() && result;
                }
                stack.push(result);
            }
        }

        return stack.pop();
    }

    
}
