package cs414.a5.eid;

import java.io.Serializable;

public class Kiosk implements Serializable {
	private int kioskID;

	private Store pizzaStore;
	
	
	public Kiosk(int ID){
		setKioskID(ID);
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

    @Override public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        return ((Kiosk) o).getKioskID() == this.getKioskID();
    }


    /**
     *
     * @param orderID
     * @return
     */
	public Order createOrder(int orderID) {
        Order newOrder = new Order(orderID);
        if(newOrder!= null){
            newOrder.setIsKioskOrder(this);
            newOrder.setIsRegisterOrder(null);
        }
        //getPizzaStore().getSetOfPlacedOrder().add(newOrder);
        return newOrder;
	}
}
