package cs414.pos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Nathan Lighthart
 */
public class SaverLoader {
	public static final File SAVE_FILE = new File("Save.ser");

	public static void save(File saveFile, Store s) throws IOException {
		serialize(new FileOutputStream(saveFile), s);
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
