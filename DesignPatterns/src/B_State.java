/*
 * class behavior changes based on its state
 * 
 * visualize a TV box operated with remote controller. 
 * We can change the state of TV by pressing buttons on remote. 
 * But the state of TV will change or not, it depends on the current state of the TV. 
 * If TV is ON, we can switch it OFF, mute or change aspects and source. 
 * But if TV is OFF, nothing will happen when we press remote buttons.
 * 
 * 
 * */
public class B_State {
	public static void main(String[] args) {
		DeliveryContext ctx = new DeliveryContext(null, "Test123");

		ctx.update();
		ctx.update();
		ctx.update();
		ctx.update();
		ctx.update();
	}

	interface PackageState {
		public void updateState(DeliveryContext ctx);
	}

	static class Acknowledged implements PackageState {
		// Singleton
		private static Acknowledged instance = new Acknowledged();

		private Acknowledged() {
		}

		public static Acknowledged instance() {
			return instance;
		}

		// Business logic and state transition
		@Override
		public void updateState(DeliveryContext ctx) {
			System.out.println("Package is acknowledged !!");
			ctx.setCurrentState(Shipped.instance());
		}
	}

	static class Shipped implements PackageState {
		// Singleton
		private static Shipped instance = new Shipped();

		private Shipped() {
		}

		public static Shipped instance() {
			return instance;
		}

		// Business logic and state transition
		@Override
		public void updateState(DeliveryContext ctx) {
			System.out.println("Package is shipped !!");
			ctx.setCurrentState(InTransition.instance());
		}
	}

	static class InTransition implements PackageState {
		// Singleton
		private static InTransition instance = new InTransition();

		private InTransition() {
		}

		public static InTransition instance() {
			return instance;
		}

		// Business logic and state transition
		@Override
		public void updateState(DeliveryContext ctx) {
			System.out.println("Package is in transition !!");
			ctx.setCurrentState(OutForDelivery.instance());
		}
	}

	static class OutForDelivery implements PackageState {
		// Singleton
		private static OutForDelivery instance = new OutForDelivery();

		private OutForDelivery() {
		}

		public static OutForDelivery instance() {
			return instance;
		}

		// Business logic and state transition
		@Override
		public void updateState(DeliveryContext ctx) {
			System.out.println("Package is out of delivery !!");
			ctx.setCurrentState(Delivered.instance());
		}
	}

	static class Delivered implements PackageState {
		// Singleton
		private static Delivered instance = new Delivered();

		private Delivered() {
		}

		public static Delivered instance() {
			return instance;
		}

		// Business logic
		@Override
		public void updateState(DeliveryContext ctx) {
			System.out.println("Package is delivered!!");
		}
	}

	static class DeliveryContext {

		private PackageState currentState;
		private String packageId;

		public DeliveryContext(PackageState currentState, String packageId) {
			super();
			this.currentState = currentState;
			this.packageId = packageId;

			if (currentState == null) {
				this.currentState = Acknowledged.instance();
			}
		}

		public PackageState getCurrentState() {
			return currentState;
		}

		public void setCurrentState(PackageState currentState) {
			this.currentState = currentState;
		}

		public String getPackageId() {
			return packageId;
		}

		public void setPackageId(String packageId) {
			this.packageId = packageId;
		}

		public void update() {
			currentState.updateState(this);
		}
	}

}
