package cs414.a5.nlighth1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 *
 * @author Nathan Lighthart
 */
public class SaverLoader {

	public static final File SAVE_FILE = new File("Save.ser");
    public static final File TEST_FILE = new File("Save.json");

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(IEmployeeFactory.class, new InterfaceAdapter<IEmployeeFactory>())
            .registerTypeAdapter(ICustomerFactory.class, new InterfaceAdapter<ICustomerFactory>())
            .setPrettyPrinting()
            .create();

	public static void save(File saveFile, Store s) throws IOException {
		serialize(new FileOutputStream(saveFile), s);
	}

    public static void saveTestGson(Store s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(TEST_FILE));
        gson.toJson(s,bw);
        bw.close();
    }

    public static Store openTestGson() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(TEST_FILE));
        return gson.fromJson(br, Store.class);
    }

	public static Store load(File file) throws IOException, ClassNotFoundException {
		return deserialize(new FileInputStream(file));
	}

	private static void serialize(OutputStream os, Store s) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os));
		oos.writeObject(s);
		oos.close();
	}

	private static Store deserialize(InputStream is) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is));
		Store s = (Store) ois.readObject();
		ois.close();
		return s;
	}
}
