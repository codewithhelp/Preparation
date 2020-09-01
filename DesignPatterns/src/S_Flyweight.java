import java.util.HashMap;
import java.util.Random;

/*
 * reuse already existing similar kind of object by storing them, create new object when no matching
 * object found
 * 
 * reduce number of objects created,decrease memory,increase performance
 *  
 * used when we create more number of similar objects(10^5), and objects are immutable
 * */
public class S_Flyweight {
	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {
			Player p = PlayerFactory.getPlayer(getRandomPlayer());
			p.assignWeapon(getRandomWeapon());
			p.mission();
		}

	}

	public static String getRandomPlayer() {
		Random rand = new Random();
		return new String[] { "CounterTerrorist", "Terrorist" }[rand.nextInt(2)];
	}

	public static String getRandomWeapon() {
		Random rand = new Random();
		return new String[] { "AK-47", "GUN" }[rand.nextInt(2)];
	}

	interface Player {
		void assignWeapon(String weapon);

		void mission();
	}

	static class Terrorist implements Player {
		private final String task; // intrinsic
		private String weapon; // extrinsic

		public Terrorist() {
			task = "PLANT A BOMB";
		}

		@Override
		public void assignWeapon(String weapon) {
			this.weapon = weapon;
		}

		@Override
		public void mission() {
			System.out.println("Terrorist with " + this.weapon + ", task is " + this.task);
		}
	}

	static class CounterTerrorist implements Player {
		private final String task; // intrinsic
		private String weapon; // extrinsic

		public CounterTerrorist() {
			task = "DEFUSE BOMB";
		}

		@Override
		public void assignWeapon(String weapon) {
			this.weapon = weapon;
		}

		@Override
		public void mission() {
			System.out.println("Counter Terrorist with " + this.weapon + ", task is " + this.task);
		}

	}

	static class PlayerFactory {
		private static HashMap<String, Player> store = new HashMap<>();

		public static Player getPlayer(String type) {
			Player p = null;
			if (store.containsKey(type))
				p = store.get(type);
			else {
				switch (type) {
				case "Terrorist": {
					System.out.println(type + " obj created");
					p = new Terrorist();
					break;
				}
				case "CounterTerrorist": {
					System.out.println(type + " obj created");
					p = new CounterTerrorist();
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + type);
				}
			}
			store.put(type, p);
			return p;
		}
	}

}
