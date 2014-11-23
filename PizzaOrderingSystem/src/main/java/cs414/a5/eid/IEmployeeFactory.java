package cs414.a5.eid;

/**
 * Interface for a factory that creates employees.
 *
 * @author Nathan Lighthart
 */
public interface IEmployeeFactory {
	/**
	 * Creates an employee based on the privilege string.
	 *
	 * @param name the name of the employee
	 * @param privilege the privilege
	 * @return the created employee
	 * @see Role#valueOf(java.lang.String)
	 */
	public Employee createEmployee(String name, String privilege);

    /**
     *
     * @param name
     * @param role
     * @return
     */
    public Employee createEmployee(String name, Role role);

	/**
	 * Creates an employee that is a Manager.
	 *
	 * @param name the name of the employee
	 * @return the created employee
	 */
	public Employee createManager(String name);

	/**
	 * Creates an employee that is a Cashier.
	 *
	 * @param name the name of the employee
	 * @return the created employee
	 */
	public Employee createCashier(String name);

	/**
	 * Creates an employee that is a Chef.
	 *
	 * @param name the name of the employee
	 * @return the created employee
	 */
	public Employee createChef(String name);
}
