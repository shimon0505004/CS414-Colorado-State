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
	
	public Employee() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Employee(String name) {
		// TODO Auto-generated constructor stub
		setEmployeeName(name);
		setEmployeeID(UUID.randomUUID().toString());
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
	 * @param employeeInfo the employeeInfo to set
	 */
	public void setEmployeeLoginInfo(LoginInfo loginInfo) {
		this.employeeLoginInfo = loginInfo;
		loginInfo.setLoginEmployee(this);
	}

	public boolean matchLoginInfo(String loginID, String password) {
		// TODO Auto-generated method stub
		//return this.getEmployeeLoginInfo().getLoginId().equals(loginID) && 		this.getEmployeeLoginInfo().getPassword().equals(password) ;
		if(this.getEmployeeLoginInfo().getLoginId().equals(loginID))
		{
			return this.getEmployeeLoginInfo().matchPassword(password);
		}
		else return false;
	}
	
	
}
