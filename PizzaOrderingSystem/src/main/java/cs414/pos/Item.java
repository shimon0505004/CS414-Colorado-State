package cs414.pos;

public class Item {

	private String itemName;
	private double itemPrice;
	private int itemID;
	private String itemDescription;
	private boolean isSpecial;
	private double specialPrice;
	
	
	public Item(String name, double price, int ID) {
		// TODO Auto-generated constructor stub
		this.setItemName(name);
		this.setItemPrice(price);
		this.setSpecial(false);
		this.setItemDescription("");
		this.setItemID(ID);
	}
	
	public Item(String name, double price , String itemDescription, int ID) {
		// TODO Auto-generated constructor stub
		this.setItemName(name);
		this.setItemPrice(price);
		this.setSpecial(false);
		this.setItemDescription(itemDescription);
		this.setItemID(ID);
	}
	
	
	public double getPrice(){
		if(isSpecial) return getSpecialPrice();
		else return getItemPrice();
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the itemPrice
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * @return the itemID
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * @return the isSpecial
	 */
	public boolean isSpecial() {
		return isSpecial;
	}

	/**
	 * @param isSpecial the isSpecial to set
	 */
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
		if(!isSpecial)
		{
			setSpecialPrice(0.0);
		}
	}

	/**
	 * @return the specialPrice
	 */
	public double getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * @param specialPrice the specialPrice to set
	 */
	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}
	
}
