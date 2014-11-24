package cs414.a5.nlighth1;

import cs414.a5.nlighth1.Employee;
import cs414.a5.nlighth1.LoginInfo;
import cs414.a5.nlighth1.Role;
import cs414.a5.nlighth1.Store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

	Employee testEmployee1,testEmployee2,testEmployee3,testEmployee4;
	String testName1,testName2,testName3,testName4;
	String testEmployee1ID, testEmployee2ID,testEmployee3ID;
	
	
	LoginInfo testLoginInfo1,testLoginInfo2,testLoginInfo3;
	String testLoginID1,testLoginID2,testLoginID3;
	String testPassWord1,testPassWord2,testPassWord3;

	@Before
	public void setUp() throws Exception {
		testName1 = "NAME1";
		testName2 = "NAME2";
		testName3 = "NAME3";		
		testName4 = "NAME4";
		testEmployee1 = new Employee(testName1, Role.Manager);
		testEmployee2 = new Employee(testName2, Role.Cashier);
		testEmployee3 = new Employee(testName3, Role.Chef);
		testEmployee4 = new Employee(testName3, Role.DeliveryMan);
		

		testLoginID1 = "skshimon";
		testLoginID2 = "ctebbe";
		testLoginID3 = "yuqiu";
		testPassWord1 = "123";
		testPassWord2 = "456";
		testPassWord3 = "789";
		testLoginInfo1 = new LoginInfo(testLoginID1, testPassWord1);
		testLoginInfo2 = new LoginInfo(testLoginID2, testPassWord2);
		testLoginInfo3 = new LoginInfo(testLoginID3, testPassWord3);
		
		testEmployee1.setEmployeeLoginInfo(testLoginInfo1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEmployeeName() {
		assertEquals(testName1, testEmployee1.getEmployeeName());
		assertEquals(testName2, testEmployee2.getEmployeeName());
		assertEquals(testName3, testEmployee3.getEmployeeName());

	}

	@Test
	public void testSetEmployeeName() {
		String Name1_edit = "NAME1_EDIT";
		String Name2_edit = "NAME2_EDIT";
		String Name3_edit = "NAME3_EDIT";
		testEmployee1.setEmployeeName(Name1_edit);
		testEmployee2.setEmployeeName(Name2_edit);
		testEmployee3.setEmployeeName(Name3_edit);
		assertEquals(Name1_edit, testEmployee1.getEmployeeName());
		assertEquals(Name2_edit, testEmployee2.getEmployeeName());
		assertEquals(Name3_edit, testEmployee3.getEmployeeName());
		
	}

	@Test
	public void testGetEmployeeLoginInfo() {
		assertEquals(testLoginInfo1, testEmployee1.getEmployeeLoginInfo());
		assertEquals(null, testEmployee2.getEmployeeLoginInfo());
		assertEquals(null, testEmployee3.getEmployeeLoginInfo());
		//assertEquals(testEmployee1, testLoginInfo1.getLoginEmployee());
	}

	@Test
	public void testSetEmployeeLoginInfo() {
		testEmployee2.setEmployeeLoginInfo(testLoginInfo2);
		assertEquals(testLoginInfo2, testEmployee2.getEmployeeLoginInfo());
		//assertEquals(testEmployee2, testLoginInfo2.getLoginEmployee());
	}

	@Test
	public void testMatchLoginInfo() {
		assertTrue(testEmployee1.matchLoginInfo(testLoginID1, testPassWord1));
		assertFalse(testEmployee2.matchLoginInfo(testLoginID2, testPassWord2));
		
	}

	@Test
	public void testGetPrivilege() {
		assertEquals(Role.Manager, testEmployee1.getRole());
		assertEquals(Role.Cashier, testEmployee2.getRole());
		assertEquals(Role.Chef, testEmployee3.getRole());
	}

	@Test
	public void testSetPrivilege() {
		testEmployee1.setRole(Role.Cashier);
		assertEquals(Role.Cashier, testEmployee1.getRole());
	}

	@Test
	public void testGetWorksForStore() {
        /*
		assertEquals(null, testEmployee1.getWorksForStore());
		assertEquals(null, testEmployee2.getWorksForStore());
		assertEquals(null, testEmployee3.getWorksForStore());
		*/
	}

	@Test
	public void testSetWorksForStore() {
		Store testStore4 = new Store("PizzaStore4","206-953-5584","Stuart St.");
		//testEmployee1.setWorksForStore(testStore4);
		
	}

}
