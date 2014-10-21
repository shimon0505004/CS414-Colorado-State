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

	private IEmployeeFactory employeeFactory = EmployeeFactory.getInstance();

	/**
	 *
	 */
	public Store() {
		// TODO Auto-generated constructor stub
		this.setStoreName("");
		this.setPhoneNumber("000-000-0000");
		address = new Address();
		initializeSetElements();
	}

	/**
	 *
	 */
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

	/**
	 *
	 * @param name
	 * @param phone
	 * @param location
	 */
	public Store(String name, String phone, String location) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber(phone);
		address = new Address(location, AddressType.Business);
		initializeSetElements();
	}

	/**
	 *
	 * @param name
	 * @param location
	 */
	public Store(String name, String location) {
		// TODO Auto-generated constructor stub
		this.setStoreName(name);
		this.setPhoneNumber("000-000-0000");
		address = new Address(location, AddressType.Business);
		initializeSetElements();
	}

	/**
	 *
	 * @param name
	 */
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

	/**
	 *
	 * @param name
	 * @param loginID
	 * @param password
	 * @return
	 */
	public String addEmployee(String name, String loginID, String password) {
		Employee newEmployee = employeeFactory.createCashier(name);
		String employeeID = newEmployee.getEmployeeID();

		LoginInfo newLoginInfo = new LoginInfo(loginID, password);

		newEmployee.setEmployeeLoginInfo(newLoginInfo);
		loginSet.add(newLoginInfo);
		employeeSet.add(newEmployee);
		return employeeID;
	}

	/**
	 *
	 * @param loginID
	 * @param password
	 * @return
	 */
	public boolean loginAttempt(String loginID, String password) {
		Iterator<Employee> iterEmployee = employeeSet.iterator();
		boolean returnVal = false;
		while(iterEmployee.hasNext() && !returnVal) {
			Employee testEmployee = iterEmployee.next();
			returnVal = testEmployee.matchLoginInfo(loginID, password);
		}

		return returnVal;
	}

    /**
	 * 
	 * @param e
	 * @return
	 */
	
    public boolean initDefineMenu(Employee e) {
        //if(e.isManager())
            return true;
        // else return false;
    }

    /**
     * 
     * @param e
     * @param name
     * @param desc
     * @return
     */
    
    public Menu defineMenu(Employee e, String name, String desc) {
        //if(e.isManager())
        Menu m = new Menu(name, desc);
        setOfMenus.add(m);
        return m;
        // else return false;
    }

    /**
     * 
     * @return
     */
    public Set<Menu> getSetOfMenus() { return this.setOfMenus; }
    
    /**
     * 
     * @param e
     * @return
     */
    public Set<Menu> authorizeEditMenus(Employee e) { //initDeleteMenu
        //if(e.isManager())
            return getSetOfMenus();
        // else return null;
    }
    
    /**
     * 
     * @param e
     * @param menu
     * @return
     */
    public Set<Item> editMenu(Employee e, Menu menu) {
        //if(e.isManager())
            return menu.getMenuItems();
        // else return null;
    }

    /**
     * 
     * @param e
     * @param i
     * @param percentOff
     */
    public void setSpecial(Employee e, Item i, double percentOff) {
        //if(e.isManager())
            i.setSpecialPercentageOffPrice(percentOff);
    }

    /**
     * 
     * @param e
     * @param menu
     * @param items
     */
    public void removeMenuItems(Employee e, Menu menu, Set<Item> items) {
        //if(e.isManager())
            for(Item i : items)
                menu.deleteItem(i);
    }

    /**
     * 
     * @param e
     * @param menu
     * @param name
     * @param price
     * @param desc
     */
    public void addMenuItem(Employee e, Menu menu, String name, double price, String desc) {
        //if(e.isManager())
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
}
