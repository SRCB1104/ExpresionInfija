import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static boolean EsOperador(String token) {
        return "+-*/".contains(token);
    }

    public static int anterior(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la expresion: ");
        String exp = sc.nextLine();

        StringTokenizer st = new StringTokenizer(exp, " ()-+*/", true);

        while (st.hasMoreElements()) {
            String token = st.nextToken().trim();

            if (token.isEmpty()) {
                continue;
            }

            if (token.matches("[0-9]+")) {
                sb.append(token).append(" ");
            } else if (EsOperador(token)) {
                while (!stack.isEmpty() && EsOperador(stack.peek()) &&
                        anterior(token) <= anterior(stack.peek())) {
                    sb.append(stack.pop()).append(" ");
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    sb.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    System.out.println("expresion incorrecta o falta algo");
                    return;
                }
            } else {
                System.out.println("expresion incorrecta");
                return;
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                System.out.println("expresion incorrecta o falta algo");
                return;
            }
            sb.append(stack.pop()).append(" ");
        }

        System.out.println("Expresion correcta");
        System.out.println("Expresion posfija: " + sb);
    }
}
