package cs414.pos;

import cs414.pos.ui.UIController;
import java.io.IOException;

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
		UIController controller = new UIController(s);
		controller.start();
	}

	private static Store createDefaultStore() {
		Store s = new Store();

		Employee manager = s.addEmployee("bob", "bob", "pw_bob", Role.Manager);
		Employee chef = s.addEmployee("billy", "billy", "pw_billy", Role.Chef);
		Employee cashier = s.addEmployee("billy-bob", "billy_bob", "pw_billy_bob", Role.Cashier);

		Kiosk k = s.addKiosk(manager, 1);
		Register r = s.addRegister(manager, 1);

		Menu m0 = s.defineMenu(manager, "menu0", "menu0_desc");
		s.addMenuItem(manager, m0, "pizza0", 5.0, "cheesy");
		s.addMenuItem(manager, m0, "pizza1", 5.0, "sausage");

		Menu m1 = s.defineMenu(manager, "menu1", "menu1_desc");
		s.addMenuItem(manager, m1, "soda", 5.0, "sprite");
		s.addMenuItem(manager, m1, "beer", 5.0, "new belgium");

		/*method create order deleted as it only accounts for creating orders via employee. See changelist description*/
		Order o1 = s.createOrderViaRegister(cashier, r.getRegisterID());
		Order o2 = s.createOrderViaKiosk(k.getKioskID());

		return s;
	}

	private static Store initStore() throws IOException, ClassNotFoundException {
		Store s;
		if(!SaverLoader.SAVE_FILE.exists()) {
			s = createDefaultStore();
			// save Store's state
			SaverLoader.save(SaverLoader.SAVE_FILE, s);
		} else {
			// load Store's state
			s = SaverLoader.load(SaverLoader.SAVE_FILE);
		}
		return s;
	}
}
