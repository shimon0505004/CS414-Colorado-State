package cs414.pos;

import java.util.HashSet;
import java.util.Set;

public class Register {

	private int registerID;
	private Set<Order> allOrdersAtRegister;
	private Store pizzaStore;
	
	Register(int ID){
		setRegisterID(ID);
		allOrdersAtRegister = new HashSet<Order>();
		pizzaStore = null;
	}
	
	Register(int ID, Store pizzaStore){
		setRegisterID(ID);
		allOrdersAtRegister = new HashSet<Order>();
		setPizzaStore(pizzaStore);
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
