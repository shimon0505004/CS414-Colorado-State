package cs414.pos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Order implements Serializable {

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
	
	private int rewardPointGenerated;
	private boolean isPaid;
	
	public Order(int ID) {
		// TODO Auto-generated constructor stub

		setOrderID(ID);
		setDateFormat(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
		setDateTime(new Date());
		setOrderDateTime(getDateFormat().format(DateTime));
		setComplete(false);
		totalPrice = 0.0 ;
		amountReceived = 0.0;
		setAmountReturned(amountReceived - totalPrice);
		setOfItems = new HashSet<OrderItem>();
		setRewardPointGenerated(0);
		setCompletedBy(null);
		createAsInHouseOrder();
		createAsCashPayment();
		
		setIsPaid(false);
	}

	private void createAsInHouseOrder(){
		setTypeOfOrder(OrderType.Inhouse);
		setOrderedByCustomerWithMembership(false);
		setCustomerWithMembership(new Customer("", ""));
		setDeliveryAddress(new Address());
	}
	
	private void createAsCashPayment(){
		setCardPayment(false);
		Card blankCard = new Card();
		setPaysWithCard(blankCard);
	}
	
	public void updateToInHouseOrder(){
		setTypeOfOrder(OrderType.Inhouse);
		setDeliveryAddress(new Address());
	}
	
	public void updateToTakeAwayOrder(){
		setTypeOfOrder(OrderType.TakeAway);
		setDeliveryAddress(new Address());		
	}

	public void updateToHomeDeliveryOrder(Address deliveryAddress){
		setTypeOfOrder(OrderType.HomeDelivery);
		setDeliveryAddress(deliveryAddress);		
	}
	
	public void updateToHomeDeliveryOrder(String Address){
		setTypeOfOrder(OrderType.HomeDelivery);
		setDeliveryAddress(new Address(Address));		
	}

	public boolean makeCardPayment(double amountReceived,String cardNumber,String cardExpirationDate, String cv2){
		boolean returnVal = makeOrderPayment(amountReceived);
		if(returnVal){
			setCardPayment(true);
			paysWithCard.updateCardInfo(cardNumber,cardExpirationDate,cv2);		
			setIsPaid(true);
		}
		return returnVal;
	}
	
	public boolean makeCardPayment(double amountReceived, Card paymentCard){
		boolean returnVal = makeOrderPayment(amountReceived);
		if(returnVal){
			setCardPayment(true);
			setPaysWithCard(paymentCard);
			setIsPaid(true);
		}
		return returnVal;
		
	}
	
	public boolean makeCashPayment(double amountReceived){
		boolean returnVal = makeOrderPayment(amountReceived);
		if(returnVal){
			setCardPayment(false);
			setPaysWithCard(null);
			setIsPaid(true);
		}
		return returnVal;
		
		
	}
	
	
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

	public OrderItem getOrderItem(Item item){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		boolean found=false;
		while(iterOrderItem.hasNext())
		{
			OrderItem tempOrderItem = iterOrderItem.next();
			if(tempOrderItem.getItem().equals(item)){
				found = true;
				return tempOrderItem;
			}
		}	
		
		return null;
	}
	
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

	private boolean makeOrderPayment(double amountReceived){
		double temp = amountReceived-getTotalPrice();
		if(temp>=0.0)
		{
			setAmountReceived(amountReceived);
			setAmountReturned(temp);
			setIsPaid(true);			
			return true;
		}
		else return false;		
	}
	
	public boolean updateMembershipHoldingCustomer(Customer member){
		if(member!=null)
		{
			removeMembershipHoldingCustomer();
			if(member.addOrder(this)){
				setCustomerWithMembership(member);
				setOrderedByCustomerWithMembership(true);
			}
		}
		
		return isOrderedByCustomerWithMembership();
	}
	
	public boolean removeMembershipHoldingCustomer(){
			if(getCustomerWithMembership().removeOrder(this)){
				setCustomerWithMembership(new Customer("", ""));
				setOrderedByCustomerWithMembership(false);				
			
			}
			return !isOrderedByCustomerWithMembership();
	}
	
	
	private void updateTotal(){
		Iterator<OrderItem> iterOrderItem = setOfItems.iterator();
		double tempTotal=0.0;
		while(iterOrderItem.hasNext())
		{
			tempTotal += iterOrderItem.next().getSubTotal();
		}
		setTotalPrice(tempTotal);
		setRewardPointGenerated(calculateRewardPoints());
	}
	
	private int calculateRewardPoints(){
		//updateTotal();
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

	public boolean setCompletedByEmployee(Employee completedBy) {
		if(completedBy!=null){
			if(completedBy.getPrivilege().equals(Privilege.Chef)){
				//then order can be completed;
				setCompletedBy(completedBy);
				setComplete(true);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param completedBy the completedBy to set
	 */
	private void setCompletedBy(Employee completedBy) {
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
	private void setComplete(boolean isComplete) {
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
	private void setTotalPrice(double totalPrice) {
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
	private void setDateFormat(DateFormat dateFormat) {
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
	private void setCustomerWithMembership(Customer customerWithMembership) {
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
	private void setDeliveryAddress(Address deliveryAddress) {
		if(deliveryAddress!=null){
			this.deliveryAddress = deliveryAddress;			
		}else{
			this.deliveryAddress = new Address();			
		}
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
	private void setTypeOfOrder(OrderType typeOfOrder) {
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
	private void setCardPayment(boolean isCardPayment) {
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
	private void setPaysWithCard(Card paysWithCard) {
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
	private void setOrderedByCustomerWithMembership(
			boolean isOrderedByCustomerWithMembership) {
		this.isOrderedByCustomerWithMembership = isOrderedByCustomerWithMembership;
	}

	/**
	 * @return the rewardPointGenerated
	 */
	public int getRewardPointGenerated() {
		return rewardPointGenerated;
	}

	/**
	 * @param rewardPointGenerated the rewardPointGenerated to set
	 */
	private void setRewardPointGenerated(int rewardPointGenerated) {
		this.rewardPointGenerated = rewardPointGenerated;
	}
	
	public Set<OrderItem> getSetOfItems() {
		return setOfItems;
	}

	/**
	 * @return the isPaid
	 */
	public boolean isPaid() {
		return isPaid;
	}

	/**
	 * @param isPaid the isPaid to set
	 */
	private void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
}
