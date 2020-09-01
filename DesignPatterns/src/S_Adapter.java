/*
 * Adapter works as a bridge between two incompatible interfaces
 * real world examples : 
 * 	-> cardreader : (memory card -> laptop)
 *  -> india plug adapter: (usa charger -> laptop)
 *  
 *  Java Exmaple:
 *  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 *  InputStreamReader as a adapter between System.in and BufferedReader
 *  
 *  Object adaptor is used, as in adapter we create object.
 *  Class adaptor requires multiple inheritence, so we don;t use
 *
 *	Adv: Reusable/Flexible/Compatibility with client interfaces
 *	Dis: all requests forward from adapter, so slight increase of overload
 *
 *
 *	MyNotes:
 *		class InputAdapter implements Output
 *		output = inputadapter(input)
 *
 *
 *
 * */
public class S_Adapter {

	public static void main(String[] args) {

		Bird sparrow = new Sparrow();
		ToyDuck toyDuck = new PlasticToyDuck();

		ToyDuck birdAdapter = new BirdAdapter(sparrow);

		System.out.println("Sparrow");
		sparrow.fly();
		sparrow.makeSound();

		System.out.println("Toyduck");
		toyDuck.squeak();

		// bird behaves like toyduck
		System.out.println("bird adapter");
		birdAdapter.squeak();
	}

	// adapter
	static class BirdAdapter implements ToyDuck {

		Bird bird;

		public BirdAdapter(Bird bird) {
			this.bird = bird;
		}

		@Override
		public void squeak() {
			bird.makeSound();
		}

	}

	interface Bird {
		public void fly();

		public void makeSound();
	}

	static class Sparrow implements Bird {
		@Override
		public void fly() {
			System.out.println("Flying");
		}

		@Override
		public void makeSound() {
			System.out.println("Chirp Chirp");
		}
	}

	interface ToyDuck { // it'll not fly, it'll make squeak sound
		public void squeak();
	}

	static class PlasticToyDuck implements ToyDuck {
		@Override
		public void squeak() {
			System.out.println("Squeak");
		}
	}

}
