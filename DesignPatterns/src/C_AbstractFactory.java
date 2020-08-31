
/*
 * is super factory which creates other factories/factory of other objects
 * also called as super factory
 * 
 * scenario: extending car factory scenario to global, where in usa -> steering posing on left, 
 * india -> steering position on right 
 * 
 * Adv: 
 * 	-> loosely coupled
 *  -> divides responsibility among multiple classes
 * 
 * Dis: Increases overall complexity by creating multiple additional classes
 * */
public class C_AbstractFactory {
	public static void main(String[] args) {

		Car indiaSeden = CarFactory.buildCar(CarType.SEDEN);
		System.out.println(indiaSeden);

	}

	public static class CarFactory {
		public static Car buildCar(CarType type) {
			Location location = Location.INDIA; // assue value pick from config

			switch (location) {
			case INDIA:
				return IndiaFactory.buidCar(type);
			case USA:
				return UsaFactory.buidCar(type);
			}
			return null;
		}
	}

	public static class IndiaFactory {
		public static Car buidCar(CarType type) {
			switch (type) {
			case SEDEN:
				return new SedenCar(Location.INDIA);
			case HATCHBACK:
				return new HatchbackCar(Location.INDIA);
			}
			return null;
		}
	}

	public static class UsaFactory {
		public static Car buidCar(CarType type) {
			switch (type) {
			case SEDEN:
				return new SedenCar(Location.USA);
			case HATCHBACK:
				return new HatchbackCar(Location.USA);
			}
			return null;
		}
	}

	public static enum CarType {
		SEDEN, HATCHBACK
	}

	public static enum Location {
		USA, INDIA
	}

	public static abstract class Car {
		private CarType type = null;
		protected Location location = null;

		Car(CarType type, Location location) {
			this.type = type;
			this.location = location;
		}

		public void setType(CarType type) {
			this.type = type;
		}

		public CarType getType() {
			return this.type;
		}

		@Override
		public String toString() {
			return "Model ::" + type + ", Location ::" + location;
		}

		protected abstract void construct();
	}

	public static class SedenCar extends Car {

		SedenCar(Location location) {
			super(CarType.SEDEN, location);
		}

		@Override
		protected void construct() {
			System.out.println("Constructed SEDEN at " + this.location);
		}

	}

	public static class HatchbackCar extends Car {
		public HatchbackCar(Location location) {
			super(CarType.HATCHBACK, location);
		}

		@Override
		protected void construct() {
			System.out.println("Constructed HATCHBACK at " + this.location);
		}

	}

}
