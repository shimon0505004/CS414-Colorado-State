package cs414.pos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class StoreTest {

	Store testStore1 , testStore2, testStore3, testStore4;
	
	Employee testEmployee1,testEmployee2,testEmployee3;
	String testName1,testName2,testName3;
		

	LoginInfo testLoginInfo1,testLoginInfo2,testLoginInfo3;
	String testLoginID1,testLoginID2,testLoginID3;
	String testPassWord1,testPassWord2,testPassWord3;

	
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
		Customer newCustomer = testStore4.addNewMember(testFirstName1, testFirstName1, phoneNumber);
		assertEquals(1, testStore4.getMembers().size());
			
		
	}
	
	@Test
	public void testGetMember(){
		String testFirstName1 = "Shimon";
		String testLastName1 = "Shimon";
		String phoneNumber = "888-888-8888";
		Customer newCustomer = testStore4.addNewMember(testFirstName1, testFirstName1, phoneNumber);
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
