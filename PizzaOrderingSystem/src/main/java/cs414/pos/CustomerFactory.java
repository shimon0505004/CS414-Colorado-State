package cs414.pos;

import java.io.Serializable;

/**
 * Implementation of the ICustomerFactory.
 *
 * @author Nathan Lighthart
 */
public class CustomerFactory implements ICustomerFactory, Serializable {
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
	public Customer createCustomer(String firstName, String lastName) {
		return new Customer(firstName, lastName);
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String phoneNumber) {
		return new Customer(firstName, lastName, phoneNumber);
	}

	@Override
	public Customer createCustomer(String firstName, String lastName,
			String phoneNumber, Store s) {
		// TODO Auto-generated method stub
		return new Customer(firstName, lastName, phoneNumber, s);
	}

}
