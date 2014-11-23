package cs414.a5.eid;

import static org.junit.Assert.*;
import cs414.a5.eid.Address;
import cs414.a5.eid.AddressType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {

	private Address deliveryAddress1,deliveryAddress2,deliveryAddress3;
	private String address2,address3;
	@Before
	public void setUp() throws Exception {
		address2 = "121 south Street";
		address3 = "122 south Street";
		deliveryAddress1 = new Address();
		deliveryAddress2 = new Address(address2);
		deliveryAddress3 = new Address(address3, AddressType.Home);

	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetLocation() {
		assertEquals("", deliveryAddress1.getLocation());
		assertEquals(address2, deliveryAddress2.getLocation());
		assertEquals(address3, deliveryAddress3.getLocation());

	}

	@Test
	public void testSetLocation() {
		deliveryAddress1.setLocation(address3);
		assertEquals(address3, deliveryAddress1.getLocation());
		deliveryAddress2.setLocation(null);
		assertEquals("", deliveryAddress2.getLocation());
		
	}

	@Test
	public void testGetAddressType() {
		assertEquals(AddressType.Unknown, deliveryAddress1.getAddressType());
		assertEquals(AddressType.Unknown, deliveryAddress2.getAddressType());
		assertEquals(AddressType.Home, deliveryAddress3.getAddressType());

	}

	@Test
	public void testSetAddressType() {
		deliveryAddress1.setAddressType(AddressType.Home);
		assertEquals(AddressType.Home, deliveryAddress1.getAddressType());
		deliveryAddress2.setAddressType(AddressType.Unknown);
		assertEquals(AddressType.Unknown, deliveryAddress2.getAddressType());
		deliveryAddress3.setAddressType(AddressType.Business);
		assertEquals(AddressType.Business, deliveryAddress3.getAddressType());
		
	}

}
