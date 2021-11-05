import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
        Stack<Integer> pilha = new Stack<>();
		try {
			File text = new File("Calc1.stk");
            Scanner file_scanner = new Scanner(text);
			while(file_scanner.hasNext()) {
				String input_string = file_scanner.next();
				if(input_string.matches("-?\\d+"))
					pilha.push(Integer.parseInt(input_string));
				else {
                    Integer operation_result = make_operation(pilha.pop(), pilha.pop(), input_string.charAt(0));
					pilha.push(operation_result);
				}
			}
			file_scanner.close();
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