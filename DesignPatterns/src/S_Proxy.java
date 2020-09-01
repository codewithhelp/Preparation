import java.util.ArrayList;
import java.util.List;

/*
 * class represents substitute functionality of another class
 * 
 * Decorator vs Proxy:
 * 	Decorator focus on adding functionalities
 * 	Proxy focus on controlling access to an obj
 * 
 * Adv:
 *  -> used at lazy loading, we do not create full object until it actually need
 *  -> add extra security around the original object
 * */

public class S_Proxy {

	public static void main(String[] args) {

		Internet internet = new ProxyInternet();
		internet.connectTo("abc.com");
		internet.connectTo("google.com");
	}

	interface Internet {
		void connectTo(String host);
	}

	static class RealInternet implements Internet {

		@Override
		public void connectTo(String host) {
			System.out.println("Connecting to " + host);
		}

	}

	static class ProxyInternet implements Internet {

		private Internet internet = new RealInternet();
		private static List<String> bannedList = new ArrayList<>();

		static {
			bannedList.add("abc.com");
			bannedList.add("rand.com");
		}

		@Override
		public void connectTo(String host) {
			if (!bannedList.contains(host)) {
				internet.connectTo(host);
			} else
				System.out.println("Banned!! " + host);
		}

	}

}
