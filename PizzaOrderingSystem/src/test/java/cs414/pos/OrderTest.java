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
	
	@Before
	public void setUp() throws Exception {
		testOrder1ID = 1;
		testOrder2ID = 2;
		testOrder1ID = 3;
		testOrder2ID = 4;
		
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
		
		testOrder3.setTypeOfOrder(OrderType.HomeDelivery);
		testOrder4.setTypeOfOrder(OrderType.TakeAway);
		
//		testOrder3.setDeliveryAddress(deliveryAddress);
		
		address1_str = "123 Street A";
		address2_str = "456 Street B";
		address3_str = "789 Street C";
		address4_str = "ABC Street D";

		address1 = new Address(address1_str);
		address2 = new Address(address2_str,AddressType.Business);
		address3 = new Address(address1_str,AddressType.Business);
		address4 = new Address(address1_str,AddressType.Home);
		
		testOrder3.setDeliveryAddress(address3);
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
		fail("Not yet implemented");
	}

	@Test
	public void testMakeCardPaymentDoubleCard() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOneCountOfItemFromOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMultipleCountOfItemFromOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeOrderPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateMembershipHoldingCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompletedBy() {
		assertEquals(null, testOrder1.getCompletedBy());
		assertEquals(null, testOrder2.getCompletedBy());

	}

	@Test
	public void testSetCompletedBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmountReturned() {
		double testReturn1=0.0;
		double testReturn2=0.0;
		assertEquals(testReturn1, testOrder1.getAmountReturned(),0);
		assertEquals(testReturn2, testOrder2.getAmountReturned(),0);
	}

	@Test
	public void testSetAmountReturned() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsComplete() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetComplete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAmountReceived() {
		double testReturn1=0.0;
		double testReturn2=0.0;
		assertEquals(testReturn1, testOrder1.getAmountReceived(),0);
		assertEquals(testReturn2, testOrder2.getAmountReceived(),0);

	}

	@Test
	public void testSetAmountReceived() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTotalPrice() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTotalPrice() {
		fail("Not yet implemented");
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
	public void testSetOrderDateTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrderID() {
		assertEquals(testOrder1ID, testOrder1.getOrderID());
		assertEquals(testOrder2ID, testOrder2.getOrderID());
	}

	@Test
	public void testSetOrderID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDateTime() {
		assertNotSame(0, testOrder1.getDateTime());
		assertNotSame(0, testOrder2.getDateTime());

	}

	@Test
	public void testSetDateTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustomerWithMembership() {
		assertEquals(null, testOrder1.getCustomerWithMembership());
		assertEquals(null, testOrder2.getCustomerWithMembership());

	}

	@Test
	public void testSetCustomerWithMembership() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDeliveryAddress() {
		assertEquals("", testOrder1.getDeliveryAddress().getLocation());
		assertEquals(AddressType.Unknown, testOrder1.getDeliveryAddress().getAddressType());
		assertEquals("", testOrder2.getDeliveryAddress().getLocation());
		assertEquals(AddressType.Unknown, testOrder2.getDeliveryAddress().getAddressType());

	}

	@Test
	public void testSetDeliveryAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTypeOfOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTypeOfOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsCardPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCardPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPaysWithCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPaysWithCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOrderedByCustomerWithMembership() {
		assertEquals(false, testOrder1.isOrderedByCustomerWithMembership());
		assertEquals(false, testOrder2.isOrderedByCustomerWithMembership());
		assertEquals(false, testOrder3.isOrderedByCustomerWithMembership());
		assertEquals(false, testOrder4.isOrderedByCustomerWithMembership());

	}

	@Test
	public void testSetOrderedByCustomerWithMembership() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRewardPointGenerated() {
		assertEquals(0, testOrder1.getRewardPointGenerated());
		assertEquals(0, testOrder2.getRewardPointGenerated());
		assertEquals(0, testOrder3.getRewardPointGenerated());
		assertEquals(0, testOrder4.getRewardPointGenerated());

	}



}
