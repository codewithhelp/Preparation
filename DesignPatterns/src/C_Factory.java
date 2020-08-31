/*
 * create object without exposing the creation logic to client and refer to newly created object 
 * using common interface
 * 
 * Adv: 
 * 	-> Loose coupling
 * 	-> Hides implementation of an application 
 * Dis: The pattern can result in too many subclasses with very minor differences.
 * */
public class C_Factory {
	public static void main(String[] args) {
		Car seden = CarFactory.buidCar(CarType.SEDEN);
		Car hatchback = CarFactory.buidCar(CarType.HATCHBACK);
		System.out.println(seden);
		System.out.println(hatchback);
	}

	public static class CarFactory {
		public static Car buidCar(CarType type) {
			switch (type) {
			case SEDEN:
				return new SedenCar();
			case HATCHBACK:
				return new HatchbackCar();
			}
			return null;
		}
	}

	public static enum CarType {
		SEDEN, HATCHBACK
	}

	public static abstract class Car {
		private CarType type = null;

		Car(CarType type) {
			this.type = type;
		}

		public void setType(CarType type) {
			this.type = type;
		}

		public CarType getType() {
			return this.type;
		}

		protected abstract void construct();
	}

	public static class SedenCar extends Car {

		SedenCar() {
			super(CarType.SEDEN);
		}

		@Override
		protected void construct() {
			System.out.println("Constructed SEDEN");
		}

	}

	public static class HatchbackCar extends Car {
		public HatchbackCar() {
			super(CarType.HATCHBACK);
		}

		@Override
		protected void construct() {
			System.out.println("Constructed HATCHBACK");
		}

	}

}
