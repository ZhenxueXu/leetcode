package solution;

import java.util.Stack;

public class EvaluteReversePolishNotation {
	public static void main(String[] args){
		String[] tokens = {"0", "3", "/"};
		EvaluteReversePolishNotation evaluteReversePolishNotation = new EvaluteReversePolishNotation();
		System.out.println(evaluteReversePolishNotation.evalRPN(tokens));

	}
	public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0){
			return 0;
		}
		Stack<Integer> value = new Stack<>();
		int left = 0;
		int right = 0;
		int  result = 0;
		for (String token : tokens) {
			switch (token){
				case "+":
					right = value.pop();
					left = value.pop();
					result = left + right;
					value.push(result);
					break;
				case "-":
					right = value.pop();
					left = value.pop();
					result = left - right;
					value.push(result);
					break;
				case "*":
					right = value.pop();
					left = value.pop();
					result = left * right;
					value.push(result);
					break;
				case "/":
					right = value.pop();
					left = value.pop();
					result = left / right;
					value.push(result);
					break;
					default:
						value.push(Integer.valueOf(token));
			}
		}


		return value.pop();
	}
}
