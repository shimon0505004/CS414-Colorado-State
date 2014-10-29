package cs414.pos;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaverLoaderTest {

    Store savedStore, loadedStore;

    Employee e0, e1, e2;
    String testName1, testName2, testName3;
    String testLoginID1, testLoginID2, testLoginID3;
    String testPassWord1, testPassWord2, testPassWord3;

    String menuName1, menuName2, menuDesc1, menuDesc2;
    Menu m0, m1;

    Kiosk testKiosk1, testKiosk2;
    Register testRegister1, testRegister2;

    Order testOrder1, testOrder2;
    int testOrderID1, testOrderID2;

    Customer c;
    String cFName, cLName, cNum;

    @Before
    public void setUp() throws Exception {

        savedStore = new Store("PizzaStore", "206-953-5584", "Stuart St.");

        testName1 = "Shimon";
        testLoginID1 = "skshimon";
        testPassWord1 = "uda";

        testName2 = "Caleb";
        testLoginID2 = "ctebbe";
        testPassWord2 = "cte";

        testName3 = "Nathan";
        testLoginID3 = "nlightHart";
        testPassWord3 = "nli";

        e0 = savedStore.addEmployee(testName1, testLoginID1, testPassWord1, Role.Cashier);
        e1 = savedStore.addEmployee(testName2, testLoginID2, testPassWord2, Role.Chef);
        e2 = savedStore.addEmployee(testName3, testLoginID3, testPassWord3, Role.Manager);

        menuName1 = "Menu1";
        menuName2 = "Menu2";
        menuDesc1 = "MenuDesc1";
        menuDesc2 = "MenuDesc2";
        m0 = savedStore.defineMenu(e2, menuName1, menuDesc1);
        savedStore.addMenuItem(e2,m0,"i1", 10, "desc");
        m1 = savedStore.defineMenu(e2,menuName2,menuDesc2);
        savedStore.addMenuItem(e2,m1,"i2", 10, "desc");

        testKiosk1 = savedStore.addKiosk(e2, 1);
        testKiosk2 = savedStore.addKiosk(e2, 2);
        testRegister1 = savedStore.addRegister(e2, 1);
        testRegister2 = savedStore.addRegister(e2, 2);

        testOrderID1 = 1;
        testOrderID2 = 2;
        testOrder1 = savedStore.createOrderViaKiosk(1);
        testOrder2 = savedStore.createOrderViaRegister(e0, 1);
        savedStore.placeOrder(testOrder1);
        savedStore.placeOrder(testOrder2);

        cFName = "caleb";
        cLName = "tebbe";
        cNum = "1231231234";
        c = savedStore.addNewMember(cFName, cLName, cNum);

        // save the store instance and read it back for test cases
        File f = new File("testSaverLoader.ser");
        SaverLoader.save(f, savedStore);
        loadedStore = SaverLoader.load(f);
    }

    @Test public void employeeTest() {
        assertTrue(loadedStore.getEmployeeSet().size() == 3);
        for(Employee e : loadedStore.getEmployeeSet()) {
            if(e.getRole() == Role.Manager) assertEquals(e.getEmployeeName(),e2.getEmployeeName());
        }
    }

    @Test public void kioskTest() {
        assertTrue(loadedStore.getSetOfKiosk().size() == 2);
    }

    @Test public void registerTest() {
        assertTrue(loadedStore.getSetOfRegister().size() == 2);
    }

    @Test public void customerTest() {
        assertEquals(loadedStore.getMembers().size(), 1);
        assertEquals(loadedStore.getMembers().toArray(new Customer[]{})[0].getFirstName(), cFName);
    }

    @Test public void orderTest() {
        assertEquals(loadedStore.getListOfPlacedOrder().size(), 2);
    }

    @Test public void menuTest() {
        assertEquals(loadedStore.getSetOfMenus().size(), 2);
        assertEquals(loadedStore.getSetOfMenus().toArray(new Menu[]{})[0].getMenuItems().size(), 1);
    }
}
