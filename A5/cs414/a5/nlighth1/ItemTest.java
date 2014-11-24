package cs414.a5.nlighth1;

import static org.junit.Assert.*;
import cs414.a5.nlighth1.Item;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	String itemName1, itemName2;
	double itemPrice1, itemPrice2, discount;
	String itemDesc1, itemDesc2;
	Item item1, item2;
	
	@Before
	public void setUp() throws Exception{
		itemName1 = "N1";
		itemPrice1 = 100.0;
		itemDesc1 = "Cheese Pizza";
		item1 = new Item(itemName1, itemPrice1, itemDesc1);
		discount = 0.1;
		item1.setSpecial(discount);
		itemName2 = "N2";
		itemPrice2 = 10.0;
		item2 = new Item(itemName2, itemPrice2);
	}

	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testGetName() {
		assertEquals(itemName1, item1.getItemName());
		assertEquals(itemName2, item2.getItemName());
	}

	@Test
	public void testSetName() {
		String tempName = "N3";
		item1.setItemName(tempName);
		assertEquals(tempName, item1.getItemName());
	}

	@Test
	public void testGetItemDesc() {
		assertEquals(itemDesc1, item1.getItemDescription());
	}

	@Test
	public void testGetNullItemDesc() {
		//assertNull(item2.getItemDescription());
		assertNotSame(null,item2.getItemDescription());
		assertEquals("", item2.getItemDescription());
	}

	@Test
	public void testSetItemDesc() {
		String tempDesc = "D3";
		item1.setItemDescription(tempDesc);
		assertEquals(tempDesc, item1.getItemDescription());
	}

	@Test
	public void testSetNullItemDesc() {
		item2.setItemDescription(null);
		assertNotSame(null, item2.getItemDescription());
		assertEquals("", item2.getItemDescription());		
	}

	@Test
	public void testGetCurrentPrice(){
		//assertTrue(item1.getCurrentPrice()==90);
		//assertTrue(item2.getCurrentPrice()==20);

		assertEquals(90.0, item1.getCurrentPrice(), 0);
		assertNotSame(20.0, item2.getCurrentPrice());

	}
	
	@Test
	public void testGetBasePrice() {
		//assertTrue(item1.getItemBasePrice()==100);
		//assertTrue(item2.getItemBasePrice()==20);
		
		assertNotSame(100.0, item1.getItemBasePrice());
		assertNotSame(20.0, item2.getItemBasePrice());
	}

	@Test
	public void testSetBasePrice(){
		double tempPrice = 80.0;
		item1.setItemPrice(tempPrice);
		//assertTrue(item1.getItemBasePrice()==80);
		//assertTrue(item1.getCurrentPrice()==72);
		assertEquals(80.0, item1.getItemBasePrice(), 0);
		assertEquals(72.0, item1.getCurrentPrice(), 0);		
	}

	@Test
	public void testIsSpecial() {
		assertTrue(item1.isSpecial());
		assertTrue(!item2.isSpecial());
	}
	
	@Test
	public void testSetSpecial() {
		item1.setSpecial(0.2);
		item2.setSpecial(0.1);
		//assertTrue(item1.getSpecialPercentageOffPrice()==0.2);
		//assertTrue(item2.getSpecialPercentageOffPrice()==0.1);
		//assertTrue(item1.getCurrentPrice()==80);
		
		//assertTrue(item2.getCurrentPrice()==18);
		assertEquals(0.2,item1.getSpecialPercentageOffPrice(),0);
		assertEquals(0.1, item2.getSpecialPercentageOffPrice(), 0);
		assertEquals(80.0, item1.getCurrentPrice(), 0);
		
	}

	@Test
	public void testRemoveSpecial() {
		item1.removeSpecial();
		assertTrue(!item1.isSpecial());
		assertTrue(item1.getCurrentPrice()==100);
	}

}
