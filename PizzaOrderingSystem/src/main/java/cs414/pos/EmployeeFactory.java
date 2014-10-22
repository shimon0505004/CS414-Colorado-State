package cs414.pos;

import java.io.Serializable;

/**
 * Implementation of the IEmployeeFactory.
 *
 * @author Nathan Lighthart
 */
public class EmployeeFactory implements IEmployeeFactory, Serializable {
	/**
	 * Singleton instance
	 */
	private static EmployeeFactory instance;

	/**
	 * Returns the singleton instance of the EmployeeFactory.
	 *
	 * @return the EmployeeFactory instance
	 */
	public static synchronized EmployeeFactory getInstance() {
		if(instance == null) {
			instance = new EmployeeFactory();
		}
		return instance;
	}

	/**
	 * Private constructor as for the specifications of the singleton pattern
	 */
	private EmployeeFactory() {
	}

	@Override
	public Employee createManager(String name) {
		return new Employee(name, Privilege.Manager);
	}

	@Override
	public Employee createCashier(String name) {
		return new Employee(name, Privilege.Cashier);
	}

	@Override
	public Employee createChef(String name) {
		return new Employee(name, Privilege.Chef);
	}

	@Override
	public Employee createEmployee(String name, String privilege) {
		return new Employee(name, Privilege.valueOf(privilege));
	}
}
