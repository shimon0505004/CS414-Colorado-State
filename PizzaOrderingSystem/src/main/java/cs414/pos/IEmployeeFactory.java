package cs414.pos;

/**
 * Interface for a factory that creates employees.
 *
 * @author Nathan Lighthart
 */
public interface IEmployeeFactory {
	/**
	 * Creates an employee that is a Manager.
	 *
	 * @param name the name of the employee.
	 * @return the created employee
	 */
	public Employee createManager(String name);

	/**
	 * Creates an employee that is a Cashier.
	 *
	 * @param name the name of the employee.
	 * @return the created employee
	 */
	public Employee createCashier(String name);

	/**
	 * Creates an employee that is a Chef.
	 *
	 * @param name the name of the employee.
	 * @return the created employee
	 */
	public Employee createChef(String name);
}
