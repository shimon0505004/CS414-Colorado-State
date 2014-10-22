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
	public void testGetEmployeeSet() {
		assertEquals(0, testStore1.getEmployeeSet().size());
		assertEquals(0, testStore2.getEmployeeSet().size());
		assertEquals(0, testStore3.getEmployeeSet().size());
		assertEquals(1, testStore4.getEmployeeSet().size());

	}

	@Test
	public void testGetStoreName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStoreName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPhoneNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPhoneNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoginAttempt() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitDefineMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testDefineMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetOfMenus() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuthorizeEditMenus() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSpecial() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMenuItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMenuItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetOfKiosk() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSetOfKiosk() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetOfRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSetOfRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetOfPlacedOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSetOfPlacedOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMembers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSetOfItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSetOfItems() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveState() {
		fail("Not yet implemented");
	}

	@Test
	public void testOpenState() {
		fail("Not yet implemented");
	}

}
