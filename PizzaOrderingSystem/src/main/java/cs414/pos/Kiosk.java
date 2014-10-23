package cs414.pos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Kiosk implements Serializable {
	private int kioskID;
	private Set<Order> allOrdersAtKiosk;
	private Store pizzaStore;
	
	
	Kiosk(int ID){
		setKioskID(ID);
		setAllOrdersAtKiosk(new HashSet<Order>());
		setPizzaStore(null);
	}

	Kiosk(int ID,Store pizzaStore){
		setKioskID(ID);
		setAllOrdersAtKiosk(new HashSet<Order>());
		setPizzaStore(pizzaStore);
	}
	
	/**
	 * @return the kioskID
	 */
	public int getKioskID() {
		return kioskID;
	}

	/**
	 * @param kioskID the kioskID to set
	 */
	public void setKioskID(int kioskID) {
		this.kioskID = kioskID;
	}

	/**
	 * @return the allOrdersAtKiosk
	 */
	public Set<Order> getAllOrdersAtKiosk() {
		return allOrdersAtKiosk;
	}

	/**
	 * @param allOrdersAtKiosk the allOrdersAtKiosk to set
	 */
	public void setAllOrdersAtKiosk(Set<Order> allOrdersAtKiosk) {
		this.allOrdersAtKiosk = allOrdersAtKiosk;
	}

	/**
	 * @return the pizzaStore
	 */
	public Store getPizzaStore() {
		return pizzaStore;
	}

	/**
	 * @param pizzaStore the pizzaStore to set
	 */
	public void setPizzaStore(Store pizzaStore) {
		this.pizzaStore = pizzaStore;
	}
	

	

}
