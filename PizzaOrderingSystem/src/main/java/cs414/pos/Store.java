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
	
	private Role[] roles; 
	
	
	public Store() {
		// TODO Auto-generated constructor stub
		this.setStoreName("");
		this.setPhoneNumber("000-000-0000");
		address = new Address();
		initializeSetElements();
		initializeRoles();
	}
	
	private void initializeRoles() {
		// Implement this in a better way.
		roles = new Role[3];
		
		roles[0] = new Manager();
		roles[1] = new Chef();
		roles[2] = new Cashier();
	}

	private void initializeSetElements() {
		// TODO Auto-generated method stub
		employeeSet = new HashSet<Employee>();
		loginSet = new HashSet<LoginInfo>();
        setOfMenus = new HashSet<Menu>();
        setSetOfItems(new HashSet<Item>());
        setSetOfKiosk(new HashSet<Kiosk>());
        setSetOfRegister(new HashSet<Register>());
        setSetOfPlacedOrder(new HashSet<Order>());
        setMembers(new HashSet<Customer>());
	}

	public Store(String name, String phone , String location) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber(phone);
		address = new Address(location,AddressType.Business);
		initializeSetElements();
		initializeRoles();
	}
	
	public Store(String name, String location) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber("000-000-0000");
		address = new Address(location,AddressType.Business);
		initializeSetElements();
		initializeRoles();
	}
	
	public Store(String name) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber("000-000-0000");
		address = new Address();
		initializeSetElements();
		initializeRoles();
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
		Employee newEmployee = new Employee(name,this);
		String employeeID = newEmployee.getEmployeeID();
		
		LoginInfo newLoginInfo = new LoginInfo(loginID, password);
		
		newEmployee.setEmployeeLoginInfo(newLoginInfo);
		loginSet.add(newLoginInfo);
		employeeSet.add(newEmployee);		
		return employeeID;
	}
	

	public void setEmployeeRole(String employeeID,int option){
		Iterator<Employee> iterEmployee= employeeSet.iterator();
		boolean returnVal = false;
		Employee testEmployee = new Employee();
		while(iterEmployee.hasNext() && !returnVal){
			testEmployee = iterEmployee.next();
			if(employeeID.equals(testEmployee.getEmployeeID())){
				returnVal = true;
				break;
			}
		}
		if(returnVal){
			// means employee found
			employeeSet.remove(testEmployee);
			switch (option) {
			case 0:
				testEmployee.setPerformsRole(roles[option]);
				employeeSet.add(testEmployee);
				break;
			case 1:
				testEmployee.setPerformsRole(roles[option]);
				employeeSet.add(testEmployee);
				break;
			case 2:
				testEmployee.setPerformsRole(roles[option]);
				employeeSet.add(testEmployee);				
				break;

			default:
				break;
			}
		}
	}

	/*	
	public void setEmployeeToManager(String employeeID){
		Iterator<Employee> iterEmployee= employeeSet.iterator();
		boolean returnVal = false;
		Employee testEmployee = new Employee();
		while(iterEmployee.hasNext() && !returnVal){
			testEmployee = iterEmployee.next();
			if(employeeID.equals(testEmployee.getEmployeeID())){
				returnVal = true;
				break;
			}
		}
		if(returnVal){
			// means employee found
			employeeSet.remove(testEmployee);
			testEmployee.setPerformsRole(roles[0]);
		}
	} 

	
	public void setEmployeeToChef(String employeeID){
		Iterator<Employee> iterEmployee= employeeSet.iterator();
		boolean returnVal = false;
		Employee testEmployee = new Employee();
		while(iterEmployee.hasNext() && !returnVal){
			testEmployee = iterEmployee.next();
			if(employeeID.equals(testEmployee.getEmployeeID())){
				returnVal = true;
				break;
			}
		}
		if(returnVal){
			// means employee found
			employeeSet.remove(testEmployee);
			testEmployee.setPerformsRole(roles[1]);
		}
	} 
	

	public void setEmployeeToCashier(String employeeID){
		Iterator<Employee> iterEmployee= employeeSet.iterator();
		boolean returnVal = false;
		Employee testEmployee = new Employee();
		while(iterEmployee.hasNext() && !returnVal){
			testEmployee = iterEmployee.next();
			if(employeeID.equals(testEmployee.getEmployeeID())){
				returnVal = true;
				break;
			}
		}
		if(returnVal){
			// means employee found
			employeeSet.remove(testEmployee);
			testEmployee.setPerformsRole(roles[2]);
		}
	} 
	
*/	
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

	/**
	 * @return the setOfKiosk
	 */
	public Set<Kiosk> getSetOfKiosk() {
		return setOfKiosk;
	}

	/**
	 * @param setOfKiosk the setOfKiosk to set
	 */
	public void setSetOfKiosk(Set<Kiosk> setOfKiosk) {
		this.setOfKiosk = setOfKiosk;
	}

	/**
	 * @return the setOfRegister
	 */
	public Set<Register> getSetOfRegister() {
		return setOfRegister;
	}

	/**
	 * @param setOfRegister the setOfRegister to set
	 */
	public void setSetOfRegister(Set<Register> setOfRegister) {
		this.setOfRegister = setOfRegister;
	}

	/**
	 * @return the setOfPlacedOrder
	 */
	public Set<Order> getSetOfPlacedOrder() {
		return setOfPlacedOrder;
	}

	/**
	 * @param setOfPlacedOrder the setOfPlacedOrder to set
	 */
	public void setSetOfPlacedOrder(Set<Order> setOfPlacedOrder) {
		this.setOfPlacedOrder = setOfPlacedOrder;
	}

	/**
	 * @return the members
	 */
	public Set<Customer> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(Set<Customer> members) {
		this.members = members;
	}

	/**
	 * @return the setOfItems
	 */
	public Set<Item> getSetOfItems() {
		return setOfItems;
	}

	/**
	 * @param setOfItems the setOfItems to set
	 */
	public void setSetOfItems(Set<Item> setOfItems) {
		this.setOfItems = setOfItems;
	}

	/**
	 * @return the roles
	 */
	public Role[] getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Role[] roles) {
		this.roles = roles;
	}
}
