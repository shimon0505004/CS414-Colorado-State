package cs414.a5.nlighth1;

import static org.junit.Assert.*;
import cs414.a5.nlighth1.Card;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

	private Card card1,card2;
	private String cardNumber1,cardNumber2;
	private String cardExpiryDate1,cardExpiryDate2;
	private String cv2_1,cv2_2;
	
	@Before
	public void setUp() throws Exception {
		cardNumber1 = "4023223123412310";
		cardNumber2 = "4023223123412311";
		cardExpiryDate1 = "16 DEC 14";
		cardExpiryDate2 = "16 DEC 15";
		cv2_1 = "342";
		cv2_2 = "244";
		
		card1 = new Card();
		card2 = new Card(cardNumber2,cardExpiryDate2,cv2_2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdateCardInfo() {
		assertEquals("", card1.getCardNumber());
		assertEquals("", card1.getCardExpirationDate());
		assertEquals("", card1.getCv2());
		card1.updateCardInfo(cardNumber1, cardExpiryDate1, cv2_1);
		assertEquals(cardNumber1, card1.getCardNumber());
		assertEquals(cardExpiryDate1, card1.getCardExpirationDate());
		assertEquals(cv2_1, card1.getCv2());
		
	}

	@Test
	public void testGetCardNumber() {
		assertNotSame(cardNumber1, card1.getCardNumber());
		assertEquals(cardNumber2, card2.getCardNumber());
	}

	@Test
	public void testSetCardNumber() {
		card1.setCardNumber(cardNumber1);
		assertEquals(cardNumber1, card1.getCardNumber());
		String cardNumber2_new = "4023223123412312";
		card2.setCardNumber(cardNumber2_new);
		assertEquals(cardNumber2_new, card2.getCardNumber());
		
	}

	@Test
	public void testGetCardExpirationDate() {
		assertEquals("", card1.getCardExpirationDate());
		assertEquals(cardExpiryDate2, card2.getCardExpirationDate());		
	}

	@Test
	public void testSetCardExpirationDate() {
		card1.setCardExpirationDate(cardExpiryDate1);
		assertEquals(cardExpiryDate1, card1.getCardExpirationDate());
		String cardExpiryDate2_new = "16 DEC 15";
		card2.setCardExpirationDate(cardExpiryDate2_new);
		assertEquals(cardExpiryDate2_new, card2.getCardExpirationDate());
		
	} 

	@Test
	public void testGetCv2() {
		assertEquals(cv2_2, card2.getCv2());
	}

	@Test
	public void testSetCv2() {
		String cv2_2_new = "112";
		card2.setCv2(cv2_2_new);
		assertEquals(cv2_2_new, card2.getCv2());
		
	}

}
