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
        setOfMenus = new HashSet<>();
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

    public boolean initDefineMenu(Employee e) {
        //if(e.isManager())
            return true;
        // else return false;
    }

    public boolean defineMenu(Employee e, String name, String desc) {
        //if(e.isManager())
        return setOfMenus.add(new Menu(name, desc)); // true if menu.name not already taken
        // else return false;
    }

    private Set<Menu> getSetOfMenus() { return this.setOfMenus; }
    public Set<Menu> authorizeEditMenus(Employee e) { //initDeleteMenu
        //if(e.isManager())
            return getSetOfMenus();
        // else return null;
    }

    public Set<Item> editMenu(Employee e, Menu menu) {
        //if(e.isManager())
            return menu.getMenuItems();
        // else return null;
    }

    public void setSpecial(Employee e, Item i, double percentOff) {
        //if(e.isManager())
            i.setSpecialPercentageOffPrice(percentOff);
    }

    public void removeMenuItems(Employee e, Menu menu, Set<Item> items) {
        //if(e.isManager())
            for(Item i : items)
                menu.deleteItem(i);
    }
}
