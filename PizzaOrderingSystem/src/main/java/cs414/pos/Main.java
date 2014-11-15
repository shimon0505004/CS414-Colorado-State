package cs414.pos;

import cs414.pos.ui.UIController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan Lighthart
 */
public class Main {
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws Exception {
		Store s = initStore();
		// default values
		boolean isKiosk = false;
		int id = 1;
		if(args.length >= 1) {
			if("false".equalsIgnoreCase(args[0]) || "true".equalsIgnoreCase(args[0])) {
				isKiosk = Boolean.valueOf(args[0]);
			} else {
				System.err.println("Error: first argument should be true or false.");
				return;
			}
			if(args.length == 2) {
				try {
					id = Integer.parseInt(args[1]);
				} catch(NumberFormatException e) {
					System.err.println("Error: parsing second argument. Please enter a positive integer.");
					return;
				}
				if(id <= 0) {
					System.err.println("Error: Second argument needs to be a positive integer.");
					return;
				}
			} else if(args.length > 2) {
				System.err.println("Error: Illegal number of arguments given");
				return;
			}
		}
		boolean found = false;
		if(isKiosk) {
			for(Kiosk k : s.getSetOfKiosk()) {
				if(k.getKioskID() == id) {
					found = true;
					break;
				}
			}
		} else {
			for(Register r : s.getSetOfRegister()) {
				if(r.getRegisterID() == id) {
					found = true;
					break;
				}
			}
		}
		if(!found) {
			System.err.println("Error: invalid id");
			return;
		}
        SaverLoader.saveTestGson(s);
        SaverLoader.openTestGson();
		UIController controller = new UIController(s, isKiosk, id);
		controller.start();
	}

	private static Store createDefaultStore() {
		Store s = new Store();

		Employee manager = s.addEmployee("bob", "bob", "pw_bob", Role.Manager);
		Employee chef = s.addEmployee("billy", "billy", "pw_billy", Role.Chef);
		Employee cashier = s.addEmployee("billy-bob", "billy_bob", "pw_billy_bob", Role.Cashier);
		Employee deliveryGuy = s.addEmployee("jane", "jane", "pw_jane", Role.DeliveryMan);

		
		Kiosk k = s.addKiosk(manager, 1);
		Register r = s.addRegister(manager, 1);

		Menu m0 = s.defineMenu(manager, "menu0", "menu0_desc");
		s.addMenuItem(manager, m0, "cheese pizza", 5.0, "cheesy");
		s.addMenuItem(manager, m0, "sausage pizza", 5.0, "full of sausage");

		Menu m1 = s.defineMenu(manager, "menu1", "menu1_desc");
		s.addMenuItem(manager, m1, "sprite", 5.0, "delicious soda");
		s.addMenuItem(manager, m1, "new belgium", 5.0, "beer");

		/*method create order deleted as it only accounts for creating orders via employee. See changelist description*/
		Order o1 = s.createOrderViaRegister(cashier, r.getRegisterID());
		o1.addItemToOrderByAmount(m0.getMenuItems().iterator().next(), 1);
		s.placeOrder(o1);
		Order o2 = s.createOrderViaKiosk(k.getKioskID());
		o1.addItemToOrderByAmount(m1.getMenuItems().iterator().next(), 1);
		s.placeOrder(o2);

		Customer c1 = s.addNewMember("john", "doe", "1234567890");
		Customer c2 = s.addNewMember("jane", "doe", "1234567891");

		return s;
	}

	public static Store initStore() throws IOException, ClassNotFoundException {
		Store s = null;
		if(SaverLoader.SAVE_FILE.exists()) {
			try {
				s = SaverLoader.load(SaverLoader.SAVE_FILE);
			} catch(Exception ex) {
				System.err.println("Error loading store. Loading default store.");
			}
		}
		if(s == null) {
			s = createDefaultStore();
			// save Store's state
			SaverLoader.save(SaverLoader.SAVE_FILE, s);
		}
		return s;
	}
	
	public static void saveToFile(Store store){
		try {
			SaverLoader.save(SaverLoader.SAVE_FILE, store);
			/**
			 * Saving files to JSON also
			 */
	        SaverLoader.saveTestGson(store);

		} catch(IOException ex) {
			Logger.getLogger(UIController.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}
