package cs414.a5.nlighth1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Register implements Serializable {

	private int registerID;
	private Set<Order> allOrdersAtRegister;

	public Register(int ID){
		setRegisterID(ID);
		allOrdersAtRegister = new HashSet<Order>();
	}
	
	/**
	 * @return the allOrdersAtRegister
	 */
	public Set<Order> getAllOrdersAtRegister() {
		return allOrdersAtRegister;
	}
	/**
	 * @param allOrdersAtRegister the allOrdersAtRegister to set
	 */
	public void setAllOrdersAtRegister(Set<Order> allOrdersAtRegister) {
		this.allOrdersAtRegister = allOrdersAtRegister;
	}
	/**
	 * @return the registerID
	 */
	public int getRegisterID() {
		return registerID;
	}
	/**
	 * @param registerID the registerID to set
	 */
	public void setRegisterID(int registerID) {
		this.registerID = registerID;
	}

    @Override public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        return ((Register) o).getRegisterID() == this.getRegisterID();
    }

    /**
	 * @param e
	 * @param orderID
	 * @return
	 */
	public Order createOrder(Employee e, int orderID) {
        if(e.getRole().canCreateOrder()) {
            Order newOrder = new Order(orderID);
            if(newOrder!=null){
                newOrder.setIsKioskOrder(null);
                newOrder.setIsRegisterOrder(this);
            }
            //getPizzaStore().getSetOfPlacedOrder().add(newOrder);
            return newOrder;
        } else {
            return null;
        }
	}
}
