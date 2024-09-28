import com.google.gson.Gson;

import java.io.*;

public class JSONFileHandler {
    private static final Gson gson = new Gson();

    public static Data[] getData() {
        try (Reader reader = new FileReader("data.json")) {
            return gson.fromJson(reader, Data[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Data[0];
    }

    public static void writeData(Data data) {
        try (Writer writer = new FileWriter("data.json")) {
            String json = gson.toJson(data);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData(Data[] data) {
        try (Writer writer = new FileWriter("data.json")) {
            String json = gson.toJson(data);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
