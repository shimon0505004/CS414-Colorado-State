package cs414.pos.ui;

import cs414.pos.Employee;
import cs414.pos.Item;
import cs414.pos.Menu;
import cs414.pos.Order;
import cs414.pos.SaverLoader;
import cs414.pos.Store;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan Lighthart
 */
public class UIController {
	private LoginUI loginView;
	private MainUI mainView;
	private EditMenuItemUI editMenuItemView;
	private EditMenuUI editMenuView;
	private PlaceOrderUI placeOrderView;
	private CompleteOrderUI completeOrderView;

	private Store store;
	private Employee currentEmployee;

	public UIController(Store store) {
		this.store = store;
	}

	public void start() {
		loginView = new LoginUI(this);
		mainView = new MainUI(this);
		editMenuItemView = new EditMenuItemUI(this);
		editMenuView = new EditMenuUI(this);
		placeOrderView = new PlaceOrderUI(this);
		completeOrderView = new CompleteOrderUI(this);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				loginView.init();
				mainView.init();
				editMenuItemView.init();
				editMenuView.init();
				placeOrderView.init();
				completeOrderView.init();

				loginView.setVisible(true);
			}
		});
	}

	public void login(String loginID, String password) {
		Employee temp = store.loginAttempt(loginID, password);
		if(temp == null) {
			// Login Failed
			loginView.setStatus("Invalid Username/Password");
		} else {
			currentEmployee = temp;

			loginView.clear();
			loginView.setVisible(false);

			mainView.setCanEditMenu(currentEmployee.getRole().canEditMenu());
			mainView.setCanPlaceOrder(currentEmployee.getRole().canUseKiosk());
			mainView.setCanCompleteOrder(currentEmployee.getRole().canCompleteOrder());
			mainView.setVisible(true);
		}
	}

	public void displayEditMenu() {
		mainView.setVisible(false);
		editMenuView.updateMenus();
		editMenuView.setVisible(true);
	}

	public void displayEditMenuItem() {
		mainView.setVisible(false);
		editMenuItemView.updateItems();
		editMenuItemView.setVisible(true);
	}

	public void displayPlaceOrder() {
		mainView.setVisible(false);
		placeOrderView.updateMenus();
		placeOrderView.setVisible(true);
	}

	public void displayCompleteOrder() {
		mainView.setVisible(false);
		completeOrderView.updateOrders();
		completeOrderView.setVisible(true);
	}

	public boolean createMenu(String name, String description) {
		if(!isValidMenuName(name)) {
			return false;
		}
		Menu m = store.defineMenu(currentEmployee, name, description);
		return m != null;
	}

	public void deleteMenu(String name) {
		Menu menu = getSelectedMenu(name);
		store.getSetOfMenus().remove(menu);
	}

	public boolean addMenuItem(String menuName, String menuItemName) {
		Menu menu = getSelectedMenu(menuName);
		Item item = getSelectedItem(menuItemName);
		menu.addItem(item);
		return true;
	}

	public boolean removeMenuItem(String menuName, String menuItemName) {
		Menu menu = getSelectedMenu(menuName);
		Item item = getSelectedItem(menuItemName);
		menu.deleteItem(item);
		return true;
	}

	public boolean createMenuItem(String itemName, String itemDescription, double price) {
		if(!isValidMenuItemName(itemName)) {
			return false;
		}
		store.addMenuItem(currentEmployee, itemName, price, itemDescription);
		return true;
	}

	public void deleteMenuItem(String itemName) {
		Item item = getSelectedItem(itemName);
		store.deleteMenuItem(currentEmployee, item);
	}

	public boolean changeMenuItemName(String itemName, String newName) {
		if(!isValidMenuItemName(newName)) {
			return false;
		}
		Item item = getSelectedItem(itemName);
		item.setItemName(newName);
		return true;
	}

	public void changeMenuItemDescription(String itemName, String itemDescription) {
		Item item = getSelectedItem(itemName);
		item.setItemDescription(itemDescription);
	}

	public void changeMenuItemSpecial(String itemName) {
		Item item = getSelectedItem(itemName);
		if(item.isSpecial()) {
			item.removeSpecial();
		} else {
			item.setSpecial();
		}
	}

	public void completeOrder(int id) {
		Set<Order> orders = getIncompleteOrdersSet();
		for(Order order : orders) {
			if(order.getOrderID() == id) {
				order.setCompletedByEmployee(currentEmployee);
			}
		}
	}

	public void closeMain() {
		try {
			SaverLoader.save(SaverLoader.SAVE_FILE, store);
		} catch(IOException ex) {
			Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.exit(0); // close jvm
	}

	public void closeEditMenu() {
		editMenuView.setVisible(false);
		mainView.setVisible(true);
	}

	public void closeEditMenuItem() {
		editMenuItemView.setVisible(false);
		mainView.setVisible(true);
	}

	public void closeCompleteOrder() {
		completeOrderView.setVisible(false);
		mainView.setVisible(true);
	}

	public void closePlaceOrder() {
		placeOrderView.setVisible(false);
		mainView.setVisible(true);
	}

	public Iterable<String> getIncompleteOrders() {
		Set<Order> orders = getIncompleteOrdersSet();
		List<String> incompleteOrders = new ArrayList<>();
		for(Order order : orders) {
			incompleteOrders.add(getOrderString(order));
		}
		return incompleteOrders;
	}

	public Iterable<String> getMenus() {
		Set<Menu> menus = store.getSetOfMenus();
		List<String> menuList = new ArrayList<>();
		for(Menu menu : menus) {
			menuList.add(menu.getMenuName());
		}
		return menuList;
	}

	public Iterable<String> getAllMenuItems() {
		return getMenuItems(store.getSetOfItems());
	}

	public Iterable<String> getAllFullMenuItems() {
		return getFullMenuItems(store.getSetOfItems());
	}

	public Iterable<String> getMenuItemsNotOnMenu(String menuName) {
		Menu menu = getSelectedMenu(menuName);
		Set<Item> items = getItemsNotOnMenu(menu);
		return getMenuItems(items);
	}

	public Iterable<String> getFullMenuItemsNotOnMenu(String menuName) {
		Menu menu = getSelectedMenu(menuName);
		Set<Item> items = getItemsNotOnMenu(menu);
		return getFullMenuItems(items);
	}

	public Iterable<String> getMenuItems(String menuName) {
		Menu menu = getSelectedMenu(menuName);

		if(menu != null) {
			return getFullMenuItems(menu.getMenuItems());
		} else {
			return new ArrayList<>();
		}
	}

	public Iterable<String> getFullMenuItems(String menuName) {
		Menu menu = getSelectedMenu(menuName);

		if(menu != null) {
			return getFullMenuItems(menu.getMenuItems());
		} else {
			return new ArrayList<>();
		}
	}

	public String getItemName(String itemString) {
		String[] split = itemString.split(" : ");
		return split[0];
	}

	public int getOrderID(String orderString) {
		String id = orderString.substring("Order #".length());
		try {
			return Integer.parseInt(id);
		} catch(NumberFormatException ex) {
			return -1;
		}
	}

	private Menu getSelectedMenu(String menuName) {
		Set<Menu> menus = store.getSetOfMenus();
		for(Menu menu : menus) {
			if(Objects.equals(menu.getMenuName(), menuName)) {
				return menu;
			}
		}
		return null;
	}

	private Item getSelectedItem(String itemName) {
		Set<Item> items = store.getSetOfItems();
		for(Item item : items) {
			if(Objects.equals(item.getItemName(), itemName)) {
				return item;
			}
		}
		return null;
	}

	private Iterable<String> getMenuItems(Set<Item> items) {
		List<String> itemList = new ArrayList<>();
		for(Item item : items) {
			itemList.add(item.getItemName());
		}
		return itemList;
	}

	private Iterable<String> getFullMenuItems(Set<Item> items) {
		List<String> itemList = new ArrayList<>();
		for(Item item : items) {
			itemList.add(getItemString(item));
		}
		return itemList;
	}

	private String getItemString(Item item) {
		String s = item.getItemName() + " : " + item.getItemDescription() + " $" + roundToTwo(item.getCurrentPrice());
		if(item.isSpecial()) {
			s += " [Special]";
		}
		return s;
	}

	private String getOrderString(Order order) {
		return "Order #" + order.getOrderID();
	}

	private double roundToTwo(double d) {
		return Math.round(d * 100.0) / 100.0;
	}

	private boolean isValidMenuName(String name) {
		if(name == null || name.trim().isEmpty()) {
			return false;
		}
		Iterable<String> menuNames = getMenus();
		for(String menuName : menuNames) {
			if(Objects.equals(name, menuName)) {
				return false;
			}
		}
		return true;
	}

	private boolean isValidMenuItemName(String name) {
		if(name == null || name.trim().isEmpty() || name.contains(":")) {
			return false;
		}
		Iterable<String> itemNames = getAllMenuItems();
		for(String itemName : itemNames) {
			if(Objects.equals(name, itemName)) {
				return false;
			}
		}
		return true;
	}

	private Set<Item> getItemsNotOnMenu(Menu menu) {
		Set<Item> items = new LinkedHashSet<>(store.getSetOfItems());
		items.removeAll(menu.getMenuItems());
		return items;
	}

	private Set<Order> getIncompleteOrdersSet() {
		Set<Order> allOrders = store.getSetOfPlacedOrder();
		Set<Order> incompleteOrders = new LinkedHashSet<>();
		for(Order order : allOrders) {
			if(order.isComplete()) {
				continue;
			}
			incompleteOrders.add(order);
		}
		return incompleteOrders;
	}
}
