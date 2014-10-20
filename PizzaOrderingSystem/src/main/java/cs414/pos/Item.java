package cs414.pos;

public class Item {

	private String itemName;
	private double itemPrice;
	private String itemDescription;
	private boolean isSpecial;
	private double specialPercentageOffPrice;
	
	
	public Item(String name, double price) {
		// TODO Auto-generated constructor stub
		this.setItemName(name);
		this.setItemPrice(price);
		this.setItemDescription("");
        removeSpecial(); // item not a special by default
	}
	
	public Item(String name, double price , String itemDescription) {
		// TODO Auto-generated constructor stub
		this.setItemName(name);
		this.setItemPrice(price);
		this.setItemDescription(itemDescription);
        removeSpecial();
	}

    /**
     * @return item.price - specialPercentage (0.0 if not a special)
     */
	public double getCurrentPrice(){
        return getItemBasePrice() - (getSpecialPercentageOffPrice()*getItemBasePrice());
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
	public double getItemBasePrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
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
	 * @param percentOff percentage off this special item's price
	 */
	public void setSpecial(double percentOff) {
		this.isSpecial = true;
        setSpecialPercentageOffPrice(percentOff);
	}

    public void removeSpecial() {
        this.isSpecial = false;
        setSpecialPercentageOffPrice(0.0);
    }

	/**
	 * @return the specialPercentageOffPrice
	 */
	public double getSpecialPercentageOffPrice() {
		return specialPercentageOffPrice;
	}

	/**
	 * @param specialPercentageOffPrice the specialPercentageOffPrice to set
	 */
	public void setSpecialPercentageOffPrice(double specialPercentageOffPrice) {
		this.specialPercentageOffPrice = specialPercentageOffPrice;
	}
	
}
