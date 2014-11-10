/**
 *
 */
package cs414.pos;

/**
 * Represents the different privileges that an Employee can have.
 *
 * @author Nathan Lighthart
 */
public enum Role {

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
	Chef,
	/**
	 * Delivery man privileges. Can mark order as delivered only.
	 */
	DeliveryMan;

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
	 * Returns {@code true} if the privilege allows for Adding a Kiosk .
	 *
	 * @return {@code true} if the privilege allows for Adding a Kiosk,
	 * otherwise returns {@code false}
	 *
	 */
	public boolean canAddKiosk() {
		switch(this) {
			case Manager:
				return true;
			default:
				return false;
		}
	}

	/**
	 * Returns {@code true} if the privilege allows for Adding a Register .
	 *
	 * @return {@code true} if the privilege allows for Adding a Register,
	 * otherwise returns {@code false}
	 *
	 */
	public boolean canAddRegister() {
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
	public boolean canUseRegister() {
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

	/**
	 * Returns {@code true} if the privilege allows for adding an order.
	 *
	 * @return {@code true} if the privilege allows for adding an order,
	 * otherwise returns {@code false}
	 */
	public boolean canCreateOrder() {
		switch(this) {
			case Manager:
			case Cashier:
				return true;
			default:
				return false;
		}
	}

	public boolean canManageEmployees() {
		switch(this) {
			case Manager:
				return true;
			default:
				return false;
		}
	}

	public boolean canViewCustomers() {
		switch(this) {
			case Manager:
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
	public boolean canCompleteDelivery() {
		switch(this) {
			case DeliveryMan:
				return true;
			default:
				return false;
		}
	}

}
