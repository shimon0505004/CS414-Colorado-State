package cs414.a5.nlighth1;

import static org.junit.Assert.*;
import cs414.a5.nlighth1.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegisterTest {

	Store testStore3;
	Register testRegister1, testRegister2;

	String testName1, testName2, testName3;
	String testLoginID1, testLoginID2, testLoginID3;
	String testPassWord1, testPassWord2, testPassWord3;
	
	@Before
	public void setUp() throws Exception {
		testName1 = "Shimon";
		testLoginID1 = "skshimon";
		testPassWord1 = "uda";

		testName2 = "Caleb";
		testLoginID2 = "ctebbe";
		testPassWord2 = "cte";

		testName3 = "Nathan";
		testLoginID3 = "nlightHart";
		testPassWord3 = "nli";

		testStore3 = new Store("PizzaStore3", "Stuart St.");
		Employee e3 = testStore3.addEmployee("c", "c", "pw", Role.Manager);
		testRegister1 = testStore3.addRegister(e3, 1);
		testRegister2 = testStore3.addRegister(e3, 2);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testCreateOrder() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Order newOrder1 = testStore3.createOrderViaRegister(test_Employee1, testRegister1.getRegisterID());
		assertEquals(testStore3.getListOfPlacedOrder().size(), newOrder1.getOrderID());
		assertEquals(true,testStore3.placeOrder(newOrder1));
		assertEquals(testStore3.getListOfPlacedOrder().size()-1, newOrder1.getOrderID());

		Order newOrder2 = testStore3.createOrderViaRegister(test_Employee2, testRegister2.getRegisterID());
		assertNull(newOrder2);
		assertEquals(false,testStore3.placeOrder(newOrder2));
		Order newOrder3 = testStore3.createOrderViaRegister(test_Employee3, testRegister2.getRegisterID());
		assertEquals(testStore3.getListOfPlacedOrder().size(), newOrder3.getOrderID());
		assertEquals(true,testStore3.placeOrder(newOrder3));
		assertEquals(testStore3.getListOfPlacedOrder().size()-1, newOrder3.getOrderID());
	}

}
