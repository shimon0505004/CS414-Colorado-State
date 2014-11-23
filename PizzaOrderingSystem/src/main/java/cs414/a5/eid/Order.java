package cs414.a5.eid;

import java.io.Serializable;
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

	private boolean isComplete;
	private Employee completedBy;
	
	private boolean isDelivered;
	private Employee deliveredBy;

	private double totalPrice;
	private double amountReceived;
	private double amountReturned;
	
	private Set<OrderItem> setOfItems;

	private boolean isOrderedByCustomerWithMembership;
	private String customerMembershipID;
	
	private Address deliveryAddress;
	private OrderType typeOfOrder;
	
	private boolean isCardPayment;
	private Card paysWithCard;
	
	private int rewardPointGenerated;
	private boolean isPaid;
	
	private Kiosk isKioskOrder;
	private Register isRegisterOrder;
	
	public Order(int ID) {
		// TODO Auto-generated constructor stub

		setOrderID(ID);
		setDateTime(new Date());
		setOrderDateTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(DateTime));
		setComplete(false);
		totalPrice = 0.0 ;
		amountReceived = 0.0;
		setAmountReturned(amountReceived - totalPrice);
		setOfItems = new HashSet<OrderItem>();
		setRewardPointGenerated(0);
		setCompletedBy(null);
		setDelivered(false);
		setDeliveredBy(null);
		createAsInHouseOrder();
		createAsCashPayment();
		
		setIsPaid(false);
	}

	public void setOrderPlacedByApp(){
		this.setIsPaid(true);
		this.setCardPayment(true);
		this.setComplete(false);
		this.setDelivered(false);
		
	}
	
	private void createAsInHouseOrder(){
		setTypeOfOrder(OrderType.Inhouse);
		setOrderedByCustomerWithMembership(false);
		//setCustomerWithMembership(new Customer("", ""));
		//setDeliveryAddress(new Address());
		setCustomerMembershipID(null);
		setDeliveryAddress(null);
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
		if(!(cardNumberFormatTester(cardNumber) && cardExpirationDateFormatTester(cardExpirationDate))){
			return false;
		}
		
		boolean returnVal = makeOrderPayment(amountReceived);
		if(returnVal){
			setCardPayment(true);
			paysWithCard.updateCardInfo(cardNumber,cardExpirationDate,cv2);		
			setIsPaid(true);
		}
		return returnVal;
	}
	
	private boolean cardNumberFormatTester(String cardNumber){
		if(cardNumber.length()<16){
			return false;
		}
		for(int i=0;i<cardNumber.length();i++){
			if(Character.isLetter(cardNumber.charAt(i))){
				return false;
			}
		}
		return true;
	}
	private boolean cardExpirationDateFormatTester(String cardExpirationDate){
		if(cardExpirationDate.length()!=7)
		{
			/*
			 * only 7 characters allowed.
			 */
			return false;
		}
		if(cardExpirationDate.charAt(2) != '/'){
			/*
			 * Date format = 08/2017
			 */
			return false;	
		}
		if(!(cardExpirationDate.charAt(0) == '0' || cardExpirationDate.charAt(0) == '1'))
		{
			return false;
		}
		if(!Character.isDigit(cardExpirationDate.charAt(1))){
			return false;
		}
		if(!(Character.isDigit(cardExpirationDate.charAt(3)) && Character.isDigit(cardExpirationDate.charAt(4)) && Character.isDigit(cardExpirationDate.charAt(5)) && Character.isDigit(cardExpirationDate.charAt(6)))){
			return false;
		}
		
		String year = cardExpirationDate.substring(3);
		if(!(Integer.parseInt(year)>=1970 && Integer.parseInt(year)<=2099))
		{
			return false;
		}
		
		return true;
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
	
	public boolean updateMembershipHoldingCustomer(Customer member, Store store){
		if(member!=null)
		{
			removeMembershipHoldingCustomer(store);
			if(member.addOrder(this)){
				setCustomerMembershipID(member);
				setOrderedByCustomerWithMembership(true);
			}
		}
		
		return isOrderedByCustomerWithMembership();
	}
	
	public boolean removeMembershipHoldingCustomer(Store store){
			if(getCustomerMembershipID()!=null){
				if(store.getMember(getCustomerMembershipID()).removeOrder(this)){
					//setCustomerWithMembership(new Customer("", ""));
					setCustomerMembershipID(null);
					setOrderedByCustomerWithMembership(false);				
				
				}
			}
			else{
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
		
		int rewardPoint = (int)(total * 1.0);
		
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
			if(completedBy.getRole().equals(Role.Chef)){
				//then order can be completed;
				setCompletedBy(completedBy);
				setComplete(true);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean setDeliveredByEmployee(Employee deliveredBy) {
		if(deliveredBy!=null){
			if(deliveredBy.getRole().equals(Role.DeliveryMan)){
				//if order is completed, only then it is delivered.
				if(isComplete()){
					setDelivered(true);
					setDeliveredBy(deliveredBy);
					return true;
				}
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
	public String getCustomerMembershipID() {
		return customerMembershipID;
	}

	/**
	 * @param customerWithMembership the customerWithMembership to set
	 */
	private void setCustomerMembershipID(Customer customerWithMembership) {
		if(customerWithMembership!=null){
			this.customerMembershipID = customerWithMembership.getMemberShipNumber();			
		}else{
			this.customerMembershipID = null;
		}
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

    public String toString() {
        return String.valueOf(this.getOrderID());
    }

	/**
	 * @return the isKioskOrder
	 */
	public Kiosk getIsKioskOrder() {
		return isKioskOrder;
	}

	/**
	 * @param isKioskOrder the isKioskOrder to set
	 */
	public void setIsKioskOrder(Kiosk isKioskOrder) {
		this.isKioskOrder = isKioskOrder;
	}

	/**
	 * @return the isRegisterOrder
	 */
	public Register getIsRegisterOrder() {
		return isRegisterOrder;
	}

	/**
	 * @param isRegisterOrder the isRegisterOrder to set
	 */
	public void setIsRegisterOrder(Register isRegisterOrder) {
		this.isRegisterOrder = isRegisterOrder;
	}

	/**
	 * @return the isDelivered
	 */
	public boolean isDelivered() {
		return isDelivered;
	}

	/**
	 * @param isDelivered the isDelivered to set
	 */
	private void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	/**
	 * @return the deliveredBy
	 */
	public Employee getDeliveredBy() {
		return deliveredBy;
	}

	/**
	 * @param deliveredBy the deliveredBy to set
	 */
	private void setDeliveredBy(Employee deliveredBy) {
		this.deliveredBy = deliveredBy;
	}
}
