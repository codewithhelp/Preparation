/*
 * generally facade is nothing but face of a building
 * provide simple interface to client instead of complex sub systems
 * it will hides the complexity, and not reduce complexity
 * 
 *  Adv: we make the system easier to use
 *  Dis: subsystems connected with facade so on change of subsystems need careful at facade
 *  
 *  Example:
 *  you are in hotel, and hotel is having veg rest, non-veg rest, vag/non-veg rest.
 *  
 *  as a client, you interact with hotel keeper to get menu from required rest.
 *  
 *  hear the hotel keeper act as facade
 */
public class S_Facade {
	public static void main(String[] args) {

		HotelKeeperFacade hotelKeeper = new HotelKeeperFacade();
		hotelKeeper.getNonVegMenu();
		hotelKeeper.getVegMenu();

	}

	interface Hotel {
		public void getMenus();
	}

	static class VegRest implements Hotel {
		@Override
		public void getMenus() {
			System.out.println("Veg Rest");
		}
	}

	static class NonVegRest implements Hotel {
		@Override
		public void getMenus() {
			System.out.println("NonVeg Rest");
		}
	}

	static class HotelKeeperFacade {
		public void getVegMenu() {
			VegRest vegRest = new VegRest();
			vegRest.getMenus();
		}

		public void getNonVegMenu() {
			NonVegRest nonVegRest = new NonVegRest();
			nonVegRest.getMenus();
		}
	}

}
