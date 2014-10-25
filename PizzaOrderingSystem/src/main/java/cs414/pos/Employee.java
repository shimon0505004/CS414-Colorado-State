/**
 *
 */
package cs414.pos;

import java.io.Serializable;

/**
 * Class that represents an Employee of the store.
 *
 * @author SHAIKHSHAWON
 */
public class Employee implements Serializable {
	private String employeeID;
	private String employeeName;
	private LoginInfo employeeLoginInfo;
	private Privilege privilege;
	private Store worksForStore;

	private static int employeeCounter=1;
	public final int objectID ;
	
	public Employee(String name, Privilege privilege) {
		setEmployeeName(name);
		setPrivilege(privilege);
		
		this.objectID = employeeCounter++;
		
		setEmployeeID(generateEmployeeID());
		setEmployeeLoginInfo(null);
		setWorksForStore(null);
	}

	private String generateEmployeeID(){
		return "Employee"+this.objectID;
		
	}
	/**
	 * @return the employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(String employeeID) {
		if(employeeID!=null){
			this.employeeID = employeeID;			
		}
		else{
			; //do nothing
		}
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the employeeInfo
	 */
	public LoginInfo getEmployeeLoginInfo() {
		return employeeLoginInfo;
	}

	/**
	 * @param loginInfo the loginInfo to set
	 */
	public void setEmployeeLoginInfo(LoginInfo loginInfo) {
		this.employeeLoginInfo = loginInfo;
		if(loginInfo!=null){
			employeeLoginInfo.setLoginEmployee(this);			
		}

	}

    /**
     *
     * @param loginID
     * @param password
     * @return
     */
	public boolean matchLoginInfo(String loginID, String password) {
		// TODO Auto-generated method stub
		//return this.getEmployeeLoginInfo().getLoginId().equals(loginID) && 		this.getEmployeeLoginInfo().getPassword().equals(password) ;
		try {
			if(this.getEmployeeLoginInfo().getLoginId().equals(loginID)) {
				return this.getEmployeeLoginInfo().matchPassword(password);
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}
		
	}

	/**
	 * Returns the privilege of the employee.
	 *
	 * @return the privileges that this employee has
	 */
	public Privilege getPrivilege() {
		return privilege;
	}

	/**
	 * Sets the privilege of the employee.
	 *
	 * @param privilege the new privilege of the employee
	 */
	public void setPrivilege(Privilege privilege) {
		if(privilege == null) {
			privilege = Privilege.Cashier;
		}
		this.privilege = privilege;
	}

	/**
	 * @return the worksForStore
	 */
	public Store getWorksForStore() {
		return worksForStore;
	}

	/**
	 * @param worksForStore the worksForStore to set
	 */
	public void setWorksForStore(Store worksForStore) {
		this.worksForStore = worksForStore;
	}

}
