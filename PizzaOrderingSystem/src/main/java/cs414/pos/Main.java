package cs414.pos;

import java.io.*;

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

            Employee manager = s.addEmployee("bob", "bob", "pw_bob", Role.Manager);
            Employee chef = s.addEmployee("billy", "billy", "pw_billy", Role.Chef);
            Employee cashier = s.addEmployee("billy-bob", "billy_bob", "pw_billy_bob", Role.Cashier);

            Kiosk k = s.addKiosk(manager, 1);
            Register r = s.addRegister(manager, 1);

            Menu m0 = s.defineMenu(manager, "menu0", "menu0_desc");
            s.addMenuItem(manager, m0, "pizza0", 5.0, "cheesy");
            s.addMenuItem(manager, m0, "pizza1", 5.0, "sausage");

            Menu m1 = s.defineMenu(manager, "menu1", "menu1_desc");
            s.addMenuItem(manager, m1, "soda", 5.0, "sprite");
            s.addMenuItem(manager, m1, "beer", 5.0, "new belgium");

            // save Store's state
            serialize(new FileOutputStream(f), s);

            // open Store's state
            Store s2 = deserialize(new FileInputStream(f));
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
