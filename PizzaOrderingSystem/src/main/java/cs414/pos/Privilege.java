/**
 *
 */
package cs414.pos;

/**
 * Represents the different privileges that an Employee can have.
 *
 * @author Nathan Lighthart
 */
public enum Privilege {
	/**
	 * Manager privileges. Can edit menu and use kiosk.
	 */
	Manager,
	/**
	 * Cashier privileges. Can use kiosk.
	 */
	Cashier,
	/**
	 * Chef privileges. Can complete order.
	 */
	Chef;

	/**
	 * Returns {@code true} if the privilege allows for editing the menu.
	 *
	 * @return {@code true} if the privilege allows for editing the menu,
	 * otherwise returns {@code false}
	 */
	public boolean canEditMenu() {
		switch(this) {
			case Manager:
				return true;
			default:
				return false;
		}
	}

	/**
	 * Returns {@code true} if the privilege allows for using the kiosk.
	 *
	 * @return {@code true} if the privilege allows for using the kiosk,
	 * otherwise returns {@code false}
	 */
	public boolean canUseKiosk() {
		switch(this) {
			case Manager:
			case Cashier:
				return true;
			default:
				return false;
		}
	}

	/**
	 * Returns {@code true} if the privilege allows for completing an order.
	 *
	 * @return {@code true} if the privilege allows for completing an order,
	 * otherwise returns {@code false}
	 */
	public boolean canCompleteOrder() {
		switch(this) {
			case Chef:
				return true;
			default:
				return false;
		}
	}
}
