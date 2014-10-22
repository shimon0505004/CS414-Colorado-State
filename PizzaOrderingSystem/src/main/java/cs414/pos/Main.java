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
            Employee e = new Employee("bob", Privilege.Manager);
            Store s = new Store();
            String f = "testSave.ser";

            Menu m = s.defineMenu(e, "menu0", "menu0_desc");
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
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(s);
        oos.close();
    }

    public static Store deserialize(InputStream is) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(is);
        Store s = (Store) ois.readObject();
        ois.close();
        return s;
    }
}
