package test.cs414.pos;

import static org.junit.Assert.*;

import cs414.pos.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KioskTest {
	Store testStore3;
	Kiosk testKiosk1, testKiosk2;

	@Before
	public void setUp() throws Exception {
		testStore3 = new Store("PizzaStore3", "Stuart St.");
		Employee e3 = testStore3.addEmployee("c", "c", "pw", Role.Manager);
		testKiosk1 = testStore3.addKiosk(e3, 1);
		testKiosk2 = testStore3.addKiosk(e3, 2);
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateOrder() {
		Order newOrder1 = testStore3.createOrderViaKiosk(testKiosk1.getKioskID());
		assertEquals(newOrder1.getOrderID(), testStore3.getListOfPlacedOrder().size()+1);
		Order newOrder2 = testStore3.createOrderViaKiosk(testKiosk2.getKioskID());
		assertEquals(newOrder2.getOrderID(), testStore3.getListOfPlacedOrder().size()+1);
		assertEquals(newOrder2.getOrderID(), newOrder1.getOrderID());
	}

}
