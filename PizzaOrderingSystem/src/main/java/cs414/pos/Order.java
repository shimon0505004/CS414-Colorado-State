package cs414.pos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Order {

	private int orderID;
	private Date DateTime;
	private String orderDateTime;
	//private String orderTime;
	private DateFormat dateFormat;
	
	private boolean isComplete;
	private Employee completedBy;

	private double totalPrice;
	private double amountReceived;
	private double amountReturned;
	
	private Set<OrderItem> setOfItems;
	
	private boolean isOrderedByCustomerWithMembership;
	private Customer customerWithMembership;
	
	private Address deliveryAddress;
	private OrderType typeOfOrder;
	
	private boolean isCardPayment;
	private Card paysWithCard;


    /**
     *
     * @param ID
     */
	public Order(int ID) {
		// TODO Auto-generated constructor stub

		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateTime = new Date();
		orderDateTime = dateFormat.format(DateTime);
		setComplete(false);
		totalPrice = 0.0 ;
		amountReceived = 0.0;
		setAmountReturned(amountReceived - totalPrice);
		setOfItems = new HashSet<OrderItem>();
		
		createAsInHouseOrder();
		createAsCashPayment();
	}

    /**
     *
     */
	private void createAsInHouseOrder(){
		setTypeOfOrder(OrderType.Inhouse);
		setOrderedByCustomerWithMembership(false);
		setCustomerWithMembership(new Customer("", "", -1));
		setDeliveryAddress(new Address());
	}

    /**
     *
     */
	private void createAsCashPayment(){
		setCardPayment(false);
		Card blankCard = new Card();
		setPaysWithCard(blankCard);
	}

    /**
     *
     */
	public void updateToInHouseOrder(){
		setTypeOfOrder(OrderType.Inhouse);
		setDeliveryAddress(new Address());
	}

    /**
     *
     */
	public void updateToTakeAwayOrder(){
		setTypeOfOrder(OrderType.TakeAway);
		setDeliveryAddress(new Address());		
	}

    /**
     *
     * @param deliveryAddress
     */
	public void updateToHomeDeliveryOrder(Address deliveryAddress){
		setTypeOfOrder(OrderType.HomeDelivery);
		setDeliveryAddress(deliveryAddress);		
	}

    /**
     *
     * @param Address
     */
	public void updateToHomeDeliveryOrder(String Address){
		setTypeOfOrder(OrderType.HomeDelivery);
		setDeliveryAddress(new Address(Address));		
	}

    /**
     *
     * @param amountReceived
     * @param cardNumber
     * @param cardExpirationDate
     * @param cv2
     * @return
     */
	public boolean makeCardPayment(double amountReceived,String cardNumber,String cardExpirationDate, String cv2){
		boolean returnVal = makeOrderPayment(amountReceived);
		if(returnVal){
			setCardPayment(true);
			paysWithCard.updateCardInfo(cardNumber,cardExpirationDate,cv2);			
		}
		return returnVal;
	}

    /**
     *
     * @param amountReceived
     * @param paymentCard
     * @return
     */
	public boolean makeCardPayment(double amountReceived, Card paymentCard){
		boolean returnVal = makeOrderPayment(amountReceived);
		if(returnVal){
			setCardPayment(true);
			setPaysWithCard(paymentCard);
		}
		return returnVal;
		
	}

    /**
     *
     * @param newItem
     */
	public void addItemToOrder(Item newItem){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		boolean found=false;
		while(iterOrderItem.hasNext())
		{
			OrderItem tempOrderItem = iterOrderItem.next();
			if(tempOrderItem.getItem().equals(newItem))
			{
				setOfItems.remove(tempOrderItem);
				tempOrderItem.incrementQuantity();
				setOfItems.add(tempOrderItem);
				found = true;
				break;
			}
		}
		if(!found){
			OrderItem newOrderItem = new OrderItem(newItem, 1);
			setOfItems.add(newOrderItem);
		}
		updateTotal();
	}

    /**
     *
     * @param newItem
     * @param Count
     */
	public void addItemToOrderByAmount(Item newItem,int Count){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		boolean found=false;
		while(iterOrderItem.hasNext())
		{
			OrderItem tempOrderItem = iterOrderItem.next();
			if(tempOrderItem.getItem().equals(newItem))
			{
				setOfItems.remove(tempOrderItem);
				tempOrderItem.incrementQuantitybyAmount(Count);
				setOfItems.add(tempOrderItem);
				found = true;
				break;
			}
		}
		if(!found){
			OrderItem newOrderItem = new OrderItem(newItem, Count);
			setOfItems.add(newOrderItem);
		}
		updateTotal();
	}

    /**
     *
     * @param newItem
     * @return
     */
	public boolean removeItemTotallyFromOrder(Item newItem){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		boolean found=false;
		while(iterOrderItem.hasNext())
		{
			OrderItem tempOrderItem = iterOrderItem.next();
			if(tempOrderItem.getItem().equals(newItem))
			{
				setOfItems.remove(tempOrderItem);
				found = true;	
				break;
			}
		}
		updateTotal();
		return found;
	}

    /**
     *
     * @param newItem
     * @return
     */
	public boolean removeOneCountOfItemFromOrder(Item newItem){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		boolean found=false;
		while(iterOrderItem.hasNext())
		{
			OrderItem tempOrderItem = iterOrderItem.next();
			if(tempOrderItem.getItem().equals(newItem))
			{
				setOfItems.remove(tempOrderItem);
				if(tempOrderItem.decrementQuantity()>0)
				{
					setOfItems.add(tempOrderItem);					
				}
				found = true;
				break;
			}
		}
		updateTotal();	
		return found;
	}

    /**
     *
     * @param newItem
     * @param Count
     * @return
     */
	public boolean removeMultipleCountOfItemFromOrder(Item newItem, int Count){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		boolean found=false;
		while(iterOrderItem.hasNext())
		{
			OrderItem tempOrderItem = iterOrderItem.next();
			if(tempOrderItem.getItem().equals(newItem))
			{
				setOfItems.remove(tempOrderItem);
				if(tempOrderItem.decrementQuantitybyAmount(Count)>0)
				{
					setOfItems.add(tempOrderItem);					
				}
				found = true;
				break;
			}
		}
		updateTotal();	
		return found;
	}

    /**
     *
     * @param amountReceived
     * @return
     */
	public boolean makeOrderPayment(double amountReceived){
		double temp = amountReceived-getTotalPrice();
		if(temp>=0.0)
		{
			setAmountReceived(amountReceived);
			setAmountReturned(temp);
			return true;
		}
		else return false;		
	}
	
	public boolean updateMembershipHoldingCustomer(Customer member){
		if(member!=null)
		{
			setCustomerWithMembership(member);
			setOrderedByCustomerWithMembership(true);
			
		}
		return isOrderedByCustomerWithMembership();
	}

    /**
     *
     */
	private void updateTotal(){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		double tempTotal=0.0;
		while(iterOrderItem.hasNext())
		{
			tempTotal += iterOrderItem.next().getSubTotal();
		}
		setTotalPrice(tempTotal);
	}

    /**
     *
     * @return
     */
	private int calculateRewardPoints(){
		updateTotal();
		double total = getTotalPrice();
		
		int rewardPoint = (int)(total * 0.0);
		
		return rewardPoint;
	}
	
	
	
	
	/**
	 * @return the completedBy
	 */
	public Employee getCompletedBy() {
		return completedBy;
	}

	/**
	 * @param completedBy the completedBy to set
	 */
	public void setCompletedBy(Employee completedBy) {
		this.completedBy = completedBy;
	}

	/**
	 * @return the amountReturned
	 */
	public double getAmountReturned() {
		return amountReturned;
	}

	/**
	 * @param amountReturned the amountReturned to set
	 */
	public void setAmountReturned(double amountReturned) {
		this.amountReturned = amountReturned;
	}

	/**
	 * @return the isComplete
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 * @return the amountReceived
	 */
	public double getAmountReceived() {
		return amountReceived;
	}

	/**
	 * @param amountReceived the amountReceived to set
	 */
	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
		
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the dateFormat
	 */
	public DateFormat getDateFormat() {
		return dateFormat;
	}

	/**
	 * @param dateFormat the dateFormat to set
	 */
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @return the orderDateTime
	 */
	public String getOrderDateTime() {
		return orderDateTime;
	}

	/**
	 * @param orderDateTime the orderDateTime to set
	 */
	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	/**
	 * @return the orderID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return DateTime;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}

	/**
	 * @return the customerWithMembership
	 */
	public Customer getCustomerWithMembership() {
		return customerWithMembership;
	}

	/**
	 * @param customerWithMembership the customerWithMembership to set
	 */
	public void setCustomerWithMembership(Customer customerWithMembership) {
		this.customerWithMembership = customerWithMembership;
	}

	/**
	 * @return the deliveryAddress
	 */
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * @param deliveryAddress the deliveryAddress to set
	 */
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * @return the typeOfOrder
	 */
	public OrderType getTypeOfOrder() {
		return typeOfOrder;
	}

	/**
	 * @param typeOfOrder the typeOfOrder to set
	 */
	public void setTypeOfOrder(OrderType typeOfOrder) {
		this.typeOfOrder = typeOfOrder;
	}

	/**
	 * @return the isCardPayment
	 */
	public boolean isCardPayment() {
		return isCardPayment;
	}

	/**
	 * @param isCardPayment the isCardPayment to set
	 */
	public void setCardPayment(boolean isCardPayment) {
		this.isCardPayment = isCardPayment;
	}

	/**
	 * @return the paysWithCard
	 */
	public Card getPaysWithCard() {
		return paysWithCard;
	}

	/**
	 * @param paysWithCard the paysWithCard to set
	 */
	public void setPaysWithCard(Card paysWithCard) {
		this.paysWithCard = paysWithCard;
	}

	/**
	 * @return the isOrderedByCustomerWithMembership
	 */
	public boolean isOrderedByCustomerWithMembership() {
		return isOrderedByCustomerWithMembership;
	}

	/**
	 * @param isOrderedByCustomerWithMembership the isOrderedByCustomerWithMembership to set
	 */
	public void setOrderedByCustomerWithMembership(
			boolean isOrderedByCustomerWithMembership) {
		this.isOrderedByCustomerWithMembership = isOrderedByCustomerWithMembership;
	}
}
