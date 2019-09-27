import java.util.*;

public class Game24 {
	public static void main(final String... args) {
		final Scanner scanner = new Scanner(System.in);

		final int[] digits = {1, 2, 4, 8};

		System.out.print("Make 24 using these digits: ");
		for (int digit: digits) System.out.print(digit + " ");
		System.out.print("\n >");

		final RPNParser parser = new RPNParser();
		parser.parse(scanner.nextLine());

		double res = parser.getResult();

		if (Math.abs(res - 24.0) < 0.001) {
			System.out.println("Good job!");
		} else {
			System.out.println("Try again.");
		}

		scanner.close();
	}
}

class RPNParser {

	public final String ops = "+/-*";
	public final Stack<Double> stack = new Stack<>();

	public void parse(String s) {
		for (char ch: s.toCharArray()) {
			parse(ch);
		}
	}

	private void parse(final char c) {
		if (c >= '0' && c <= '9') {
			stack.push((double)(c - '0'));
		} else if (ops.indexOf(c) != -1) {
			stack.push(calc(stack.pop(), stack.pop(),c));
		}
	}

	private double calc(final double a, final double b, final char op) {
		switch (op) {
			case '+': 
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				return a / b;
			default:
				return Double.NaN;
		}
	}

	public double getResult() {
		return stack.peek();
	}

}
