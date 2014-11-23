package cs414.a5.eid.ui;

import com.google.gson.reflect.TypeToken;

import cs414.a5.eid.Customer;
import cs414.a5.eid.Employee;
import cs414.a5.eid.Item;
import cs414.a5.eid.LoginInfo;
import cs414.a5.eid.Menu;
import cs414.a5.eid.Order;
import cs414.a5.eid.OrderItem;
import cs414.a5.eid.OrderType;
import cs414.a5.eid.Role;
import cs414.a5.eid.SaverLoader;
import cs414.a5.eid.Store;
import cs414.a5.eid.server.POS_Server;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

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
    private CompleteDeliverOrderUI completeDeliverOrderView;
    private EmployeeUI employeeView;
    private CustomerInfoUI customerInfoView;

    private Store store;
    private Employee currentEmployee;
    private boolean isKiosk;
    // Id is the id of the register or kiosk
    private int id;
    private Order currentOrder;

    private final static String baseUrl = "http://localhost:8000/";

    public UIController(Store store, boolean isKiosk, int registerOrKioskId) {
        this.store = store;
        this.isKiosk = isKiosk;
        this.id = registerOrKioskId;
    }

    public void start() {
        loginView = new LoginUI(this);
        mainView = new MainUI(this);
        editMenuItemView = new EditMenuItemUI(this);
        editMenuView = new EditMenuUI(this);
        placeOrderView = new PlaceOrderUI(this);
        completeDeliverOrderView = new CompleteDeliverOrderUI(this);
        employeeView = new EmployeeUI(this);
        customerInfoView = new CustomerInfoUI(this);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                loginView.init();
                mainView.init();
                editMenuItemView.init();
                editMenuView.init();
                placeOrderView.init();
                completeDeliverOrderView.init();
                employeeView.init();
                customerInfoView.init();

                if(isKiosk) {
                    setPermissions(true);
                    mainView.setVisible(true);
                } else {
                    loginView.setVisible(true);
                }
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
            loginView.setStatus("");
            loginView.setVisible(false);
            setPermissions(false);
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
        if(isKiosk) {
            currentOrder = store.createOrderViaKiosk(id);
        } else {
            currentOrder = store.createOrderViaRegister(currentEmployee, id);
        }
        placeOrderView.updateMenus();
        placeOrderView.updateOrder();
        placeOrderView.setVisible(true);
    }

    public void displayCompleteOrder() {
        mainView.setVisible(false);
        completeDeliverOrderView.setIsDeliveryOption(false);
        completeDeliverOrderView.updateOrders();
        completeDeliverOrderView.setVisible(true);
    }

    public void displayDeliverOrder() {
        mainView.setVisible(false);
        completeDeliverOrderView.setIsDeliveryOption(true);
        completeDeliverOrderView.updateOrders();
        completeDeliverOrderView.setVisible(true);
    }

    public void displayEmployee() {
        mainView.setVisible(false);
        employeeView.updateEmployees();
        employeeView.setVisible(true);
    }

    public void displayCustomers() {
        mainView.setVisible(false);
        customerInfoView.updateInformation();
        customerInfoView.setVisible(true);
    }

    public boolean loginIDValid(String loginID) {
        Collection<Employee> employees = store.getEmployeeSet();
        Collection<String> loginInfos = new ArrayList<>();

        for(Employee employee : employees) {
            loginInfos.add(employee.getEmployeeLoginInfo().getLoginId());
        }

        return !loginInfos.contains(loginID);
    }

    public boolean createEmployee(String name, String loginID, String password,
            String role) {

        if(loginIDValid(loginID)) {
            Role r;
            switch(role) {
                case "Manager":
                    r = Role.Manager;
                    break;
                case "Chef":
                    r = Role.Chef;
                    break;
                case "DeliveryMan":
                    r = Role.DeliveryMan;
                    break;
                default:
                    r = Role.Cashier;
                    break;
            }
            Employee e = store.addEmployee(name, loginID, password, r);
            return true;
        }
        return false;
    }

    public boolean editEmployee(String loginID, String newName,
            String newLoginID, String newPassword, String newRole) {
        if(loginIDValid(newLoginID)) {
            Employee e = this.getSelectedEmployee(loginID);
            e.setEmployeeName(newName);
            e.setEmployeeLoginInfo(new LoginInfo(newLoginID, newPassword));
            Role r;
            switch(newRole) {
                case "Manager":
                    r = Role.Manager;
                    break;
                case "Chef":
                    r = Role.Chef;
                    break;
                case "DeliveryMan":
                    r = Role.DeliveryMan;
                    break;
                default:
                    r = Role.Cashier;
                    break;
            }
            e.setRole(r);
            return true;
        } else {
            return false;
        }
    }

    public boolean createMenu(String name, String description) {
        if(!isValidMenuName(name)) {
            return false;
        }
        Menu m = store.defineMenu(currentEmployee, name, description);
        return m != null;
    }

    public boolean editMenu(String menuName, String newName, String newDesc) {
        if(!isValidMenuName(newName)) {
            return false;
        }
        Menu m = getSelectedMenu(menuName);
        m.setMenuName(newName);
        m.setMenuDescription(newDesc);
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

    public boolean createMenuItem(String itemName, String itemDescription,
            double price) {
        if(!isValidMenuItemName(itemName)) {
            return false;
        }
        store.addMenuItem(currentEmployee, itemName, price, itemDescription);
        return true;
    }

    public boolean changeItemPrice(String itemName, double price) {
        if(price >= 0) {
            Item item = getSelectedItem(itemName);
            item.setItemPrice(price);
            return true;
        }
        return false;
    }

    public int getRequiredPointForFreePizzaCertificate() {
        return store.getRequiredPointsForFreePizzaCertificate();
    }

    public int setRequiredPointForFreePizzaCertificate(int point) {
        store.setRequiredPointsForFreePizzaCertificate(point);

        return getRequiredPointForFreePizzaCertificate();
    }

    public boolean deleteMenuItem(String itemName) {
        Item item = getSelectedItem(itemName);
        return store.deleteMenuItem(currentEmployee, item);
    }

    public boolean changeMenuItemName(String itemName, String newName) {
        if(!isValidMenuItemName(newName)) {
            return false;
        }
        Item item = getSelectedItem(itemName);
        item.setItemName(newName);
        return true;
    }

    public void changeMenuItemDescription(String itemName,
            String itemDescription) {
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

    public boolean completeOrder(int orderId) {
        boolean returnVal = false;
        Collection<Order> orders = getIncompleteOrdersSet();
        for(Order order : orders) {
            if(order.getOrderID() == orderId) {
                returnVal = order.setCompletedByEmployee(currentEmployee);
            }
        }
        return returnVal;
    }

    public boolean deliverOrder(int id) {
        boolean returnVal = false;
        Collection<Order> orders = getCompletedButUnderliveredOrdersSet();
        for(Order order : orders) {
            if(order.getOrderID() == id) {
                returnVal = order.setDeliveredByEmployee(currentEmployee);
            }
        }
        return returnVal;
    }

    // deliveryType {0 = InHouse, 1 = TakeAway, 2 = Delivery}
    // paymentType {0 = cash, 1 = card}
    public boolean payOrder(String membershipID, int deliveryType,
            String address, int paymentType, String cardNumber,
            String expirationDate, String cv2, double amount) {

        Customer c = null;
        if(membershipID != null) {
            if(isKiosk) {
                JSONObject object = new JSONObject();
                object.put("LoginID", membershipID);
                Reader r = postToServer(object, "login");
                c = POS_Server.gson.fromJson(r, Customer.class);
            } else {
                c = store.getMember(membershipID);
            }
            if(c == null) {
                return false;
            }
            //currentOrder.updateMembershipHoldingCustomer(c);
        }

        if(deliveryType == 0) {
            currentOrder.updateToInHouseOrder();
        } else if(deliveryType == 1) {
            currentOrder.updateToTakeAwayOrder();
        } else if(deliveryType == 2) {
            currentOrder.updateToHomeDeliveryOrder(address);
        }

        boolean success = false;
        if(paymentType == 0) {
            success = currentOrder.makeCashPayment(amount);
        } else if(paymentType == 1) {
            success = currentOrder.makeCardPayment(amount, cardNumber,
                    expirationDate, cv2);
        }
        if(success && c != null) {
            /**
             * if the payment is successful, only then the current order should
             * be updated. Otherwise record can be problematic.
             */
            currentOrder.updateMembershipHoldingCustomer(c, store);
        }

        return success;
    }

    public int getMemberPoints(String membershipID) {
        if(membershipID != null) {
            Customer c;
            if(isKiosk) {
                JSONObject object = new JSONObject();
                object.put("LoginID", membershipID);
                Reader r = postToServer(object, "login");
                c = POS_Server.gson.fromJson(r, Customer.class);
            } else {
                c = store.getMember(membershipID);
            }
            if(c == null) {
                return 0;
            } else {
                return c.getRewardsPoint();
            }
        } else {
            return 0;
        }
    }

    public int setMemberPoints(String membershipID, int points) {
        if(membershipID != null) {
            Customer c = store.getMember(membershipID);
            if(c == null) {
                return 0;
            } else {
                c.setRewardsPoint(points);
                return c.getRewardsPoint();
            }
        } else {
            return 0;
        }
    }

    public void placeOrder() {
        if(isKiosk) {
            JSONObject obj = new JSONObject(POS_Server.gson.toJson(currentOrder));
            postToServer(obj, "placeOrder");
        } else {
            store.placeOrder(currentOrder);
        }
    }

    public void addOrderItem(String itemName, int quantity) {
        Item item = getSelectedItem(itemName);
        if(item != null) {
            currentOrder.addItemToOrderByAmount(item, quantity);
        }
    }

    public void removeOrderItem(String itemName, int quantity) {
        Item item = getSelectedItem(itemName);
        if(item != null) {
            currentOrder.removeMultipleCountOfItemFromOrder(item, quantity);
        }
    }

    public int getCurrentCountOfOrderItem(String itemName) {
        Item item = getSelectedItem(itemName);
        if(item != null) {
            return currentOrder.getOrderItem(item).getQuantity();
        } else {
            return 0;
        }
    }

    public double getOrderChange() {
        if(isKiosk) {
            return 0.0;
        }
        return roundToTwo(currentOrder.getAmountReturned());
    }

    public double getTotal() {
        return roundToTwo(currentOrder.getTotalPrice());
    }

    public void logout() {
        mainView.setVisible(false);
        currentEmployee = null;
        loginView.setVisible(true);
    }

    public void closeProgram() {
        if(!isKiosk) {
            saveToFile();
        }
        System.exit(0); // close jvm
    }

    private void saveToFile() {
        try {
            SaverLoader.save(SaverLoader.SAVE_FILE, store);
            /**
             * Saving files to JSON also
             */
            SaverLoader.saveTestGson(store);

        } catch(IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void closeEditMenu() {
        editMenuView.setVisible(false);
        mainView.setVisible(true);
    }

    public void closeEditMenuItem() {
        editMenuItemView.setVisible(false);
        mainView.setVisible(true);
    }

    public void closeCompleteDeliverOrder() {
        completeDeliverOrderView.setVisible(false);
        mainView.setVisible(true);
    }

    public void closePlaceOrder() {
        placeOrderView.setVisible(false);
        currentOrder = null;
        mainView.setVisible(true);
    }

    public void closeEmployee() {
        employeeView.setVisible(false);
        mainView.setVisible(true);
    }

    public void closeCustomerInformation() {
        customerInfoView.setVisible(false);
        mainView.setVisible(true);
    }

    public Iterable<String> getIncompleteOrders() {

        Collection<Order> orders = getIncompleteOrdersSet();
        List<String> incompleteOrders = new ArrayList<>();
        for(Order order : orders) {
            incompleteOrders.add(getOrderString(order));
        }
        return incompleteOrders;
    }

    public Iterable<String> getCompletedButUnderliveredOrders() {
        Collection<Order> orders = getCompletedButUnderliveredOrdersSet();
        List<String> undeliveredOrders = new ArrayList<>();
        for(Order order : orders) {
            undeliveredOrders.add(getOrderString(order));
        }
        return undeliveredOrders;

    }

    public Iterable<String> getMenus() {
        Collection<Menu> menus;
        List<String> menuList = new ArrayList<>();

        if(isKiosk) {
            Reader menuReader = postToServer(new JSONObject(), "getMenus");
            Type collectionType = new TypeToken<Collection<Menu>>() {
            }.getType();
            menus = POS_Server.gson.fromJson(menuReader, collectionType);

        } else {
            menus = store.getSetOfMenus();
        }

        for(Menu menu : menus) {
            menuList.add(menu.getMenuName());
        }
        return menuList;
    }

    public String getMenuDesc(String menuName) {
        Menu m = getSelectedMenu(menuName);
        return m.getMenuDescription();
    }

    public Iterable<String> getAllMenuItems() {
        return getMenuItems(store.getSetOfItems());
    }

    public Iterable<String> getAllFullMenuItems() {
        return getFullMenuItems(store.getSetOfItems());
    }

    public Iterable<String> getMenuItemsNotOnMenu(String menuName) {
        Menu menu = getSelectedMenu(menuName);
        Collection<Item> items = getItemsNotOnMenu(menu);
        return getMenuItems(items);
    }

    public Iterable<String> getFullMenuItemsNotOnMenu(String menuName) {
        Menu menu = getSelectedMenu(menuName);
        Collection<Item> items = getItemsNotOnMenu(menu);
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

    public String getEmployeeLoginID(String employeeString) {
        String[] split = employeeString.split(" : ");
        return split[2];
    }

    public Iterable<String> getEmployees() {

        return getEmployees(store.getEmployeeSet());
    }

    private String getEmployeeSring(Employee employee) {
        String role;
        Role r = employee.getRole();
        switch(r) {
            case Manager:
                role = "Manager";
                break;
            case Chef:
                role = "Chef";
                break;
            case DeliveryMan:
                role = "DeliveryMan";
                break;
            default:
                role = "Cashier";
        }
        String s = employee.getEmployeeName() + " : "
                + employee.getEmployeeID() + " : "
                + employee.getEmployeeLoginInfo().getLoginId() + " : " + role;
        return s;
    }

    public Iterable<String> getFullMenuItems(String menuName) {
        Menu menu = getSelectedMenu(menuName);

        if(menu != null) {
            return getFullMenuItems(menu.getMenuItems());
        } else {
            return new ArrayList<>();
        }
    }

    public Iterable<String> getOrderItems() {
        return getOrderItems(currentOrder);
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

    public String getOrderItemName(String orderItemString) {
        return orderItemString.substring(0, orderItemString.lastIndexOf("x"))
                .trim();
    }

    public Object[] getCustomerColumnNames() {
        Object[] columnNames = new Object[]{"ID", "Name", "Phone Number", "Points"};
        return columnNames;
    }

    public Object[][] getCustomerData() {
        Collection<Customer> customers = store.getMembers();
        Object[][] data = new Object[customers.size()][4];
        int curr = 0;
        for(Customer c : customers) {
            /*
             * Customer ID is not object ID, it is the membership ID
             * data[curr][0] = c.objectID;
             */
            data[curr][0] = c.getMemberShipNumber();
            data[curr][1] = c.getFirstName() + " " + c.getLastName();
            data[curr][2] = c.getCustomerPhoneNumber();
            data[curr][3] = c.getRewardsPoint();
            ++curr;
        }
        return data;
    }

    private BufferedReader postToServer(JSONObject object, String function) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(baseUrl + function);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(new StringEntity(object.toString(), "utf-8"));

        BufferedReader br = null;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            if(response.getEntity().getContent() != null && response.getStatusLine().getStatusCode() == 200) {
                br = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    public String createAccount(String firstName, String lastName, String phoneNumber) {
        Customer c = null;
        if(isKiosk) {
            JSONObject object = new JSONObject();
            object.put("firstName", firstName);
            object.put("lastName", lastName);
            object.put("customerPhoneNumber", phoneNumber);

            BufferedReader br = postToServer(object, "customerAccounts");
            c = POS_Server.gson.fromJson(br, Customer.class);
        } else {
            c = store.addNewMember(firstName, lastName, phoneNumber);
        }
        return c.getMemberShipNumber();
        //return c.objectID;
    }

    public Iterable<String> getItemsForOrder(int orderId) {
        Collection<Order> orders = store.getListOfPlacedOrder();
        for(Order order : orders) {
            if(order.getOrderID() == orderId) {
                return getOrderItems(order);
            }
        }
        return new ArrayList<>();
    }

    private Employee getSelectedEmployee(String employeeLoginID) {
        Collection<Employee> employees = store.getEmployeeSet();
        for(Employee employee : employees) {
            if(Objects.equals(employee.getEmployeeLoginInfo().getLoginId(),
                    employeeLoginID)) {
                return employee;
            }
        }
        return null;
    }

    private Menu getSelectedMenu(String menuName) {
        Collection<Menu> menus = store.getSetOfMenus();
        for(Menu menu : menus) {
            if(Objects.equals(menu.getMenuName(), menuName)) {
                return menu;
            }
        }
        return null;
    }

    private Item getSelectedItem(String itemName) {
        Collection<Item> items = store.getSetOfItems();
        for(Item item : items) {
            if(Objects.equals(item.getItemName(), itemName)) {
                return item;
            }
        }
        return null;
    }

    private Iterable<String> getMenuItems(Collection<Item> items) {
        List<String> itemList = new ArrayList<>();
        for(Item item : items) {
            itemList.add(item.getItemName());
        }
        return itemList;
    }

    private Iterable<String> getFullMenuItems(Collection<Item> items) {
        List<String> itemList = new ArrayList<>();
        for(Item item : items) {
            itemList.add(getItemString(item));
        }
        return itemList;
    }

    public Iterable<String> getEmployees(Collection<Employee> employees) {
        List<String> employeeList = new ArrayList<>();
        for(Employee employee : employees) {
            employeeList.add(getEmployeeSring(employee));
        }
        return employeeList;
    }

    private String getItemString(Item item) {
        String s = item.getItemName() + " : " + item.getItemDescription()
                + " $" + roundToTwo(item.getCurrentPrice());
        if(item.isSpecial()) {
            s += " [Special]";
        }
        return s;
    }

    private String getOrderString(Order order) {
        return "Order #" + order.getOrderID();
    }

    private String getOrderItemString(OrderItem item) {
        return item.getItem().getItemName() + " x" + item.getQuantity() + " $"
                + roundToTwo(item.computeSubtotal());
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

    private Collection<Item> getItemsNotOnMenu(Menu menu) {
        Collection<Item> items = new LinkedHashSet<>(store.getSetOfItems());
        items.removeAll(menu.getMenuItems());
        return items;
    }

    private Collection<Order> getIncompleteOrdersSet() {
        Collection<Order> allOrders = store.getListOfPlacedOrder();
        Collection<Order> incompleteOrders = new LinkedHashSet<>();
        for(Order order : allOrders) {
            if(order.isComplete()) {
                continue;
            }
            incompleteOrders.add(order);
        }
        return incompleteOrders;
    }

    private Collection<Order> getCompletedButUnderliveredOrdersSet() {
        Collection<Order> allOrders = store.getListOfPlacedOrder();
        Collection<Order> undeliveredOrders = new LinkedHashSet<>();
        for(Order order : allOrders) {
            if(order.isComplete() && !order.isDelivered() && order.getTypeOfOrder().equals(OrderType.HomeDelivery)) {
                undeliveredOrders.add(order);
            }
        }
        return undeliveredOrders;
    }

    private void setPermissions(boolean isKiosk) {
        if(isKiosk) {
            mainView.setCanPlaceOrder(true);
            mainView.setCanEditMenu(false);
            mainView.setCanCompleteOrder(false);
            mainView.setCanManageEmployee(false);
            mainView.setCanViewCustomers(false);
            mainView.setCanDeliverOrder(false);
            mainView.setCanLogout(false);
        } else {
            mainView.setCanEditMenu(currentEmployee.getRole().canEditMenu());
            mainView.setCanEditEmployee(currentEmployee.getRole().canEditMenu());
            mainView.setCanPlaceOrder(currentEmployee.getRole().canUseRegister());
            mainView.setCanCompleteOrder(currentEmployee.getRole()
                    .canCompleteOrder());
            mainView.setCanManageEmployee(currentEmployee.getRole().canManageEmployees());
            mainView.setCanViewCustomers(currentEmployee.getRole().canViewCustomers());
            mainView.setCanDeliverOrder(currentEmployee.getRole().canCompleteDelivery());
            mainView.setCanLogout(true);
        }
    }

    private Collection<String> getOrderItems(Order o) {
        List<String> items = new ArrayList<>();
        for(OrderItem item : o.getSetOfItems()) {
            items.add(getOrderItemString(item));
        }
        return items;
    }

}
