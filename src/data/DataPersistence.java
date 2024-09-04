package data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataPersistence {

    private static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    private static <T> T fromJsonToList(String json, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeOfT);
    }

    public static void save(BankDataWrapper bankDataWrapper) {
        try (FileWriter fileWriter = new FileWriter("database.json")) {
            fileWriter.write(toJson(bankDataWrapper));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save data: " + e.getMessage(), e);
        }
    }

    public static BankDataWrapper load() {
        File file = new File("database.json");
        if (!file.exists()) {
            return new BankDataWrapper(new ArrayList<>(), new ArrayList<>());
        }

        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                json.append(line);
            }
            Type listType = new TypeToken<BankDataWrapper>() {
            }.getType();
            return fromJsonToList(json.toString(), listType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data: " + e.getMessage(), e);
        }
    }
}
