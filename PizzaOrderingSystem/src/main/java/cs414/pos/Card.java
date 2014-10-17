package cs414.pos;

public class Card {
	private String cardNumber;
	private String cardExpirationDate;
	private String cv2;
	
	Card(){
		setCardNumber("");
		setCardExpirationDate("");
		setCv2("");
	}
	
	Card(String cardNumber,String cardExpirationDate, String cv2){
		setCardNumber(cardNumber);
		setCardExpirationDate(cardExpirationDate);
		setCv2(cv2);
	}

	public void updateCardInfo(String cardNumber,String cardExpirationDate, String cv2){
		setCardNumber(cardNumber);
		setCardExpirationDate(cardExpirationDate);
		setCv2(cv2);		
	}
	
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardExpirationDate
	 */
	public String getCardExpirationDate() {
		return cardExpirationDate;
	}

	/**
	 * @param cardExpirationDate the cardExpirationDate to set
	 */
	public void setCardExpirationDate(String cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}

	/**
	 * @return the cv2
	 */
	public String getCv2() {
		return cv2;
	}

	/**
	 * @param cv2 the cv2 to set
	 */
	public void setCv2(String cv2) {
		this.cv2 = cv2;
	}
}
