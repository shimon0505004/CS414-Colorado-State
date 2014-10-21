package cs414.pos;

public class Customer {

	private String firstName;
	private String lastName;
	private String customerPhoneNumber;
	private int memberShipNumber;
	private int rewardsPoint;

	public Customer(String firstName, String lastName, int memberShipNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setMemberShipNumber(memberShipNumber);
		setCustomerPhoneNumber("000-000-0000");
		setRewardsPoint(0);
	}

	public Customer(String firstName, String lastName, int memberShipNumber, String phoneNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setMemberShipNumber(memberShipNumber);
		setCustomerPhoneNumber(phoneNumber);
		setRewardsPoint(0);
	}

	public void addToRewardsPoint(int point) {
		setRewardsPoint(getRewardsPoint() + point);
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the rewardsPoint
	 */
	public int getRewardsPoint() {
		return rewardsPoint;
	}

	/**
	 * @param rewardsPoint the rewardsPoint to set
	 */
	public void setRewardsPoint(int rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}

	/**
	 * @return the memberShipNumber
	 */
	public int getMemberShipNumber() {
		return memberShipNumber;
	}

	/**
	 * @param memberShipNumber the memberShipNumber to set
	 */
	public void setMemberShipNumber(int memberShipNumber) {
		this.memberShipNumber = memberShipNumber;
	}

	/**
	 * @return the customerPhoneNumber
	 */
	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	/**
	 * @param customerPhoneNumber the customerPhoneNumber to set
	 */
	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

}
