import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;

public class JSONFileHandler {
    private static final Gson gson = new Gson();
    private static final String FILE_PATH = "data.json";

    public static Data[] getData() {
        try (Reader reader = new FileReader("data.json")) {
            return gson.fromJson(reader, Data[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Data[0];
    }

    public static void writeData(Data data) {
        try {
            Data[] existingData = getData();
            Data[] newData = Arrays.copyOf(existingData, existingData.length + 1);
            newData[newData.length - 1] = data;

            try (Writer writer = new FileWriter(FILE_PATH)) {
                gson.toJson(newData, writer);
            }

            System.out.println("Данные успешно добавлены.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData(Data[] data) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
