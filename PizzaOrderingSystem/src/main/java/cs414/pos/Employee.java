/**
 *
 */
package cs414.pos;

import java.util.UUID;

/**
 * @author SHAIKHSHAWON
 *
 */
public class Employee {

	/**
	 *
	 */
	private String employeeID;
	private String employeeName;
	private LoginInfo employeeLoginInfo;
	private Role performsRole;
	private Store worksForStore;
	
	public Employee() {
		// TODO Auto-generated constructor stub

	}

	public Employee(String name, Store Employee) {
		// TODO Auto-generated constructor stub
		setEmployeeName(name);
		setEmployeeID(UUID.randomUUID().toString());
		setEmployeeLoginInfo(null);
		setPerformsRole(null);
		setWorksForStore(Employee);
	}

	public Employee(String name) {
		// TODO Auto-generated constructor stub
		setEmployeeName(name);
		setEmployeeID(UUID.randomUUID().toString());
		setEmployeeLoginInfo(null);
		setPerformsRole(null);
		setWorksForStore(null);
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
		this.employeeID = employeeID;
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

	public void setLoginInfo(LoginInfo newLoginInfo) {
		// TODO Auto-generated method stub

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
		loginInfo.setLoginEmployee(this);
	}

	public boolean matchLoginInfo(String loginID, String password) {
		// TODO Auto-generated method stub
		//return this.getEmployeeLoginInfo().getLoginId().equals(loginID) && 		this.getEmployeeLoginInfo().getPassword().equals(password) ;
		if(this.getEmployeeLoginInfo().getLoginId().equals(loginID)) {
			return this.getEmployeeLoginInfo().matchPassword(password);
		} else {
			return false;
		}
	}

	/**
	 * @return the performsRole
	 */
	public Role getPerformsRole() {
		return performsRole;
	}

	/**
	 * @param performsRole the performsRole to set
	 */
	public void setPerformsRole(Role performsRole) {
		this.performsRole = performsRole;
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
