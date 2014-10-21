package cs414.pos;

/**
 * Implementation of the ICustomerFactory.
 *
 * @author Nathan Lighthart
 */
public class CustomerFactory implements ICustomerFactory {
	/**
	 * Singleton instance
	 */
	private static CustomerFactory instance;

	/**
	 * Returns the singleton instance of the CustomerFactory.
	 *
	 * @return the CustomerFactory instance
	 */
	public static synchronized CustomerFactory getInstance() {
		if(instance == null) {
			instance = new CustomerFactory();
		}
		return instance;
	}

	/**
	 * Private constructor as for the specifications of the singleton pattern
	 */
	private CustomerFactory() {
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, int membershipNumber) {
		return new Customer(firstName, lastName, membershipNumber);
	}
}
