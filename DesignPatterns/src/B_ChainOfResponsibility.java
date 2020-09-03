/*
 * chain of receiver objects for a request
 * 
 * each receiver contains reference to other receiver, if one object cannot handle the request, then
 * it passes the same object to next receiver
 * 
 * kind of if..else if..else if..else if..end if
 * 
 * Adv:
 * 	->decouple a requests sender and receiver
 */

public class B_ChainOfResponsibility {

	public static void main(String[] args) {

		Chain chain1 = new AddNumbers();
		Chain chain2 = new MulNumbers();
		Chain chain3 = new DivNumbers();

		chain1.setNextChain(chain2);
		chain2.setNextChain(chain3);

		Numbers req = new Numbers(10, 20, "add");
		chain1.calculate(req);

		Numbers req1 = new Numbers(10, 20, "mul");
		chain1.calculate(req1);

		Numbers req2 = new Numbers(10, 20, "div");
		chain1.calculate(req2);

		Numbers req3 = new Numbers(10, 20, "sub");
		chain1.calculate(req3);
	}

	interface Chain {
		void setNextChain(Chain nextChain);

		void calculate(Numbers req);
	}

	static class Numbers {
		private int num1, num2;
		String calulationWanted = "";

		public Numbers(int num1, int num2, String calculationWanted) {
			this.num1 = num1;
			this.num2 = num2;
			this.calulationWanted = calculationWanted;
		}

		public int getNum1() {
			return this.num1;
		}

		public int getNum2() {
			return this.num2;
		}

		public String getCalculationWanted() {
			return this.calulationWanted;
		}
	}

	static class AddNumbers implements Chain {

		private Chain nextChain;

		@Override
		public void setNextChain(Chain nextChain) {
			this.nextChain = nextChain;
		}

		@Override
		public void calculate(Numbers req) {
			if (req.getCalculationWanted().equals("add"))
				System.out.println("Add of " + req.num1 + " and " + req.num2 + " is " + (req.num1 + req.num2));
			else
				nextChain.calculate(req);
		}

	}

	static class MulNumbers implements Chain {

		private Chain nextChain;

		@Override
		public void setNextChain(Chain nextChain) {
			this.nextChain = nextChain;
		}

		@Override
		public void calculate(Numbers req) {
			if (req.getCalculationWanted().equals("mul"))
				System.out.println("Mul of " + req.num1 + " and " + req.num2 + " is " + (req.num1 * req.num2));
			else
				nextChain.calculate(req);
		}

	}

	static class DivNumbers implements Chain {

		private Chain nextChain;

		@Override
		public void setNextChain(Chain nextChain) {
			this.nextChain = nextChain;
		}

		@Override
		public void calculate(Numbers req) {
			if (req.getCalculationWanted().equals("div"))
				System.out.println("Div of " + req.num1 + " and " + req.num2 + " is " + (req.num1 / req.num2));
			else
				System.out.println("Support only for add/mul/div");
		}

	}
}
