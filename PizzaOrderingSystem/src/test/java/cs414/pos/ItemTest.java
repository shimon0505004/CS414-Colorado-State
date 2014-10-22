package cs414.pos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	String itemName1, itemName2;
	double itemPrice1, itemPrice2;
	String itemDesc1, itemDesc2;
	Item item1, item2;

	public void setUp() {
		itemName1 = "N1";
		itemPrice1 = 100.0;
		itemDesc1 = "Cheese Pizza";
		item1 = new Item(itemName1, itemPrice1, itemDesc1);
	}

	public void testGetName() {
		assertEquals(itemName1, item1);
	}

	public void testSetName() {

	}

	public void testSetNullName() {

	}

	public void testGetItemDesc() {

	}

	public void testGetNullItemDesc() {

	}

	public void testSetItemDesc() {

	}

	public void testSetNullItemDesc() {

	}

	public void testGetBasePrice() {

	}

	public void testSetBasePrice(){
		
	}
	
	public void testGetDiscoutPrice() {

	}

	public void testIsSpecial() {

	}

	public void testSetSpecial() {

	}

	public void testSetNegSpecial() {

	}

	public void testRemoveSpecial() {

	}

}
