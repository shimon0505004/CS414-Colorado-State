package cs414.pos;

/**
 * Interface for a factory for creating a Customer.
 *
 * @author Nathan Lighthart
 */
public interface ICustomerFactory {
	/**
	 * Creates a Customer.
	 *
	 * @param firstName the first name of the Customer
	 * @param lastName the last name of the Customer
	 * @param membershipNumber the membershipNumber
	 * @return The created instance of the customer
	 */
	public Customer createCustomer(String firstName, String lastName, int membershipNumber);
}
