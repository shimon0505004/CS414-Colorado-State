package cs414.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class StoreTest {

	Store testStore1 , testStore2, testStore3, testStore4;

	@Before
	public void setUp() throws Exception {
		testStore1 = new Store();
		testStore2 = new Store("PizzaStore2");		
		testStore3 = new Store("PizzaStore3","Stuart St.");		
		testStore4 = new Store("PizzaStore4","206-953-5584","Stuart St.");		

		testStore4.addEmployee("Shimon", "skshimon", "uda");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStore() {
		assertEquals("", testStore1.getStoreName().toString());
		assertEquals("000-000-0000", testStore1.getPhoneNumber().toString());
		assertEquals("",testStore1.getAddress().getLocation().toString());
		assertEquals(AddressType.Unknown,testStore1.getAddress().getAddressType());		
	}

	@Test
	public void testStoreStringStringString() {
		assertEquals("PizzaStore4", testStore4.getStoreName().toString());
		assertEquals("206-953-5584", testStore4.getPhoneNumber().toString());
		assertEquals("Stuart St.",testStore4.getAddress().getLocation().toString());
		assertEquals(AddressType.Business,testStore4.getAddress().getAddressType());		
	}

	@Test
	public void testStoreStringString() {
		assertEquals("PizzaStore3", testStore3.getStoreName().toString());
		assertEquals("000-000-0000", testStore3.getPhoneNumber().toString());
		assertEquals("Stuart St.",testStore3.getAddress().getLocation().toString());
		assertEquals(AddressType.Business,testStore3.getAddress().getAddressType());		
	}

	@Test
	public void testStoreString() {
		assertEquals("PizzaStore2", testStore2.getStoreName().toString());
		assertEquals("000-000-0000", testStore2.getPhoneNumber().toString());
		assertEquals("",testStore2.getAddress().getLocation().toString());
		assertEquals(AddressType.Unknown,testStore2.getAddress().getAddressType());		
	}


	@Test
	public void testAddEmployee() {
		assertEquals(false,testStore4.getEmployeeSet().isEmpty());
		assertEquals(1,testStore4.getEmployeeSet().size());
		testStore4.addEmployee("Hasan", "hasan", "tda");
		assertEquals(false,testStore4.getEmployeeSet().isEmpty());
		assertEquals(2,testStore4.getEmployeeSet().size());
		
	}
	
	@Test
	public void testloginAttempt() {
		assertEquals(true,testStore4.loginAttempt("skshimon", "uda"));
		testStore4.addEmployee("Hasan", "hasan", "tda");
		assertEquals(false,testStore4.loginAttempt("skshimon", "tda"));
		assertEquals(false,testStore4.loginAttempt("hasan", "uda"));
		assertEquals(true,testStore4.loginAttempt("hasan", "tda"));
		
	}

}
