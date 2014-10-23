package cs414.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	Customer testCustomer1,testCustomer2;
	String firstName1;
	String firstName2;
	String lastName1;
	String lastName2;
	int membershipNumber1;
	int membershipNumber2;
	String phoneNumber1;
	String phoneNumber2;
	
	Address testAddress1 ;
	Address testAddress2 ;
	
	@Before
	public void setUp() throws Exception {
		firstName1 = "F1";
		firstName2 = "F2";
		lastName1 = "L1";
		lastName2 = "L2";
		membershipNumber1 = 1000;
		membershipNumber2 = 1001;
		phoneNumber1 = "000-000-0000";
		phoneNumber2 = "201-222-1231";
		
		testCustomer1 = new Customer(firstName1, lastName1);
		testCustomer2 = new Customer(firstName2, lastName2,phoneNumber2);
		
		testAddress1 = new Address("123 mason street", AddressType.Home);
		testAddress2 = new Address("123 mason street", AddressType.Home);
		
		testCustomer1.addNewAddress(testAddress1);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetFirstName() {
		assertEquals(firstName1, testCustomer1.getFirstName());
		assertEquals(firstName2, testCustomer2.getFirstName());		
	}

	@Test
	public void testSetFirstName() {
		assertEquals(firstName1, testCustomer1.getFirstName());
		assertEquals(firstName2, testCustomer2.getFirstName());		
		String firstName1_edit = "F1_edit";
		String firstName2_edit = "F2_edit";
		testCustomer1.setFirstName(firstName1_edit);
		testCustomer2.setFirstName(firstName2_edit);
		assertEquals(firstName1_edit, testCustomer1.getFirstName());
		assertEquals(firstName2_edit, testCustomer2.getFirstName());		
		testCustomer2.setFirstName(null);
		assertEquals("", testCustomer2.getFirstName());		
		testCustomer1.setFirstName(null);
		assertEquals("", testCustomer1.getFirstName());		
		
	}

	@Test
	public void testGetLastName() {
		assertEquals(lastName1, testCustomer1.getLastName());
		assertEquals(lastName2, testCustomer2.getLastName());		
	}

	@Test
	public void testSetLastName() {
		assertEquals(lastName1, testCustomer1.getLastName());
		assertEquals(lastName2, testCustomer2.getLastName());		
		String lastName1_edit = "L1_edit";
		String lastName2_edit = "L2_edit";
		testCustomer1.setLastName(lastName1_edit);
		testCustomer2.setLastName(lastName2_edit);
		assertEquals(lastName1_edit, testCustomer1.getLastName());
		assertEquals(lastName2_edit, testCustomer2.getLastName());		
		testCustomer2.setLastName(null);
		assertEquals("", testCustomer2.getLastName());		
		testCustomer1.setLastName(null);
		assertEquals("", testCustomer1.getLastName());		
		
	}

	@Test
	public void testGetRewardsPoint() {
		assertEquals(0, testCustomer1.getRewardsPoint());
		assertEquals(0, testCustomer2.getRewardsPoint());
	}

	@Test
	public void testSetRewardsPoint() {
		testCustomer1.setRewardsPoint(100);
		testCustomer2.setRewardsPoint(200);
		assertEquals(100, testCustomer1.getRewardsPoint());
		assertEquals(200, testCustomer2.getRewardsPoint());
	}

	@Test
	public void testGetMemberShipNumber() {
		assertNotSame(testCustomer1.getMemberShipNumber(), testCustomer2.getMemberShipNumber());

	}

	@Test
	public void testSetMemberShipNumber() {
		String membershipNumber1_edit = "1003";
		String membershipNumber2_edit = "1004";
		testCustomer1.setMemberShipNumber(membershipNumber1_edit);
		testCustomer2.setMemberShipNumber(membershipNumber2_edit);
		assertEquals(membershipNumber1_edit, testCustomer1.getMemberShipNumber());
		assertEquals(membershipNumber2_edit, testCustomer2.getMemberShipNumber());
	}

	@Test
	public void testGetCustomerPhoneNumber() {
		assertEquals(phoneNumber1, testCustomer1.getCustomerPhoneNumber());
		assertEquals(phoneNumber2, testCustomer2.getCustomerPhoneNumber());	
	}

	@Test
	public void testSetCustomerPhoneNumber() {
		String phoneNumber1_edit = "234-232-1113";
		testCustomer1.setCustomerPhoneNumber( phoneNumber1_edit);
		assertEquals(phoneNumber1_edit, testCustomer1.getCustomerPhoneNumber());
		String phoneNumber2_edit = null;
		testCustomer2.setCustomerPhoneNumber( phoneNumber2_edit);
		assertEquals(phoneNumber1, testCustomer2.getCustomerPhoneNumber());

	}




	@Test
	public void testAddNewAddress() {
		//testCustomer1.addNewAddress(testAddress1);
		assertTrue(testCustomer1.getCustomerAddresses().contains(testAddress1));
		testCustomer1.addNewAddress(testAddress2);
		assertTrue(testCustomer1.getCustomerAddresses().contains(testAddress2));

	}

	@Test
	public void testRemoveAddress() {
		assertTrue(testCustomer1.getCustomerAddresses().contains(testAddress1));
		testCustomer1.removeAddress(testAddress1);
		assertTrue(testCustomer1.getCustomerAddresses().isEmpty());		
	}

	@Test
	public void testAddOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveOrder() {
		fail("Not yet implemented");
	}

}
