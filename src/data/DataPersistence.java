package data;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataPersistence {

    public static void save(DataWrapper dataWrapper) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.ser"))) {
            oos.writeObject(dataWrapper);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save data: " + e.getMessage(), e);
        }
    }

    public static DataWrapper load() {
        File file = new File("database.ser");
        if (!file.exists()) {
            return new DataWrapper(new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (DataWrapper) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load data: " + e.getMessage(), e);
        }
    }
}
