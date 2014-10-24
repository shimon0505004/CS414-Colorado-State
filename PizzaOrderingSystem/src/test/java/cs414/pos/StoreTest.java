package cs414.pos;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

	Store testStore1 , testStore2, testStore3, testStore4;
	
	Employee testEmployee1,testEmployee2,testEmployee3;
	String testName1,testName2,testName3;
		

	LoginInfo testLoginInfo1,testLoginInfo2,testLoginInfo3;
	String testLoginID1,testLoginID2,testLoginID3;
	String testPassWord1,testPassWord2,testPassWord3;

	String menuName1, menuName2, menuDesc1, menuDesc2;
	Item test_item1, test_item2;
	
	Kiosk kiosk1, kiosk2;
	
	@Before
	public void setUp() throws Exception {
		testStore1 = new Store();
		testStore2 = new Store("PizzaStore2");		
		testStore3 = new Store("PizzaStore3","Stuart St.");		
		testStore4 = new Store("PizzaStore4","206-953-5584","Stuart St.");		


		testStore4.addEmployee("Shimon", "skshimon", "uda", Privilege.Cashier);

		testName1 = "Shimon";
		testLoginID1 = "skshimon";
		testPassWord1 = "uda";
		
		testName2 = "Caleb";
		testLoginID2 = "ctebbe";
		testPassWord2 = "cte";
		
		testName3 = "Nathan";
		testLoginID3 = "nlightHart";
		testPassWord3 = "nli";
		
		testStore4.addEmployee(testName1, testLoginID1, testPassWord1,Privilege.Cashier);	
		testStore4.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		testStore4.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);	
		
		menuName1 = "Menu1";
		menuName2 = "Menu2";
		menuDesc1 = "MenuDesc1";
		menuDesc2 = "MenuDesc2";
		test_item1 = new Item("item1", 10, "Test Item 1");
		test_item2 = new Item("item2", 5, "Test Item 2");
	
		kiosk1 = new Kiosk(1, testStore3);
		kiosk2 = new Kiosk(2, testStore3);
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
		assertEquals(4, testStore4.getEmployeeSet().size());

	}

	@Test
	public void testGetStoreName() {
		assertEquals("PizzaStore4", testStore4.getStoreName().toString());
		assertEquals("PizzaStore3", testStore3.getStoreName().toString());
		assertEquals("PizzaStore2", testStore2.getStoreName().toString());
		assertEquals("", testStore1.getStoreName().toString());
	}

	@Test
	public void testSetStoreName() {
		String testStoreName1_new= "PizzaStore1";
		testStore1.setStoreName(testStoreName1_new);
		assertEquals(testStoreName1_new, testStore1.getStoreName().toString());
		testStore1.setStoreName(null);
		assertEquals("",testStore1.getStoreName());
	}

	@Test
	public void testGetPhoneNumber() {
		assertEquals("000-000-0000", testStore1.getPhoneNumber().toString());
		assertEquals("000-000-0000", testStore2.getPhoneNumber().toString());
		assertEquals("000-000-0000", testStore3.getPhoneNumber().toString());
		assertEquals("206-953-5584", testStore4.getPhoneNumber().toString());
		
	}

	@Test
	public void testSetPhoneNumber() {
		String testNumber1_new = "123-435-2345";
		String testNumber2_new = "123-435-2346";
		String testNumber3_new = "123-435-2347";
		String testNumber4_new = "123-435-2348";
		testStore1.setPhoneNumber(testNumber1_new);
		testStore2.setPhoneNumber(testNumber2_new);
		testStore3.setPhoneNumber(testNumber3_new);
		testStore4.setPhoneNumber(testNumber4_new);
		assertEquals(testNumber1_new, testStore1.getPhoneNumber().toString());
		assertEquals(testNumber2_new, testStore2.getPhoneNumber().toString());
		assertEquals(testNumber3_new, testStore3.getPhoneNumber().toString());
		assertEquals(testNumber4_new, testStore4.getPhoneNumber().toString());
	
	}

	@Test
	public void testGetAddress() {
		assertEquals("",testStore1.getAddress().getLocation().toString());
		assertEquals("",testStore2.getAddress().getLocation().toString());
		assertEquals("Stuart St.",testStore3.getAddress().getLocation().toString());
		assertEquals("Stuart St.",testStore4.getAddress().getLocation().toString());
	}

	@Test
	public void testSetAddress() {
		String testAddress1_new = "Pitkin St.";
		String testAddress2_new = "PitkinOus St.";
		testStore1.setAddress(testAddress1_new);
		testStore2.setAddress(testAddress2_new);
		assertEquals(testAddress1_new,testStore1.getAddress().getLocation().toString());
		assertEquals(testAddress2_new,testStore2.getAddress().getLocation().toString());
		
	}

	@Test
	public void testAddEmployee() {

		assertEquals(0, testStore3.getEmployeeSet().size());
		Employee testEmployee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1,Privilege.Cashier);	
		assertEquals(1, testStore3.getEmployeeSet().size());
		Employee testEmployee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Cashier);	
		assertEquals(2, testStore3.getEmployeeSet().size());
		Employee testEmployee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Cashier);	
		assertEquals(3, testStore3.getEmployeeSet().size());
		
		String test_Employee1 = testEmployee1.getEmployeeID();
		String test_Employee2 = testEmployee2.getEmployeeID();
		String test_Employee3 = testEmployee3.getEmployeeID();
		
		assertEquals(test_Employee1, testStore3.getEmployee(test_Employee1).getEmployeeID());
		assertEquals(test_Employee2, testStore3.getEmployee(test_Employee2).getEmployeeID());
		assertEquals(test_Employee3, testStore3.getEmployee(test_Employee3).getEmployeeID());
		
		assertEquals(testStore3, testStore3.getEmployee(test_Employee1).getWorksForStore());
		assertEquals(testStore3, testStore3.getEmployee(test_Employee2).getWorksForStore());
		assertEquals(testStore3, testStore3.getEmployee(test_Employee3).getWorksForStore());
		
		assertEquals(testLoginID1, testStore3.getEmployee(test_Employee1).getEmployeeLoginInfo().getLoginId());
		assertEquals(testLoginID2, testStore3.getEmployee(test_Employee2).getEmployeeLoginInfo().getLoginId());
		assertEquals(testLoginID3, testStore3.getEmployee(test_Employee3).getEmployeeLoginInfo().getLoginId());

		assertEquals(test_Employee1, testStore3.getEmployee(test_Employee1).getEmployeeLoginInfo().getLoginEmployee().getEmployeeID());
		assertEquals(test_Employee2, testStore3.getEmployee(test_Employee2).getEmployeeLoginInfo().getLoginEmployee().getEmployeeID());
		assertEquals(test_Employee3, testStore3.getEmployee(test_Employee3).getEmployeeLoginInfo().getLoginEmployee().getEmployeeID());

		assertEquals(Privilege.Cashier,testStore3.getEmployee(test_Employee1).getPrivilege());
		assertEquals(Privilege.Cashier,testStore3.getEmployee(test_Employee2).getPrivilege());
		assertEquals(Privilege.Cashier,testStore3.getEmployee(test_Employee3).getPrivilege());
	}

	@Test
	public void testAddEmployeeWithPriviledge() {

		assertEquals(0, testStore3.getEmployeeSet().size());
		String test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1,0);	
		assertEquals(1, testStore3.getEmployeeSet().size());
		String test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,1);	
		assertEquals(2, testStore3.getEmployeeSet().size());
		String test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,2);	
		assertEquals(3, testStore3.getEmployeeSet().size());
		
		assertEquals(test_Employee1, testStore3.getEmployee(test_Employee1).getEmployeeID());
		assertEquals(test_Employee2, testStore3.getEmployee(test_Employee2).getEmployeeID());
		assertEquals(test_Employee3, testStore3.getEmployee(test_Employee3).getEmployeeID());
		
		assertEquals(testStore3, testStore3.getEmployee(test_Employee1).getWorksForStore());
		assertEquals(testStore3, testStore3.getEmployee(test_Employee2).getWorksForStore());
		assertEquals(testStore3, testStore3.getEmployee(test_Employee3).getWorksForStore());
		
		assertEquals(testLoginID1, testStore3.getEmployee(test_Employee1).getEmployeeLoginInfo().getLoginId());
		assertEquals(testLoginID2, testStore3.getEmployee(test_Employee2).getEmployeeLoginInfo().getLoginId());
		assertEquals(testLoginID3, testStore3.getEmployee(test_Employee3).getEmployeeLoginInfo().getLoginId());

		assertEquals(test_Employee1, testStore3.getEmployee(test_Employee1).getEmployeeLoginInfo().getLoginEmployee().getEmployeeID());
		assertEquals(test_Employee2, testStore3.getEmployee(test_Employee2).getEmployeeLoginInfo().getLoginEmployee().getEmployeeID());
		assertEquals(test_Employee3, testStore3.getEmployee(test_Employee3).getEmployeeLoginInfo().getLoginEmployee().getEmployeeID());

		assertEquals(Privilege.Manager,testStore3.getEmployee(test_Employee1).getPrivilege());
		assertEquals(Privilege.Cashier,testStore3.getEmployee(test_Employee2).getPrivilege());
		assertEquals(Privilege.Chef,testStore3.getEmployee(test_Employee3).getPrivilege());

	}
	
	@Test
	public void testAddNewMember(){
		String testFirstName1 = "Shimon";
		String testLastName1 = "Shimon";
		String phoneNumber = "888-888-8888";
		assertEquals(0, testStore4.getMembers().size());
		Customer newCustomer = testStore4.addNewMember(testFirstName1, testLastName1, phoneNumber);
		assertEquals(1, testStore4.getMembers().size());
			
		
	}
	
	@Test
	public void testGetMember(){
		String testFirstName1 = "Shimon";
		String testLastName1 = "Shimon";
		String phoneNumber = "888-888-8888";
		Customer newCustomer = testStore4.addNewMember(testFirstName1, testLastName1, phoneNumber);
		assertEquals(newCustomer,testStore4.getMember(newCustomer.getMemberShipNumber()));
	}

	@Test
	public void testLoginAttempt() {
		assertTrue(testStore4.loginAttempt(testLoginID1, testPassWord1));
		assertFalse(testStore4.loginAttempt(testLoginID1, testPassWord2));
		assertFalse(testStore3.loginAttempt(testLoginID1, testPassWord2));

	}

	@Test
	public void testInitDefineMenu() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Privilege.Cashier);	
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		assertFalse(testStore3.initDefineMenu(test_Employee1));
		assertFalse(testStore3.initDefineMenu(test_Employee2));
		assertTrue(testStore3.initDefineMenu(test_Employee3));
		assertFalse(testStore4.initDefineMenu(test_Employee3));
	}

	@Test
	public void testDefineMenu() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Privilege.Cashier);	
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		Menu m = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(menuName1, m.getMenuName());
		assertEquals(menuDesc1, m.getMenuDescription());
		assertNull(testStore3.defineMenu(test_Employee2, menuName1, menuDesc1));
		assertNull(testStore3.defineMenu(test_Employee1, menuName1, menuDesc1));
	}

	@Test
	public void testGetSetOfMenus() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(1, testStore3.getSetOfMenus().size());
		testStore3.defineMenu(test_Employee3, menuName2, menuDesc2);
		assertEquals(2, testStore3.getSetOfMenus().size());
	}

	@Test
	public void testAuthorizeEditMenus() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Privilege.Cashier);	
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);

		testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		testStore3.defineMenu(test_Employee3, menuName2, menuDesc2);
		assertNull(testStore3.authorizeEditMenus(test_Employee1));
		assertNull(testStore3.authorizeEditMenus(test_Employee2));
		assertEquals(2, testStore3.getSetOfMenus().size());
	}

	@Test
	public void testEditMenu() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		m1.addItem(test_item1);
		m1.addItem(test_item2);
		assertTrue(testStore3.editMenu(test_Employee3, m1).contains(test_item1));
		assertTrue(testStore3.editMenu(test_Employee3, m1).contains(test_item2));
	}

	@Test
	public void testSetSpecial() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		m1.addItem(test_item1);
		testStore3.setSpecial(test_Employee3, test_item1, 0.1);
		assertTrue(test_item1.isSpecial());
		
		//TODO: to be determined...
		//Not sure should I add this or not:
		
		/*testStore3.setSpecial(test_Employee3, test_item2, 0.2);
		assertFalse(test_item2.isSpecial());
		*/
	}

	@Test
	public void testRemoveMenuItems() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		m1.addItem(test_item1);
		m1.addItem(test_item2);
		Set<Item> itemSet = new HashSet<Item>();
		itemSet.add(test_item1);
		testStore3.removeMenuItems(test_Employee3, m1, itemSet);
		assertFalse(m1.getMenuItems().contains(test_item1));
		assertTrue(m1.getMenuItems().contains(test_item2));
	}

	@Test
	public void testAddMenuItem() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Privilege.Cashier);	
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(0, testStore3.getSetOfMenus().size());
		testStore3.addMenuItem(test_Employee3, m1, "item1", 10, "test item 1");
		assertEquals(1, testStore3.getSetOfMenus().size());
		testStore3.addMenuItem(test_Employee3, m1, "item2", 8, "test item 2");
		assertEquals(2, testStore3.getSetOfMenus().size());
		testStore3.addMenuItem(test_Employee2, m1, "item3", 15, "test item 3");
		assertEquals(2, testStore3.getSetOfMenus().size());
		testStore3.addMenuItem(test_Employee1, m1, "item4", 19, "test item 4");
		assertEquals(2, testStore3.getSetOfMenus().size());
	}

	@Test
	public void testGetSetOfKiosk() {
		assertEquals(0, testStore4.getSetOfKiosk().size());
		assertEquals(2, testStore3.getSetOfKiosk());
	}

	@Test
	public void testSetSetOfKiosk() {
		Kiosk tempKiosk1 = new Kiosk(3);
		Kiosk tempKiosk2 = new Kiosk(4);
		Set<Kiosk> kioskSet = new HashSet<Kiosk>();
		kioskSet.add(tempKiosk1);
		kioskSet.add(tempKiosk2);
		assertEquals(0, testStore4.getSetOfKiosk());
		testStore4.setSetOfKiosk(kioskSet);
		assertEquals(2, testStore4.getSetOfKiosk());
		assertTrue(testStore4.getSetOfKiosk().contains(tempKiosk1));
		assertTrue(testStore4.getSetOfKiosk().contains(tempKiosk2));
	}
	
	@Test
	public void testAddKiosk(){
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Privilege.Cashier);	
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		assertEquals(2, testStore3.getSetOfKiosk().size());
		testStore3.addKiosk(test_Employee1, 3);
		assertEquals(2, testStore4.getSetOfKiosk().size());
		testStore3.addKiosk(test_Employee2, 4);
		assertEquals(2, testStore4.getSetOfKiosk().size());
		testStore3.addKiosk(test_Employee3, 5);
		assertEquals(3, testStore4.getSetOfKiosk().size());
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
	public void testAddRegister(){
		
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
