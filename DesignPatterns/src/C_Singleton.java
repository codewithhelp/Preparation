import java.io.Serializable;

/*
 * When we want to have one and only instance of a class
 * */
public class C_Singleton {
	public static void main(String[] args) {

		Single.what();
		// Singleton1.whatDoYouDo();
		// Singleton1.getInstance();

	}

	// Bill Pugh :: Very very useful
	public static class Single {
		private Single() {
			System.out.println("Single constructor executed");
		}

		private static class Lazy {
			private static final Single INSTANCE = new Single();
		}

		public static Single getInstance() {
			return Lazy.INSTANCE;
		}

		public static void what() {
			System.out.println("what to do");
		}
	}

	// Bill Pugh + Serializable :: Very very useful
	public static class SingleSerial implements Serializable {

		// Is required in cases where your class structure changes between serialization
		// and deserialization
		public static final long serialVersionUID = 1L;

		private SingleSerial() {
			System.out.println("SingleSerial constructor executed");
		}

		private static class Lazy {
			private static final SingleSerial INSTANCE = new SingleSerial();
		}

		public static SingleSerial getInstance() {
			return Lazy.INSTANCE;
		}

		// This method will be invoked when you will de-serialize the object.
		protected Object readResolve() {
			return getInstance();
		}

		public static void what() {
			System.out.println("what to do");
		}
	}

	// Singleton1 : Eager Initialisation
	// The instance is created irrespective of it is required in runtime or not.
	// If this instance is not a big object and you can live with it being unused,
	// this is the best approach.
	// Instance is creates, even if I look for whatDoYouDo()
	public static class Singleton1 {
		private static volatile Singleton1 INSTANCE = new Singleton1();;

		private Singleton1() {
			System.out.println("Create obj called");
		}

		public static Singleton1 getInstance() {
			return INSTANCE;
		}

		public static void whatDoYouDo() {
			System.out.println("I am singleton eager initialization");
		}
	}

	// Singleton2 : Lazy Initialisation
	public static class Singleton2 {
		private static volatile Singleton2 INSTANCE = null;

		private Singleton2() {
			System.out.println("Create obj called");
		}

		public static Singleton2 getInstance() {
			if (INSTANCE == null) {
				synchronized (Singleton2.class) {
					INSTANCE = new Singleton2();
				}
			}
			return INSTANCE;
		}
	}

	// Singleton3 : Double checking
	public static class Singleton3 {
		private static volatile Singleton3 INSTANCE = null;

		private Singleton3() {
			System.out.println("Create obj called");
		}

		public static Singleton3 getInstance() {
			if (INSTANCE == null) {
				synchronized (Singleton2.class) {
					if (INSTANCE == null)
						INSTANCE = new Singleton3();
				}
			}
			return INSTANCE;
		}
	}
}
