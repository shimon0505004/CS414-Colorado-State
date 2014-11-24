package cs414.a5.nlighth1;

import java.io.Serializable;

public class Card implements Serializable {
	private String cardNumber;
	private String cardExpirationDate;
	private String cv2;
	
	public Card(){
		setCardNumber("");
		setCardExpirationDate("");
		setCv2("");
	}
	
 	public Card(String cardNumber,String cardExpirationDate, String cv2){
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
		if(cardNumber!=null){
			this.cardNumber = cardNumber;			
		}
		else{
			this.cardNumber = "";			
		}
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
		if(cardExpirationDate!=null){
			this.cardExpirationDate = cardExpirationDate;
		}
		else{
			this.cardExpirationDate = "";
		}
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
		if(cv2!= null){
			this.cv2 = cv2;
		}
		else{
			this.cv2 = "";			
		}
	}
}
