package cs414.pos;

public class OrderItem {

	private Item item;
	private int quantity;
	private double subTotal;
	public OrderItem(Item orderedItem , int quantity) {
		setItem(orderedItem);
		setQuantity(quantity);
		computeSubtotal();
	}
	
	public double computeSubtotal(){
		setSubTotal(quantity*(item.getItemPrice()));
		return getSubTotal();
	}
	
	public int incrementQuantity(){
		setQuantity(getQuantity()+1);
		computeSubtotal();
		return getQuantity();
	}
	
	public int incrementQuantitybyAmount(int amonunt){
		for(int i=1;i<=amonunt;i++){
			incrementQuantity();
		}			
		return getQuantity();
	}
	
	public int decrementQuantity(){
		setQuantity(getQuantity()-1);
		computeSubtotal();

		return getQuantity();
	}
	
	public int decrementQuantitybyAmount(int amonunt){
		for(int i=1;i<=amonunt;i++){
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
	public void setItem(Item item) {
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
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

}
