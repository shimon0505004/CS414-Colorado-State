/**
 * 
 */
package cs414.pos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author SHAIKHSHAWON
 *
 */
public class Store {

	/**
	 * 
	 */
	private String storeName;
	private String phoneNumber;
	private Address address;
	private Set<Employee> employeeSet;
	private Set<LoginInfo> loginSet;
	
	private Set<Kiosk> setOfKiosk;
	private Set<Register> setOfRegister;
	
	private Set<Order> setOfPlacedOrder;
	private Set<Customer> members;
	
	private Set<Menu> setOfMenus;
	private Set<Item> setOfItems;
	
	
	
	
	public Store() {
		// TODO Auto-generated constructor stub
		this.setStoreName("");
		this.setPhoneNumber("000-000-0000");
		address = new Address();
		initializeSetElements();
	}
	
	private void initializeSetElements() {
		// TODO Auto-generated method stub
		employeeSet = new HashSet<Employee>();
		loginSet = new HashSet<LoginInfo>();
	}

	public Store(String name, String phone , String location) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber(phone);
		address = new Address(location,AddressType.Business);
		initializeSetElements();
	}
	
	public Store(String name, String location) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber("000-000-0000");
		address = new Address(location,AddressType.Business);
		initializeSetElements();
	}
	
	public Store(String name) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber("000-000-0000");
		address = new Address();
		initializeSetElements();
	}
	
	/**
	 * @return the storeName
	 */
	public Set<Employee> getEmployeeSet() {
		return employeeSet;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address.setLocation(address);
	}
	
	public String addEmployee(String name, String loginID, String password){
		Employee newEmployee = new Employee(name);
		String employeeID = newEmployee.getEmployeeID();
		
		LoginInfo newLoginInfo = new LoginInfo(loginID, password);
		
		newEmployee.setEmployeeLoginInfo(newLoginInfo);
		loginSet.add(newLoginInfo);
		employeeSet.add(newEmployee);		
		return employeeID;
	}
	
	public boolean loginAttempt(String loginID, String password){
		Iterator<Employee> iterEmployee= employeeSet.iterator();
		boolean returnVal = false;
		while(iterEmployee.hasNext() && !returnVal)
		{
			Employee testEmployee = iterEmployee.next();
			returnVal = testEmployee.matchLoginInfo(loginID,password);
		}
		
		return returnVal;
	} 

}
