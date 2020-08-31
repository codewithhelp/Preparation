
/*
 * Way to Construct/Create complex objects
 * We can use builder to set properties of a product, and product will not have setters :: immutability
 * 
 * Adv: We can reduce a parameters to constructors, easily add/remove fields, easily understandable/maintainable
 * Dis: Code increases as to maintain builder class
 * 
 * Ref: https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
 * */

public class C_Builder {

	public static void main(String[] args) {

		Phone phone = new PhoneBuilder().name("Nokia").os("Symbion").buildPhone();
		phone.printPhone();

		// Without Builder pattern
		Phone p1 = new Phone("Microsoft", "Windows", false);
		p1.printPhone();

	}

	static class PhoneBuilder {
		String name, os;
		boolean foldType;

		public PhoneBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PhoneBuilder os(String os) {
			this.os = os;
			return this;
		}

		// Approach1 :: Pass all the parameter
		public Phone buildPhone() {
			return new Phone(this.name, this.os, this.foldType);
		}

		// Approach2 :: Pass entire object as a parameter, object contains parameters
		// public Phone getPhone1() {
		// return new Phone(this);
		// }

	}

	static class Phone {
		private String name;
		private String os;
		private boolean foldType;

		Phone(String name, String os, boolean foldType) {
			this.name = name;
			this.os = os;
			this.foldType = foldType;
		}

		public void printPhone() {
			System.out.println("Name: " + this.name + ", OS:" + this.os + ", FoldType:" + this.foldType);
		}

		public String getName() {
			return this.name;
		}

	}
}
