package cs414.pos;

import java.util.HashSet;
import java.util.Set;

public class Kiosk {
	private int kioskID;
	private Set<Order> allOrdersAtKiosk;
	
	Kiosk(int ID){
		setKioskID(ID);
		allOrdersAtKiosk = new HashSet<Order>();
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
	

}
