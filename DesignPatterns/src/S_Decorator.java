/*
 * add new functionality to an existing object, without altering its structure
 * 
 * decorator is similar to subclassing, decorator is at runtime, subclassing at compiler time
 * 
 * */
public class S_Decorator {
	public static void main(String[] args) {

		Pizza pizza = new SimplePizza();
		System.out.println(pizza.getDescription());
		System.out.println(pizza.getCost());

		Pizza pannerPizza = new Paneer(pizza);
		System.out.println(pannerPizza.getDescription());
		System.out.println(pannerPizza.getCost());

	}

	static abstract class Pizza {
		String desc = "Unknown";

		abstract int getCost();

		public String getDescription() {
			return desc;
		}
	}

	static abstract class ToppingsDecorator extends Pizza {
		abstract public String getDescription();
	}

	static class SimplePizza extends Pizza {
		SimplePizza() {
			this.desc = "SimplePizza";
		}

		@Override
		int getCost() {
			return 10;
		}
	}

	static class Paneer extends ToppingsDecorator {
		Pizza pizza;

		Paneer(Pizza pizza) {
			this.pizza = pizza;
		}

		@Override
		public String getDescription() {
			return this.pizza.getDescription() + " decorated with paneer";
		}

		@Override
		int getCost() {
			return 25 + this.pizza.getCost();
		}

	}
}
