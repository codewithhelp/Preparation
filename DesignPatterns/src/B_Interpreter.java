/*
 * it for mainly for evaluate language grammar or expression
 * 
 * context : information that'll be interpreted
 * terminal 
 * expression: abstract class, that'll define all methods need to perform different conversions
 */
public class B_Interpreter {
	public static void main(String[] args) {
		Expression person1 = new TerminalExpression("Kushagra");
		Expression person2 = new TerminalExpression("Lokesh");
		Expression isSingle = new OrExpression(person1, person2);

		Expression vikram = new TerminalExpression("Vikram");
		Expression committed = new TerminalExpression("Committed");
		Expression isCommitted = new AndExpression(vikram, committed);

		System.out.println(isSingle.interpreter("Kushagra"));
		System.out.println(isSingle.interpreter("Lokesh"));
		System.out.println(isSingle.interpreter("Achint"));

		System.out.println(isCommitted.interpreter("Committed, Vikram"));
		System.out.println(isCommitted.interpreter("Single, Vikram"));
	}

	interface Expression {
		boolean interpreter(String con);
	}

	static class TerminalExpression implements Expression {
		String data;

		public TerminalExpression(String data) {
			this.data = data;
		}

		@Override
		public boolean interpreter(String con) {
			if (con.contains(data))
				return true;
			return false;
		}

	}

	static class OrExpression implements Expression {
		Expression one, two;

		public OrExpression(Expression one, Expression two) {
			this.one = one;
			this.two = two;
		}

		@Override
		public boolean interpreter(String con) {
			return one.interpreter(con) || two.interpreter(con);
		}
	}

	static class AndExpression implements Expression {
		Expression one, two;

		public AndExpression(Expression one, Expression two) {
			this.one = one;
			this.two = two;
		}

		@Override
		public boolean interpreter(String con) {
			return one.interpreter(con) && two.interpreter(con);
		}
	}

}
