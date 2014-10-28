package cs414.pos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static cs414.pos.Main.deserialize;
import static cs414.pos.Main.serialize;
import static org.junit.Assert.*;

public class StoreTest {

	Store testStore1 , testStore2, testStore3, testStore4;
	
	Employee testEmployee1_store4,testEmployee2_store4,testEmployee3_store4;
	String testName1,testName2,testName3;
		

	LoginInfo testLoginInfo1,testLoginInfo2,testLoginInfo3;
	String testLoginID1,testLoginID2,testLoginID3;
	String testPassWord1,testPassWord2,testPassWord3;

	String menuName1, menuName2, menuDesc1, menuDesc2;
	Item test_item1, test_item2;
	
	Kiosk testKiosk1, testKiosk2;
	Register testRegister1, testRegister2;
	
	Order testOrder1, testOrder2;
	int testOrderID1, testOrderID2, testOrderID3;
	@Before
	public void setUp() throws Exception {
		testStore1 = new Store();
		testStore2 = new Store("PizzaStore2");		
		testStore3 = new Store("PizzaStore3","Stuart St.");		
		testStore4 = new Store("PizzaStore4","206-953-5584","Stuart St.");		



		testName1 = "Shimon";
		testLoginID1 = "skshimon";
		testPassWord1 = "uda";
		
		testName2 = "Caleb";
		testLoginID2 = "ctebbe";
		testPassWord2 = "cte";
		
		testName3 = "Nathan";
		testLoginID3 = "nlightHart";
		testPassWord3 = "nli";
		
		testEmployee1_store4 = testStore4.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		testEmployee2_store4 = testStore4.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		testEmployee3_store4 = testStore4.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		
		menuName1 = "Menu1";
		menuName2 = "Menu2";
		menuDesc1 = "MenuDesc1";
		menuDesc2 = "MenuDesc2";
		test_item1 = new Item("item1", 10, "Test Item 1");
		test_item2 = new Item("item2", 5, "Test Item 2");

        Employee e3 = testStore3.addEmployee("c", "c", "pw", Role.Manager);
        testKiosk1= testStore3.addKiosk(e3, 1);
        testKiosk2 = testStore3.addKiosk(e3, 2);
        testRegister1= testStore3.addRegister(e3, 1);
        testRegister2 = testStore3.addRegister(e3, 2);

		testOrderID1 = 1;
		testOrderID2 = 2;
		testOrderID3 = 3;
	}

	@After
	public void tearDown() throws Exception {
	}

    @Test public void basicSerializeTest() throws IOException, ClassNotFoundException {
        String f = "testSave.ser";
        Store s = new Store();
        Employee manager = s.addEmployee("bob", "bob", "pw_bob", Role.Manager);

        Menu m0 = s.defineMenu(manager, "menu0", "menu0_desc");
        s.addMenuItem(manager, m0, "pizza0", 5.0, "cheesy");

        // save Store's state
        serialize(new FileOutputStream(f), s);
        // open Store's state
        Store s2 = deserialize(new FileInputStream(f));

        assertEquals(1,s2.getSetOfMenus().size());
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
		assertEquals(1, testStore3.getEmployeeSet().size());
		assertEquals(3, testStore4.getEmployeeSet().size());

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

		assertEquals(1, testStore3.getEmployeeSet().size());
		Employee testEmployee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		assertEquals(2, testStore3.getEmployeeSet().size());
		Employee testEmployee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		assertEquals(3, testStore3.getEmployeeSet().size());
		Employee testEmployee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		assertEquals(4, testStore3.getEmployeeSet().size());
		
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

		assertEquals(Role.Cashier,testStore3.getEmployee(test_Employee1).getRole());
		assertEquals(Role.Chef,testStore3.getEmployee(test_Employee2).getRole());
		assertEquals(Role.Manager,testStore3.getEmployee(test_Employee3).getRole());
		
		
		assertEquals(testEmployee1,testStore3.loginAttempt(testLoginID1, testPassWord1));
		assertEquals(testEmployee2,testStore3.loginAttempt(testLoginID2, testPassWord2));
		assertEquals(testEmployee3,testStore3.loginAttempt(testLoginID3, testPassWord3));
		
		assertEquals(null,testStore3.loginAttempt(testLoginID1, testPassWord2));
		assertEquals(null,testStore3.loginAttempt(testLoginID2, testPassWord3));
		assertEquals(null,testStore3.loginAttempt(testLoginID3, testPassWord1));
		
				
	}

