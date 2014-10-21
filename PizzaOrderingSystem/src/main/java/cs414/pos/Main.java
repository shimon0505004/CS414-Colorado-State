package cs414.pos;

import java.io.IOException;

/**
 *
 * @author Nathan Lighthart
 */
public class Main {
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
        try {
            Employee e = new Employee();
            Store s = new Store();

            Menu m = s.defineMenu(e, "menu0", "menu0_desc");
            System.out.println(m.getMenuName());
            s.saveState("testSave.ser");

            Store s2 = Store.openState("testSave.ser");
            System.out.println(s2.getSetOfMenus().size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
