package cs414.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginInfoTest {

	LoginInfo testLoginInfo1,testLoginInfo2,testLoginInfo3;
	String testLoginID1,testLoginID2,testLoginID3;
	String testPassWord1,testPassWord2,testPassWord3;
	
	Employee testEmployee1,testEmployee2,testEmployee3;
	String testName1,testName2,testName3;
	
	@Before
	public void setUp() throws Exception {
		testLoginID1 = "skshimon";
		testLoginID2 = "ctebbe";
		testLoginID3 = "yuqiu";
		testPassWord1 = "123";
		testPassWord2 = "456";
		testPassWord3 = "789";
		testLoginInfo1 = new LoginInfo(testLoginID1, testPassWord1);
		testLoginInfo2 = new LoginInfo(testLoginID2, testPassWord2);
		testLoginInfo3 = new LoginInfo(testLoginID3, testPassWord3);


		testName1 = "NAME1";
		testName2 = "NAME2";
		testName3 = "NAME3";		
		testEmployee1 = new Employee(testName1, Role.Manager);
		testEmployee2 = new Employee(testName2, Role.Cashier);
		testEmployee3 = new Employee(testName3, Role.Chef);
	
		testLoginInfo1.setLoginEmployee(testEmployee1);
	}

	@After
	public void tearDown() throws Exception {
	}

	

	@Test
	public void testGetLoginId() {
		assertEquals(testLoginID1, testLoginInfo1.getLoginId());
		assertEquals(testLoginID2, testLoginInfo2.getLoginId());
		assertEquals(testLoginID3, testLoginInfo3.getLoginId());

	}

	@Test
	public void testSetLoginId() {
		String testLoginID1_edit = "skShimon1";
		testLoginInfo1.setLoginId(testLoginID1_edit);
		assertEquals(testLoginID1_edit, testLoginInfo1.getLoginId());
		
	}

	@Test
	public void testGetPassword() {
		assertEquals(testPassWord1, testLoginInfo1.getPassword());
		assertEquals(testPassWord2, testLoginInfo2.getPassword());
		assertEquals(testPassWord3, testLoginInfo3.getPassword());

	}

	@Test
	public void testSetPassword() {
		String testPassWord1_edit = "123_edit";
		testLoginInfo1.setPassword(testPassWord1_edit);
		assertEquals(testPassWord1_edit, testLoginInfo1.getPassword());

	}

	@Test
	public void testGetLoginEmployee() {
		assertEquals(testEmployee1, testLoginInfo1.getLoginEmployee());
		assertEquals(null,testEmployee1.getEmployeeLoginInfo());
	}

	/*
	@Test
	public void testSetLoginEmployee() {
		fail("Not yet implemented");
	}
	*/
	
	@Test
	public void testMatchPassword() {
		assertTrue(testLoginInfo1.matchPassword(testPassWord1));
		assertFalse(testLoginInfo1.matchPassword(testPassWord2));
	}

}
