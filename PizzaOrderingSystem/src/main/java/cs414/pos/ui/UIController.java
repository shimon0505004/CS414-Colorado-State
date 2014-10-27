package cs414.pos.ui;

import cs414.pos.Employee;
import cs414.pos.Store;
import java.awt.EventQueue;

/**
 *
 * @author Nathan Lighthart
 */
public class UIController {
	private LoginUI loginView;
	private MainUI mainView;
	private EditMenuUI editMenuView;
	private PlaceOrderUI placeOrderView;
	private CompleteOrderUI completeOrderView;

	private Store store;
	private Employee currentEmployee;

	public UIController() {
	}

	public UIController(Store store) {
		this.store = store;
	}

	public void start() {
		loginView = new LoginUI(this);
		mainView = new MainUI(this);
		editMenuView = new EditMenuUI(this);
		placeOrderView = new PlaceOrderUI(this);
		completeOrderView = new CompleteOrderUI(this);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				loginView.init();
				mainView.init();
				editMenuView.init();
				placeOrderView.init();
				completeOrderView.init();

				loginView.setVisible(true);
			}
		});
	}

	public void login(String loginID, String password) {
		Employee temp = store.loginAttempt(loginID, password);
		if(temp == null) {
			// Login Failed
			loginView.setStatus("Invalid Username/Password");
		} else {
			currentEmployee = temp;

			loginView.clear();
			loginView.setVisible(false);

			mainView.setCanEditMenu(currentEmployee.getRole().canEditMenu());
			mainView.setCanPlaceOrder(currentEmployee.getRole().canUseKiosk());
			mainView.setCanCompleteOrder(currentEmployee.getRole().canCompleteOrder());
			mainView.setVisible(true);
		}
	}

	public void displayEditMenu() {
		mainView.setVisible(false);
		editMenuView.setVisible(true);
	}

	public void displayPlaceOrder() {
		mainView.setVisible(false);
		placeOrderView.setVisible(true);
	}

	public void displayCompleteOrder() {
		mainView.setVisible(false);
		completeOrderView.setVisible(true);
	}
}
