package cs414.pos;

import java.io.*;
import java.util.Iterator;

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
            String f = "testSave.ser";
            Store s = new Store();

            Employee manager = s.addEmployee("bob", "bob", "pw_bob", Privilege.Manager);
            Employee chef = s.addEmployee("billy", "billy", "pw_billy", Privilege.Chef);
            Employee cashier = s.addEmployee("billy-bob", "billy_bob", "pw_billy_bob", Privilege.Cashier);

            Menu m = s.defineMenu(manager, "menu0", "menu0_desc");
            System.out.println(m.getMenuName());
            serialize(new FileOutputStream(f), s);

            Store s2 = deserialize(new FileInputStream(f));
            Iterator<Menu> i = s2.getSetOfMenus().iterator();
            while(i.hasNext())
                System.out.println(i.next().equals(m));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void serialize(OutputStream os, Store s) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os));
        oos.writeObject(s);
        oos.close();
    }

    public static Store deserialize(InputStream is) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is));
        Store s = (Store) ois.readObject();
        ois.close();
        return s;
    }
}
