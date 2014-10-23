package cs414.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderItemTest {

	OrderItem orderItem1, orderItem2;
	Item item1, item2;
	int quan1, quan2;
	double subTotal1, subTotal2, itemPrice1, itemPrice2;

	@Before
	public void SetUp() {
		itemPrice1 = 10;
		itemPrice2 = 5;
		item1 = new Item("item1", itemPrice1);
		item2 = new Item("item2", itemPrice2);
		quan1 = 2;
		quan2 = 6;
		orderItem1 = new OrderItem(item1, quan1);
		orderItem2 = new OrderItem(item2, quan2);
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testComputeSubTotal() {
		assertTrue(orderItem1.computeSubtotal() == 20);
		assertTrue(orderItem2.computeSubtotal() == 30);
	}

	@Test
	public void testIncrQuant() {
		orderItem1.incrementQuantity();
		assertEquals(3, orderItem1.getQuantity());
	}

	@Test
	public void testIncrMultQuant() {
		orderItem2.incrementQuantitybyAmount(5);
		assertEquals(11, orderItem2.getQuantity());
	}

	@Test
	public void testDecrQuan() {
		orderItem1.decrementQuantity();
		assertEquals(1, orderItem1.getQuantity());
	}

	@Test
	public void testDecrMultQuan() {
		orderItem2.decrementQuantitybyAmount(2);
		assertEquals(4, orderItem2.getQuantity());
	}

	@Test
	public void testOverDecrMultQuan() {
		try {
			orderItem1.decrementQuantitybyAmount(3);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetItem() {
		assertEquals(item1, orderItem1.getItem());
		assertEquals(item2, orderItem2.getItem());
	}

	@Test
	public void testGetQuan() {
		assertEquals(2, orderItem1.getQuantity());
		assertEquals(6, orderItem2.getQuantity());
	}

	@Test
	public void testSetQuan() {
		orderItem1.setQuantity(4);
		assertEquals(4, orderItem1.getQuantity());
	}

	@Test
	public void getSubTotal() {
		assertTrue(orderItem1.getSubTotal() == 20);
	}

}
