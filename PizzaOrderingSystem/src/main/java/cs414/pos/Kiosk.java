package cs414.pos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Kiosk implements Serializable {
	private int kioskID;

	private Store pizzaStore;
	
	
	public Kiosk(int ID){
		setKioskID(ID);

		setPizzaStore(null);
	}

	public Kiosk(int ID,Store pizzaStore){
		setKioskID(ID);

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

    @Override public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        return ((Kiosk) o).getKioskID() == this.getKioskID();
    }
    
    
    /**
	 * @param e
	 * @param o
	 * @return
	 */
	public Order createOrder(int orderID) {
		if(getPizzaStore()!=null){
				Order newOrder = new Order(orderID);
				if(newOrder!= null){
					newOrder.setIsKioskOrder(this);
					newOrder.setIsRegisterOrder(null);					
				}
				//getPizzaStore().getSetOfPlacedOrder().add(newOrder);
				return newOrder;
		}
		else{
			return null;
		}
	}

    
    
}
