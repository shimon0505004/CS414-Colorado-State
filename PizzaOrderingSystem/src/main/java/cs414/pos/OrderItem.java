package cs414.pos;

public class OrderItem {

	private Item item;
	private int quantity;
	private double subTotal;

    /**
     *
     * @param orderedItem
     * @param quantity
     */
	public OrderItem(Item orderedItem , int quantity) {
		setItem(orderedItem);
		setQuantity(quantity);
		computeSubtotal();
	}

    /**
     *
     * @return
     */
	public double computeSubtotal(){
		setSubTotal(quantity*(item.getCurrentPrice()));
		return getSubTotal();
	}

    /**
     *
     * @return
     */
	public int incrementQuantity(){
		setQuantity(getQuantity()+1);
		computeSubtotal();
		return getQuantity();
	}

    /**
     *
     * @param amount
     * @return
     */
	public int incrementQuantitybyAmount(int amount){
		for(int i=1;i<=amount;i++){
			incrementQuantity();
		}			
		return getQuantity();
	}

    /**
     *
     * @return
     */
	public int decrementQuantity(){
		setQuantity(getQuantity()-1);
		computeSubtotal();

		return getQuantity();
	}

    /**
     *
     * @param amount
     * @return
     */
	public int decrementQuantitybyAmount(int amount){
		for(int i=1;i<=amount;i++){
			decrementQuantity();
		}

		return getQuantity();

	}

	
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	private void setItem(Item item) {
		this.item = item;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		if(quantity>=0){
			this.quantity = quantity;			
		}
		else{
			//cant be less than zero
		} 
	}
	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	private void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

}