	@Test
	public void testAddEmployeeWithPriviledge() {

		assertEquals(1, testStore3.getEmployeeSet().size());
		String test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1,0);	
		assertEquals(2, testStore3.getEmployeeSet().size());
		String test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,1);	
		assertEquals(3, testStore3.getEmployeeSet().size());
		String test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,2);	
		assertEquals(4, testStore3.getEmployeeSet().size());
		
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

		assertEquals(Role.Manager,testStore3.getEmployee(test_Employee1).getRole());
		assertEquals(Role.Cashier,testStore3.getEmployee(test_Employee2).getRole());
		assertEquals(Role.Chef,testStore3.getEmployee(test_Employee3).getRole());

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
		assertEquals(null,testStore4.loginAttempt(testLoginID1, testPassWord2));
		assertEquals(null,testStore4.loginAttempt(testLoginID2, testPassWord3));
		assertEquals(null,testStore4.loginAttempt(testLoginID3, testPassWord1));

		assertEquals(null,testStore3.loginAttempt(testLoginID1, testPassWord2));
		assertEquals(null,testStore3.loginAttempt(testLoginID2, testPassWord3));
		assertEquals(null,testStore3.loginAttempt(testLoginID3, testPassWord1));
		
		assertEquals(testEmployee1_store4,testStore4.loginAttempt(testLoginID1, testPassWord1));
		assertEquals(testEmployee2_store4,testStore4.loginAttempt(testLoginID2, testPassWord2));
		assertEquals(testEmployee3_store4,testStore4.loginAttempt(testLoginID3, testPassWord3));
		
	}

	@Test
	public void testInitDefineMenu() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		assertFalse(testStore3.initDefineMenu(test_Employee1));
		assertFalse(testStore3.initDefineMenu(test_Employee2));
		assertTrue(testStore3.initDefineMenu(test_Employee3));
		//assertFalse(testStore4.initDefineMenu(test_Employee3));
	}

	@Test
	public void testDefineMenu() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Menu m = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(menuName1, m.getMenuName());
		assertEquals(menuDesc1, m.getMenuDescription());
		assertNull(testStore3.defineMenu(test_Employee2, menuName1, menuDesc1));
		assertNull(testStore3.defineMenu(test_Employee1, menuName1, menuDesc1));
	}

	@Test
	public void testGetSetOfMenus() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(1, testStore3.getSetOfMenus().size());
		testStore3.defineMenu(test_Employee3, menuName2, menuDesc2);
		assertEquals(2, testStore3.getSetOfMenus().size());
	}

	@Test
	public void testAuthorizeEditMenus() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);

		testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		testStore3.defineMenu(test_Employee3, menuName2, menuDesc2);
		assertNull(testStore3.authorizeEditMenus(test_Employee1));
		assertNull(testStore3.authorizeEditMenus(test_Employee2));
		assertEquals(2, testStore3.getSetOfMenus().size());
	}

	@Test
	public void testEditMenu() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		m1.addItem(test_item1);
		m1.addItem(test_item2);
		assertTrue(testStore3.editMenu(test_Employee3, m1).contains(test_item1));
		assertTrue(testStore3.editMenu(test_Employee3, m1).contains(test_item2));
	}

	@Test
	public void testSetSpecial() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
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
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		m1.addItem(test_item1);
		m1.addItem(test_item2);
		Set<Item> itemSet = new HashSet<Item>();
		itemSet.add(test_item1);
		assertTrue(m1.getMenuItems().contains(test_item1));
		testStore3.removeMenuItems(test_Employee3, m1, itemSet);
		assertFalse(m1.getMenuItems().contains(test_item1));
		assertTrue(m1.getMenuItems().contains(test_item2));
	}

	@Test
	public void testAddMenuItem() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(0, m1.getMenuItems().size());
		testStore3.addMenuItem(test_Employee3, m1, "item1", 10, "test item 1");
		assertEquals(1, m1.getMenuItems().size());
		testStore3.addMenuItem(test_Employee3, m1, "item2", 8, "test item 2");
		assertEquals(2, m1.getMenuItems().size());
		testStore3.addMenuItem(test_Employee2, m1, "item3", 15, "test item 3");
		assertEquals(2, m1.getMenuItems().size());
		testStore3.addMenuItem(test_Employee1, m1, "item4", 19, "test item 4");
		assertEquals(2, m1.getMenuItems().size());
	}

	@Test
	public void testGetSetOfKiosk() {
		assertEquals(2, testStore3.getSetOfKiosk().size());
		assertTrue(testStore3.getSetOfKiosk().contains(testKiosk1));
		assertTrue(testStore3.getSetOfKiosk().contains(testKiosk2));
	}

	@Test
	public void testSetSetOfKiosk() {
		Kiosk tempKiosk1 = new Kiosk(3);
		Kiosk tempKiosk2 = new Kiosk(4);
		Set<Kiosk> kioskSet = new HashSet<Kiosk>();
		kioskSet.add(tempKiosk1);
		kioskSet.add(tempKiosk2);
		assertEquals(0, testStore4.getSetOfKiosk().size());
		testStore4.setSetOfKiosk(kioskSet);
		assertEquals(2, testStore4.getSetOfKiosk().size());
		assertTrue(testStore4.getSetOfKiosk().contains(tempKiosk1));
		assertTrue(testStore4.getSetOfKiosk().contains(tempKiosk2));
	}
	
	@Test
	public void testAddKiosk(){
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		assertEquals(2, testStore3.getSetOfKiosk().size());
		testStore3.addKiosk(test_Employee1, 3);
		assertEquals(2, testStore3.getSetOfKiosk().size());
		testStore3.addKiosk(test_Employee2, 4);
		assertEquals(2, testStore3.getSetOfKiosk().size());
		testStore3.addKiosk(test_Employee3, 5);
		assertEquals(3, testStore3.getSetOfKiosk().size());
	}
	

	@Test
	public void testGetSetOfRegister() {
		assertEquals(2, testStore3.getSetOfRegister().size());
		assertTrue(testStore3.getSetOfRegister().contains(testRegister1));
		assertTrue(testStore3.getSetOfRegister().contains(testRegister2));
	}

	@Test
	public void testSetSetOfRegister() {
		Register tempRegister1 = new Register(3);
		Register tempRegister2 = new Register(4);
		Set<Register> registerSet = new HashSet<Register>();
		registerSet.add(tempRegister1);
		registerSet.add(tempRegister2);
		assertEquals(0, testStore4.getSetOfRegister().size());
		testStore4.setSetOfRegister(registerSet);
		assertEquals(2, testStore4.getSetOfRegister().size());
		assertTrue(testStore4.getSetOfRegister().contains(tempRegister1));
		assertTrue(testStore4.getSetOfRegister().contains(tempRegister2));	
	}

	@Test
	public void testAddRegister(){
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		assertEquals(2, testStore3.getSetOfRegister().size());
		testStore3.addRegister(test_Employee1, 3);
		assertEquals(2, testStore3.getSetOfRegister().size());
		testStore3.addRegister(test_Employee2, 4);
		assertEquals(2, testStore3.getSetOfRegister().size());
		testStore3.addRegister(test_Employee3, 5);
		assertEquals(3, testStore3.getSetOfRegister().size());
	}
	
	@Test
	public void testCreateOrder(){
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Order newOrder1 = testStore3.createOrder(test_Employee1, testOrderID1);
		Order newOrder2 = testStore3.createOrder(test_Employee2, testOrderID2);
		Order newOrder3 = testStore3.createOrder(test_Employee3, testOrderID3);
		assertEquals(testOrderID1, newOrder1.getOrderID());
		assertNull(newOrder2);
		assertEquals(testOrderID3, newOrder3.getOrderID());
	}
	
	@Test
	public void testGetSetOfPlacedOrder() {
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		assertEquals(0, testStore3.getSetOfPlacedOrder().size());
		Order newOrder1 = testStore3.createOrder(test_Employee1, testOrderID1);
		Order newOrder3 = testStore3.createOrder(test_Employee3, testOrderID3);
		assertEquals(2, testStore3.getSetOfPlacedOrder().size());
		assertTrue(testStore3.getSetOfPlacedOrder().contains(newOrder1));
		assertTrue(testStore3.getSetOfPlacedOrder().contains(newOrder3));
	}

	@Test
	public void testSetSetOfPlacedOrder() {
		Order tempOrder1 = new Order(3);
		Order tempOrder2 = new Order(4);
		Set<Order> tempOrderSet = new HashSet<Order>();
		tempOrderSet.add(tempOrder1);
		tempOrderSet.add(tempOrder2);
		assertEquals(0, testStore3.getSetOfPlacedOrder().size());
		testStore3.setSetOfPlacedOrder(tempOrderSet);
		assertEquals(2, testStore3.getSetOfPlacedOrder().size());
	}


	@Test
	public void testGetSetOfItems() {
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);
		Menu m1 = testStore3.defineMenu(test_Employee3, menuName1, menuDesc1);
		assertEquals(0, testStore3.getSetOfItems().size());
		testStore3.addMenuItem(test_Employee3, m1, "item1", 10, "test item 1");
		assertEquals(1, testStore3.getSetOfItems().size());
		testStore3.addMenuItem(test_Employee3, m1, "item2", 8, "test item 2");
		assertEquals(2, testStore3.getSetOfItems().size());
	}

	@Test
	public void testSetSetOfItems() {
		Set<Item> newItemSet = new HashSet<Item>();
		newItemSet.add(test_item1);
		newItemSet.add(test_item2);
		/*
		Employee test_Employee1 = testStore3.addEmployee(testName1, testLoginID1, testPassWord1, Privilege.Cashier);	
		Employee test_Employee2 = testStore3.addEmployee(testName2, testLoginID2, testPassWord2,Privilege.Chef);	
		Employee test_Employee3 = testStore3.addEmployee(testName3, testLoginID3, testPassWord3,Privilege.Manager);
		*/
		assertEquals(0, testStore3.getSetOfItems().size());
		testStore3.setSetOfItems(newItemSet);
		assertEquals(2, testStore3.getSetOfItems().size());
	}


}
