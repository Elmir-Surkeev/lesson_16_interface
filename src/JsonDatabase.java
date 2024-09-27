import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonDatabase {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Record[] records;
    private boolean connected;
    private String filePath;

    static class Record {
        String key;
        String value;

        public Record(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public JsonDatabase(String filePath) {
        this.filePath = filePath;
        this.records = new Record[0];
        this.connected = false;
    }

    public void openConnection() throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            records = gson.fromJson(reader, Record[].class);
            if (records == null) {
                records = new Record[0];
            }
            connected = true;
            System.out.println("Соединение открыто.");
        } catch (JsonSyntaxException e) {
            throw new IOException("Ошибка синтаксиса в JSON-файле.", e);
        }
    }

    public void closeConnection() throws IOException {
        if (connected) {
            try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(records, writer);
            }
            connected = false;
            System.out.println("Соединение закрыто.");
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public String getRecordByIndex(int index) throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        if (index >= 0 && index < records.length) {
            return records[index].value;
        } else {
            throw new Exception("Запись с данным индексом не найдена.");
        }
    }

    public boolean containsRecordByKey(String key) throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        for (Record record : records) {
            if (record.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public String getRecordByKey(String key) throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        for (Record record : records) {
            if (record.key.equals(key)) {
                return record.value;
            }
        }
        throw new Exception("Запись с данным ключом не найдена.");
    }

    public void addRecord(String key, String value) throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        if (containsRecordByKey(key)) {
            throw new Exception("Запись с таким ключом уже существует.");
        }

        Record[] newRecords = new Record[records.length + 1];
        System.arraycopy(records, 0, newRecords, 0, records.length);
        newRecords[records.length] = new Record(key, value);
        records = newRecords;

    }

    public void updateRecordByIndex(int index, String newKey, String newValue) throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        if (index >= 0 && index < records.length) {
            records[index].key = newKey;
            records[index].value = newValue;
        } else {
            throw new Exception("Запись с данным индексом не найдена.");
        }
    }

    public void updateRecordByKey(String key, String newKey, String newValue) throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        for (Record record : records) {
            if (record.key.equals(key)) {
                record.key = newKey;
                record.value = newValue;
                return;
            }
        }
        throw new Exception("Запись с данным ключом не найдена.");
    }

    public int getRecordCount() throws Exception {
        if (!connected) {
            throw new Exception("Соединение закрыто.");
        }
        return records.length;
    }
}
