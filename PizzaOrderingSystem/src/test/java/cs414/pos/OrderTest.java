package cs414.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order testOrder1,testOrder2,testOrder3,testOrder4;
	private int testOrder1ID,testOrder2ID,testOrder3ID,testOrder4ID;
	
	private Item testItem1,testItem2,testItem3,testItem4;
	private double testItem1_price,testItem2_price,testItem3_price,testItem4_price;
	private String address1_str,address2_str,address3_str,address4_str;
	private Address address1,address2,address3,address4;
	
	private Employee testEmployee1,testEmployee2,testEmployee3;
	private String employee1Name,employee2Name,employee3Name; 
	
	private double testPayment1,testPayment2,testPayment3;
	private String testCardNumber1,testCardNumber2,testCardNumber3;
	private String testCV2_1,testCV2_2,testCV2_3;
	private String testEXPDate_1,testEXPDate_2,testEXPDate_3;
	
	private Card testCard1,testCard2,testCard3;
	
	private Customer testCustomer1,testCustomer2,testCustomer3;
	
	private String testFirstName1,testFirstName2,testFirstName3;
	private String testLastName1,testLastName2,testLastName3;
	private String testPhoneNumber1,testPhoneNumber2,testPhoneNumber3;
	
	@Before
	public void setUp() throws Exception {
		testOrder1ID = 1;
		testOrder2ID = 2;
		testOrder3ID = 3;
		testOrder4ID = 4;
		
		testOrder1 = new Order(testOrder1ID);
		testOrder2 = new Order(testOrder2ID);
		testOrder3 = new Order(testOrder3ID);
		testOrder4 = new Order(testOrder4ID);
				
		testItem1_price= 1.23;
		testItem2_price= 2.23;
		testItem3_price= 5.10;
		testItem4_price= 8.12;

		testItem1 = new Item("ALaMonde", testItem1_price,"a");
		testItem2 = new Item("Burgers", testItem2_price,"b");
		testItem3 = new Item("Pizza",testItem3_price,"c");
		testItem4 = new Item("Burito", testItem4_price,"d");

		
		testOrder3.addItemToOrder(testItem3);
		testOrder4.addItemToOrder(testItem3);
		testOrder4.addItemToOrder(testItem4);
		testOrder4.addItemToOrderByAmount(testItem2, 4);
		
		
//		testOrder3.setDeliveryAddress(deliveryAddress);
		
		address1_str = "123 Street A";
		address2_str = "456 Street B";
		address3_str = "789 Street C";
		address4_str = "ABC Street D";

		address1 = new Address(address1_str);
		address2 = new Address(address2_str,AddressType.Business);
		address3 = new Address(address1_str,AddressType.Business);
		address4 = new Address(address1_str,AddressType.Home);
		
		testOrder4.updateToTakeAwayOrder();
		testOrder3.updateToHomeDeliveryOrder(address3);		
		
		
		employee1Name = "testManager";
		employee2Name = "testCashier";
		employee3Name = "testChef";

		testEmployee1 = new Employee(employee1Name, Privilege.Manager);
		testEmployee2 = new Employee(employee1Name, Privilege.Cashier);		
		testEmployee3 = new Employee(employee1Name, Privilege.Chef);
		
		testPayment1=34.5	;
		testPayment2=45.2	;
		testPayment3=67.1	;
		
		testCardNumber1="1111-1111-1111-1111"	;
		testCardNumber2="1111-1111-2222-2222"	;
		testCardNumber3="1111-3333-1111-3333"	;
		
		testCV2_1= "342";
		testCV2_2= "351";
		testCV2_3= "987";
		
		testEXPDate_1="AUG 2017"	;
		testEXPDate_2="AUG 2018"	;
		testEXPDate_3="AUG 2010"	;
		
		testFirstName1 = "Shawon";
		testFirstName2 = "Caleb";
		testFirstName3 = "Nathan";

		testLastName1 = "Arefin";
		testLastName2 = "Tebbe";
		testLastName3 = "LightHart";
		
		testPhoneNumber1 = "123-456-7890";
		testPhoneNumber2 = "456-789-1230";
		testPhoneNumber3 = "789-123-4560";

		
		testCard1 = new Card(testCardNumber1,testEXPDate_1,testCV2_1);
		testCard2 = new Card(testCardNumber2,testEXPDate_2,testCV2_2);
		
		testCustomer1 = new Customer(testFirstName1,testLastName1,testPhoneNumber1);
		testCustomer2 = new Customer(testFirstName2,testLastName2,testPhoneNumber2);
		testCustomer3 = new Customer(testFirstName3,testLastName3,testPhoneNumber3);

		testOrder1.updateMembershipHoldingCustomer(testCustomer1);
	}

	@After
	public void tearDown() throws Exception {
	}



	@Test
	public void testUpdateToInHouseOrder() {
		assertEquals(OrderType.Inhouse, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.Inhouse, testOrder2.getTypeOfOrder());
		assertEquals(OrderType.HomeDelivery, testOrder3.getTypeOfOrder());
		assertEquals(OrderType.TakeAway, testOrder4.getTypeOfOrder());
		
		testOrder3.updateToInHouseOrder();
		testOrder4.updateToInHouseOrder();
		assertEquals(OrderType.Inhouse, testOrder3.getTypeOfOrder());
		assertEquals(OrderType.Inhouse, testOrder4.getTypeOfOrder());

	}

	@Test
	public void testUpdateToTakeAwayOrder() {
		assertEquals(OrderType.Inhouse, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.Inhouse, testOrder2.getTypeOfOrder());
		assertEquals(OrderType.HomeDelivery, testOrder3.getTypeOfOrder());
		assertEquals(OrderType.TakeAway, testOrder4.getTypeOfOrder());
		
		testOrder1.updateToTakeAwayOrder();
		testOrder2.updateToTakeAwayOrder();
		assertEquals(OrderType.TakeAway, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.TakeAway, testOrder2.getTypeOfOrder());
	}

	@Test
	public void testUpdateToHomeDeliveryOrderAddress() {
		assertEquals(OrderType.Inhouse, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.Inhouse, testOrder2.getTypeOfOrder());
		assertEquals(OrderType.HomeDelivery, testOrder3.getTypeOfOrder());
		assertEquals(OrderType.TakeAway, testOrder4.getTypeOfOrder());
		
		assertNotSame(null, testOrder1.getDeliveryAddress());
		assertNotSame(address1,testOrder1.getDeliveryAddress());
		assertEquals(AddressType.Unknown, testOrder1.getDeliveryAddress().getAddressType());
		assertEquals("",testOrder1.getDeliveryAddress().getLocation());

		
		testOrder1.updateToHomeDeliveryOrder(address1);
		testOrder2.updateToHomeDeliveryOrder(address2);
		
		assertEquals(address1, testOrder1.getDeliveryAddress());
		assertEquals(address2, testOrder2.getDeliveryAddress());
		assertEquals(AddressType.Unknown, testOrder1.getDeliveryAddress().getAddressType());
		assertEquals(AddressType.Business, testOrder2.getDeliveryAddress().getAddressType());
		assertEquals(OrderType.HomeDelivery, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.HomeDelivery, testOrder2.getTypeOfOrder());
		
	}

	@Test
	public void testUpdateToHomeDeliveryOrderString() {
		assertEquals(OrderType.Inhouse, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.Inhouse, testOrder2.getTypeOfOrder());
		assertEquals(OrderType.HomeDelivery, testOrder3.getTypeOfOrder());
		assertEquals(OrderType.TakeAway, testOrder4.getTypeOfOrder());
		
		assertNotSame(null, testOrder1.getDeliveryAddress());
		assertNotSame(address1,testOrder1.getDeliveryAddress());
		assertEquals(AddressType.Unknown, testOrder1.getDeliveryAddress().getAddressType());
		assertEquals("",testOrder1.getDeliveryAddress().getLocation());

		testOrder1.updateToHomeDeliveryOrder(address1_str);
		testOrder2.updateToHomeDeliveryOrder(address2_str);

		assertEquals(address1_str, testOrder1.getDeliveryAddress().getLocation());
		assertEquals(address2_str, testOrder2.getDeliveryAddress().getLocation());
		assertEquals(AddressType.Unknown, testOrder1.getDeliveryAddress().getAddressType());
		assertEquals(AddressType.Unknown, testOrder2.getDeliveryAddress().getAddressType());
		assertEquals(OrderType.HomeDelivery, testOrder1.getTypeOfOrder());
		assertEquals(OrderType.HomeDelivery, testOrder2.getTypeOfOrder());
		
	}

	@Test
	public void testMakeCardPaymentDoubleStringStringString() {
		assertEquals(false, testOrder3.makeCardPayment(4.0, testCardNumber3,testEXPDate_3,testCV2_3));
		assertEquals(true, testOrder4.makeCardPayment(47.0, testCardNumber3,testEXPDate_3,testCV2_3));	
	}

	@Test
	public void testMakeCardPaymentDoubleCard() {
		assertEquals(false, testOrder3.makeCardPayment(4.0, testCard1));
		assertEquals(true, testOrder4.makeCardPayment(47.0, testCard2));		
		
	}

	@Test
	public void testAddItemToOrder() {
		assertEquals(0, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());
		
		testOrder1.addItemToOrder(testItem1);
		testOrder1.addItemToOrder(testItem2);
		testOrder1.addItemToOrder(testItem3);
		testOrder1.addItemToOrder(testItem4);

		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());

		assertEquals(1, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());
		
	}

	@Test
	public void testAddItemToOrderByAmount() {
		assertEquals(0, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());
		
		testOrder1.addItemToOrderByAmount(testItem1,4);
		testOrder1.addItemToOrder(testItem2);
		testOrder1.addItemToOrder(testItem3);
		testOrder1.addItemToOrder(testItem4);

		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());
		
		assertEquals(4, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

	}

	@Test
	public void testRemoveItemTotallyFromOrder() {
		testOrder1.addItemToOrderByAmount(testItem1,4);
		testOrder1.addItemToOrder(testItem2);
		testOrder1.addItemToOrder(testItem3);
		testOrder1.addItemToOrder(testItem4);

		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());
		
		assertEquals(4, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

		testOrder1.removeItemTotallyFromOrder(testItem1);
		assertEquals(3, testOrder1.getSetOfItems().size());

		assertEquals(null, testOrder1.getOrderItem(testItem1));
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());
		
		
	}

	@Test
	public void testRemoveOneCountOfItemFromOrder() {
		
		testOrder1.addItemToOrderByAmount(testItem1,4);
		testOrder1.addItemToOrder(testItem2);
		testOrder1.addItemToOrder(testItem3);
		testOrder1.addItemToOrder(testItem4);

		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());
		
		assertEquals(4, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

		testOrder1.removeOneCountOfItemFromOrder(testItem1);
		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(3, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

		testOrder1.removeOneCountOfItemFromOrder(testItem2);
		assertEquals(3, testOrder1.getSetOfItems().size());
		assertEquals(3, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(null, testOrder1.getOrderItem(testItem2));
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

	}

	@Test
	public void testRemoveMultipleCountOfItemFromOrder() {
		testOrder1.addItemToOrderByAmount(testItem1,4);
		testOrder1.addItemToOrder(testItem2);
		testOrder1.addItemToOrder(testItem3);
		testOrder1.addItemToOrder(testItem4);

		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(0, testOrder2.getSetOfItems().size());
		assertEquals(1, testOrder3.getSetOfItems().size());
		assertEquals(3, testOrder4.getSetOfItems().size());
		
		assertEquals(4, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

		testOrder1.removeMultipleCountOfItemFromOrder(testItem1, 2);
		assertEquals(4, testOrder1.getSetOfItems().size());
		assertEquals(2, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem2).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());

		testOrder1.removeMultipleCountOfItemFromOrder(testItem2, 4);
		assertEquals(3, testOrder1.getSetOfItems().size());
		assertEquals(2, testOrder1.getOrderItem(testItem1).getQuantity());
		assertEquals(null, testOrder1.getOrderItem(testItem2));
		assertEquals(1, testOrder1.getOrderItem(testItem3).getQuantity());
		assertEquals(1, testOrder1.getOrderItem(testItem4).getQuantity());
	}

	@Test
	public void testMakeCashPayment() {
		assertEquals(false,testOrder3.makeCashPayment(3.0));
		assertEquals(false,testOrder3.isPaid());
		assertEquals(false, testOrder3.isCardPayment());

		assertEquals(true,testOrder3.makeCashPayment(6.0));
		assertEquals(true,testOrder3.isPaid());
		assertEquals(false, testOrder3.isCardPayment());
	}

	@Test
	public void testUpdateMembershipHoldingCustomer() {
		
		assertEquals(testCustomer1,testOrder1.getCustomerWithMembership());
		assertEquals(testCustomer1.getCustomerOrders().size(), 1);
		assertEquals(testOrder1.getCustomerWithMembership().getCustomerOrders().size(),1);


		assertEquals(testCustomer2.getCustomerOrders().size(), 0);
		testOrder2.updateMembershipHoldingCustomer(testCustomer2);
		assertEquals(testCustomer2.getCustomerOrders().size(), 1);
		assertEquals(testOrder2.getCustomerWithMembership().getCustomerOrders().size(),1);
	
		testOrder1.updateMembershipHoldingCustomer(testCustomer2);

		assertEquals(testCustomer2,testOrder1.getCustomerWithMembership());
		assertEquals(testCustomer1.getCustomerOrders().size(), 0);
		assertEquals(testOrder1.getCustomerWithMembership().getCustomerOrders().size(),2);

		
	}

	@Test
	public void testGetCompletedBy() {
		assertEquals(null, testOrder1.getCompletedBy());
		assertEquals(null, testOrder2.getCompletedBy());
		assertEquals(null, testOrder3.getCompletedBy());
		assertEquals(null, testOrder4.getCompletedBy());		

	}

	@Test
	public void testSetCompletedByEmployee() {
		assertEquals(false, testOrder1.setCompletedByEmployee(testEmployee1));
		assertEquals(false, testOrder2.setCompletedByEmployee(testEmployee2));
		assertEquals(true, 	testOrder3.setCompletedByEmployee(testEmployee3));
		assertEquals(true, 	testOrder4.setCompletedByEmployee(testEmployee3));
		
		assertEquals(null, testOrder1.getCompletedBy());
		assertEquals(null, testOrder2.getCompletedBy());
		assertEquals(testEmployee3, testOrder3.getCompletedBy());
		assertEquals(testEmployee3, testOrder4.getCompletedBy());		

		assertEquals(false, testOrder1.isComplete());
		assertEquals(false, testOrder2.isComplete());
		assertEquals(true, 	testOrder3.isComplete());
		assertEquals(true, 	testOrder4.isComplete());
		
	}

	@Test
	public void testGetAmountReturned() {
		double testReturn1=0.0;
		double testReturn2=0.0;
		assertEquals(testReturn1, testOrder1.getAmountReturned(),0);
		assertEquals(testReturn2, testOrder2.getAmountReturned(),0);
	}



	@Test
	public void testIsComplete() {
		assertEquals(false, testOrder1.isComplete());
		assertEquals(false, testOrder2.isComplete());
		assertEquals(false, testOrder3.isComplete());
		assertEquals(false, testOrder4.isComplete());

		
	}

	@Test
	public void testSetComplete() {
		
	}

	@Test
	public void testGetAmountReceived() {
		double testReturn1=0.0;
		double testReturn2=0.0;
		assertEquals(testReturn1, testOrder1.getAmountReceived(),0);
		assertEquals(testReturn2, testOrder2.getAmountReceived(),0);

	}



	@Test
	public void testGetTotalPrice() {
		assertEquals(0.0, testOrder1.getTotalPrice(),0);
		assertEquals(0.0, testOrder2.getTotalPrice(),0);
		assertEquals(testItem3_price, testOrder3.getTotalPrice(),0);
		assertEquals((testItem2_price*4 + testItem3_price + testItem4_price ), testOrder4.getTotalPrice(),0);

	}





	@Test
	public void testGetOrderDateTime() {
		String testOrderDateTime1 = testOrder1.getOrderDateTime();
		String testOrderDateTime2 = testOrder2.getOrderDateTime();
		assertNotSame(testOrderDateTime1, testOrderDateTime2);
		assertNotSame(null, testOrderDateTime1);
		assertNotSame(null, testOrderDateTime2);

	}



	@Test
	public void testGetOrderID() {
		assertEquals(testOrder1ID, testOrder1.getOrderID());
		assertEquals(testOrder2ID, testOrder2.getOrderID());
		assertEquals(testOrder3ID, testOrder3.getOrderID());
		assertEquals(testOrder4ID, testOrder4.getOrderID());
	}

	@Test
	public void testSetOrderID() {
		int testOrder4ID_edit = 5;
		testOrder4.setOrderID(testOrder4ID_edit);
		assertEquals(testOrder4ID_edit, testOrder4.getOrderID());		
	}

	@Test
	public void testGetDateTime() {
		assertNotSame(0, testOrder1.getDateTime());
		assertNotSame(0, testOrder2.getDateTime());

	}



	@Test
	public void testGetCustomerWithMembership() {
		assertEquals(testCustomer1, testOrder1.getCustomerWithMembership());
		assertNotSame(testCustomer2, testOrder2.getCustomerWithMembership());
		assertNotSame(testCustomer3, testOrder3.getCustomerWithMembership());

	}



	@Test
	public void testGetDeliveryAddress() {
		assertEquals("", testOrder1.getDeliveryAddress().getLocation());
		assertEquals(AddressType.Unknown, testOrder1.getDeliveryAddress().getAddressType());
		assertEquals("", testOrder2.getDeliveryAddress().getLocation());
		assertEquals(AddressType.Unknown, testOrder2.getDeliveryAddress().getAddressType());

	}


	@Test
	public void testGetTypeOfOrder() {
		assertEquals(testOrder1.getTypeOfOrder(), OrderType.Inhouse);
		assertEquals(testOrder2.getTypeOfOrder(), OrderType.Inhouse);
		assertEquals(testOrder3.getTypeOfOrder(), OrderType.HomeDelivery);
		assertEquals(testOrder4.getTypeOfOrder(), OrderType.TakeAway);

	}



	@Test
	public void testIsCardPayment() {
		assertEquals(false,testOrder1.isCardPayment());
		assertEquals(false,testOrder2.isCardPayment());
		assertEquals(false,testOrder3.isCardPayment());
		assertEquals(false,testOrder4.isCardPayment());

	}



	@Test
	public void testGetPaysWithCard() {
		assertEquals(false,testOrder3.makeCashPayment(3.0));
		assertEquals(false,testOrder3.isPaid());
		assertEquals(false, testOrder3.isCardPayment());

		assertEquals(true,testOrder3.makeCashPayment(6.0));
		assertEquals(true,testOrder3.isPaid());
		assertEquals(false, testOrder3.isCardPayment());
		
		assertEquals(false,testOrder4.makeCardPayment(20.0, testCard1));
		assertEquals(false,testOrder4.isPaid());
		assertEquals(false, testOrder4.isCardPayment());
		
		
	}



	@Test
	public void testIsOrderedByCustomerWithMembership() {
		assertEquals(true, testOrder1.isOrderedByCustomerWithMembership());
		assertEquals(false, testOrder2.isOrderedByCustomerWithMembership());
		assertEquals(false, testOrder3.isOrderedByCustomerWithMembership());
		assertEquals(false, testOrder4.isOrderedByCustomerWithMembership());
		assertEquals(testCustomer1, testOrder1.getCustomerWithMembership());
		assertEquals(testCustomer1.getCustomerOrders().size(), 1);
		
		assertEquals(testCustomer2.getCustomerOrders().size(), 0);
		testOrder2.updateMembershipHoldingCustomer(testCustomer2);
		assertEquals(testCustomer2.getCustomerOrders().size(), 1);
		assertEquals(testOrder2.getCustomerWithMembership().getCustomerOrders().size(),1);
		
		
	}


	@Test
	public void testGetRewardPointGenerated() {
		assertEquals(0, testOrder1.getRewardPointGenerated());
		assertEquals(0, testOrder2.getRewardPointGenerated());
		assertEquals(0, testOrder3.getRewardPointGenerated());
		assertEquals(0, testOrder4.getRewardPointGenerated());

	}



}
