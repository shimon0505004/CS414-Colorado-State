/**
 *
 */
package cs414.pos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author SHAIKHSHAWON
 *
 */
public class Store implements Serializable {

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

	private ArrayList<Order> listOfPlacedOrder;
	private Set<Customer> members;

	private Set<Menu> setOfMenus;
	private Set<Item> setOfItems;
	private int requiredPointsForFreePizzaCertificate;
	
	private IEmployeeFactory employeeFactory = EmployeeFactory.getInstance();
    private ICustomerFactory customerFactory = CustomerFactory.getInstance();

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
		setListOfPlacedOrder(new ArrayList<Order>());
		setMembers(new HashSet<Customer>());
		
		setRequiredPointsForFreePizzaCertificate(150);
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
		if(storeName != null) {
			this.storeName = storeName;
		} else {
			this.storeName = "";
		}
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
		if(phoneNumber != null) {
			this.phoneNumber = phoneNumber;
		} else {
			this.phoneNumber = "";
		}
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
	public Employee addEmployee(String name, String loginID, String password, Role role) {
		Employee newEmployee = employeeFactory.createEmployee(name, role);
		LoginInfo newLoginInfo = new LoginInfo(loginID, password);

		newEmployee.setEmployeeLoginInfo(newLoginInfo);
		loginSet.add(newLoginInfo);
		employeeSet.add(newEmployee);
		return newEmployee;
	}

