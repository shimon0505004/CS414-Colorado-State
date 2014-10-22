package cs414.pos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
            Employee e = new Employee("bob", Privilege.Manager);
            Store s = new Store();
            String f = "testSave.ser";

            Menu m = s.defineMenu(e, "menu0", "menu0_desc");
            System.out.println(m.getMenuName());
            s.saveState(new FileOutputStream(f));

            Store s2 = Store.openState(new FileInputStream(f));
            System.out.println(s2.getSetOfMenus().size());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
