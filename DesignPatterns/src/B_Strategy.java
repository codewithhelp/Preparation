/*
 * a class behavior or its algorithm can be changed at run time.
 * 
 * Adv:
 *  -> This pattern defines a set of related algorithm and encapsulate them in separated classes, 
 * 		and allows client to choose any algorithm at run time.'
 *  -> Strategy pattern is based upon Open Closed design principle of SOLID principals.
 *  
 * */
public class B_Strategy {
	public static void main(String[] args) {
		// Creating social Media Connect Object for connecting with friend by
		// any social media strategy.
		SocialMediaContext context = new SocialMediaContext();

		// Setting Facebook strategy.
		context.setSocialmediaStrategy(new FacebookStrategy());
		context.connect("Bala");

		System.out.println("====================");

		// Setting Twitter strategy.
		context.setSocialmediaStrategy(new GooglePlusStrategy());
		context.connect("Bala");

		System.out.println("====================");
	}

	interface ISocialMediaStrategy {
		public void connectTo(String friendName);
	}

	static class SocialMediaContext {
		ISocialMediaStrategy smStrategy;

		public void setSocialmediaStrategy(ISocialMediaStrategy smStrategy) {
			this.smStrategy = smStrategy;
		}

		public void connect(String name) {
			smStrategy.connectTo(name);
		}
	}

	static class FacebookStrategy implements ISocialMediaStrategy {

		public void connectTo(String friendName) {
			System.out.println("Connecting with " + friendName + " through Facebook");
		}
	}

	static class GooglePlusStrategy implements ISocialMediaStrategy {

		public void connectTo(String friendName) {
			System.out.println("Connecting with " + friendName + " through GooglePlus");
		}
	}
}