	/**
	 *
	 * @param name
	 * @param loginID
	 * @param password
	 * @return
	 */
	public String addEmployee(String name, String loginID, String password, int option) {
		Employee newEmployee;
		if(option == Role.Chef.ordinal()) {
			newEmployee = employeeFactory.createChef(name);
		} else if(option == Role.Manager.ordinal()) {
			newEmployee = employeeFactory.createManager(name);
		} else {
			newEmployee = employeeFactory.createCashier(name);
		}

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
	 * @return the Employee that matches the loginID and password other returns
	 * null.
	 */
	public Employee loginAttempt(String loginID, String password) {
		Iterator<Employee> iterEmployee = employeeSet.iterator();
		while(iterEmployee.hasNext()) {
			Employee testEmployee = iterEmployee.next();
			if(testEmployee.matchLoginInfo(loginID, password)) {
				return testEmployee;
			}
		}
		return null;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public boolean initDefineMenu(Employee e) {
		if(e.getRole().canEditMenu()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @param e
	 * @param name
	 * @param desc
	 * @return
	 */
	public Menu defineMenu(Employee e, String name, String desc) {
		if(e.getRole().canEditMenu()) {
			Menu m;
			if(getMenu(name)!=null){
				m = getMenu(name);
				m.setMenuDescription(desc);
				return m;
			}
			else{
				m = new Menu(name, desc);
				setOfMenus.add(m);
				return m;	
			}
		} else {
			return null;
		}
	}

	/**
	 *
	 * @return
	 */
	public Set<Menu> getSetOfMenus() {
		return this.setOfMenus;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public Set<Menu> authorizeEditMenus(Employee e) { //initDeleteMenu
		if(e.getRole().canEditMenu()) {
			return getSetOfMenus();
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param e
	 * @param menu
	 * @return
	 */
	public Set<Item> editMenu(Employee e, Menu menu) {
		if(e.getRole().canEditMenu()) {
			return menu.getMenuItems();
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param e
	 * @param i
	 * @param percentOff
	 */
	public void setSpecial(Employee e, Item i, double percentOff) {
		if(e.getRole().canEditMenu()) {
			i.setSpecial(percentOff);
		}
	}

	/**
	 *
	 * @param e
	 * @param menu
	 * @param items
	 */
	public void removeMenuItems(Employee e, Menu menu, Set<Item> items) {
		if(e.getRole().canEditMenu()) {
			for(Item i : items) {
				menu.deleteItem(i);
				this.setOfItems.remove(i);
			}
		}
	}

	/**
	 *
	 * @param e
	 * @param menu
	 * @param name
	 * @param price
	 * @param desc
	 */
	public boolean addMenuItem(Employee e, Menu menu, String name, double price, String desc) {
		if(e.getRole().canEditMenu() && setOfMenus.contains(menu)) {
			Item newItem = new Item(name, price, desc);
			//menu.addItem(new Item(name, price, desc));
			menu.addItem(newItem);
			return this.setOfItems.add(newItem);
			
		}else{
			return false;
		}
		
	}

	public boolean addMenuItem(Employee e, String name, double price, String desc) {
		if(e.getRole().canEditMenu()) {
			Item newItem = new Item(name, price, desc);
			return this.setOfItems.add(newItem);
		}else{
			return false;
		}
		
	}

	public boolean deleteMenuItem(Employee e, Item item) {
		if(!e.getRole().canEditMenu()) {
			return false;
		}
		for(Menu m : setOfMenus) {
			m.deleteItem(item);
		}
		return setOfItems.remove(item);
	}

	/**
	 *
	 * @param e
	 * @param id
	 * @return
	 */
	public Kiosk addKiosk(Employee e, int id) {
		if(e.getRole().canAddKiosk()) {
			Kiosk k = new Kiosk(id);
			setOfKiosk.add(k);
			return k;
		} else {
			return null;
		}
	}

	public Register addRegister(Employee e, int id) {
		if(e.getRole().canAddRegister()) {
			Register r = new Register(id);
			setOfRegister.add(r);
			return r;
		} else {
			return null;
		}
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
     *
     * This method only creates an order for that store via that register
     * until method placeOrder() is not called, the created order is not stored
     * for the store
     * @param e
     * @param registerID
     * @return
     */
	public Order createOrderViaRegister(Employee e, int registerID) {
		Register register = getRegister(registerID);
		if(register!=null)
		{
			int orderID = getListOfPlacedOrder().size();
			Order createdOrder = register.createOrder(e, orderID);
			if(createdOrder!=null){
				//getSetOfPlacedOrder().add(createdOrder);
				return createdOrder;
			}
			else{
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * This method only creates an order for that store via that register
	 * until method placeOrder() is not called, the created order is not stored
	 * for the store
	 * @param kioskID
	 * @return
	 */
	public Order createOrderViaKiosk(int kioskID) {
		Kiosk kiosk = getKiosk(kioskID);
		if(kiosk!=null)
		{
			int orderID = getListOfPlacedOrder().size()+1;
			Order createdOrder = kiosk.createOrder(orderID);
			if(createdOrder!=null){
				//getSetOfPlacedOrder().add(createdOrder);
				return createdOrder;
			}
			else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * This method actually places the order. If this 
	 * method is not called, the order is not saved.
	 * @param orderToBeSaved
	 * @return
	 */
	public boolean placeOrder(Order orderToBeSaved){
		if(orderToBeSaved != null)
		{
			int size = getListOfPlacedOrder().size();
			if((orderToBeSaved.getIsKioskOrder()!=null && orderToBeSaved.getIsRegisterOrder()==null)
			|| (orderToBeSaved.getIsKioskOrder()==null && orderToBeSaved.getIsRegisterOrder()!=null))
			{
				orderToBeSaved.setOrderID(size);
				return getListOfPlacedOrder().add(orderToBeSaved);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	
	private Register getRegister(int registerID){
		Iterator<Register> iter= getSetOfRegister().iterator();
		while(iter.hasNext())
		{
			Register r = iter.next();
			if(r.getRegisterID() == registerID){
				return r;
			}
		}
		return null;
	}
	
	
	private Kiosk getKiosk(int kioskID){
		Iterator<Kiosk> iter= getSetOfKiosk().iterator();
		while(iter.hasNext())
		{
			Kiosk k = iter.next();
			if(k.getKioskID() == kioskID){
				return k;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param menuName
	 * @return
	 */
	public Menu getMenu(String menuName){
		if(menuName!= null){
			Iterator<Menu> menuIter= getSetOfMenus().iterator();
			while(menuIter.hasNext())
			{
				Menu m = menuIter.next();
				if(m.getMenuName() == menuName){
					return m;
				}
			}
			return null;
		}
		else{
			return null;
		}
	}
	
	/**
	 * 
	 * @param itemName
	 * @return
	 */
	public Item getItem(String itemName){
		if(itemName!= null){
			Iterator<Item> itemIter= getSetOfItems().iterator();
			while(itemIter.hasNext())
			{
				Item item = itemIter.next();
				if(item.getItemName() == itemName){
					return item;
				}
			}
			return null;
		}
		else{
			return null;
		}
	}
	
	
	
	/**
	 * @return the setOfPlacedOrder
	 */
	public ArrayList<Order> getListOfPlacedOrder() {
		return listOfPlacedOrder;
	}

	/**
	 * @param setOfPlacedOrder the setOfPlacedOrder to set
	 */
	//TODO: privilege check?  And when do we need this?
	public void setListOfPlacedOrder(ArrayList<Order> setOfPlacedOrder) {
		this.listOfPlacedOrder = setOfPlacedOrder;
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
	//TODO: privilege check? And when do we need this?
	public void setSetOfItems(Set<Item> setOfItems) {
		this.setOfItems = setOfItems;
	}

	public Employee getEmployee(String employeeID) {
		if(employeeID == null) {
			return null;
		} else {
			for(Employee e : getEmployeeSet()) {
				if(e.getEmployeeID().equals(employeeID)) {
					return e;
				}
			}
			return null;
		}

	}

	public Customer getMember(String customerID) {
		if(customerID == null) {
			return null;
		} else {
			for(Customer c : getMembers()) {
				if(c.getMemberShipNumber().equals(customerID)) {
					return c;
				}
			}
			return null;
		}
	}

	public Customer addNewMember(String firstName, String LastName, String customerPhoneNumber) {
		Customer newCustomer = customerFactory.createCustomer(firstName, LastName, customerPhoneNumber,this);
		getMembers().add(newCustomer);
		return newCustomer;
	}
	
	public boolean deleteMenu(Menu menu){
		if(menu!= null){
				
			Iterator<Item> iter = menu.getMenuItems().iterator();
			while(iter.hasNext()){
				Item testItem = iter.next();
				menu.deleteItem(testItem);
			}
			return getSetOfMenus().remove(menu);
		}else{
			return false;
		}
		
	}

	/**
	 * @return the requiredPointsForFreePizzaCertificate
	 */
	public int getRequiredPointsForFreePizzaCertificate() {
		return requiredPointsForFreePizzaCertificate;
	}

	/**
	 * @param requiredPointsForFreePizzaCertificate the requiredPointsForFreePizzaCertificate to set
	 */
	public void setRequiredPointsForFreePizzaCertificate(
			int requiredPointsForFreePizzaCertificate) {
		this.requiredPointsForFreePizzaCertificate = requiredPointsForFreePizzaCertificate;
	}
	
	public int getLargestObjectIDofCustomer(){
		int ID = -1;
		if(!getMembers().isEmpty()){
			for(Customer c: getMembers()){
				if(c.objectID>ID){
					ID = c.objectID;
				}
			}			
		}
		return ID;
	}
}
