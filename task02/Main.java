import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws UnrecognizedCharacterException {
        Stack<Integer> pilha = new Stack<>();
        LinkedList<Token> token_ll = new LinkedList<>();
		try {
			File text = new File("Calc1.stk");
            Scanner file_scanner = new Scanner(text);
			while(file_scanner.hasNext()) {
				String input_string = file_scanner.next();
				if(input_string.matches("-?\\d+"))
                    token_ll.add(new Token(TokenType.NUM, input_string)); 
				else {
                    char op = input_string.charAt(0);
                    TokenType op_type;
                    if (op == '+') op_type = TokenType.PLUS;
                    else if (op == '-') op_type = TokenType.MINUS;
                    else if (op == '*') op_type = TokenType.STAR;
                    else if (op == '/') op_type = TokenType.SLASH;
                    else throw new UnrecognizedCharacterException("Error: Unexpected character: "+ String.valueOf(op));
                    token_ll.add(new Token(op_type, input_string));
				}
			}
			file_scanner.close();
            token_ll.forEach((current_token) -> {
				System.out.println(current_token.toString());
			    if(current_token.type != TokenType.NUM) {
                    Integer operation_result = make_operation(pilha.pop(), pilha.pop(), current_token.lexeme.charAt(0));
					pilha.push(operation_result);
			    }else{
                    pilha.push(Integer.parseInt(current_token.lexeme));
			    }
			});
			System.out.println("Operation output: " + pilha.peek());
		}catch (FileNotFoundException e) {
		      System.out.println("No file was found in the corresponding path.");
	    }
	}
	public static Integer make_operation(Integer a, Integer b, char operator) {
        if (operator == '-')
            return a - b;
        else if (operator == '+')
            return a + b;
        else if (operator == '*')
            return a * b;
        else if (operator == '/')
            return a / b;
        else {
            System.out.println("Operator not recognized");
            return 0;
            }
	}

}