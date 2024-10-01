import java.util.Arrays;

public class DataServiceImpl implements DataService {
    private boolean isDatabaseOpen;

    @Override
    public void openDatabaseConnection() {
        this.isDatabaseOpen = true;
        System.out.println("Соединение с базой данных открыто");
    }

    @Override
    public void closeDatabaseConnection() {
        this.isDatabaseOpen = false;
        System.out.println("Соединение с базой данных закрыто");
    }

    @Override
    public boolean isOpenDatabaseConnection() {
        return this.isDatabaseOpen;
    }

    @Override
    public Data getDataByIndex(int index) throws Exception {
        if (isOpenDatabaseConnection()) {
            Data[] data = JSONFileHandler.getData();
            if (index < 0 || index > data.length - 1) {
                throw new Exception(String.format("Индекс %d не существует в базе данных", index));
            }
            for (int i = 0; i < data.length; i++) {
                if (i == index) {
                    return data[index];
                }
            }
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

    @Override
    public boolean isDataByKey(String key) {
        return false;
    }

    @Override
    public Data getDataByKey(String key) throws Exception {
        if (isOpenDatabaseConnection()) {
            Data[] data = JSONFileHandler.getData();
            for (Data dataFromDatabase : data) {
                if (dataFromDatabase.getKey().equals(key)) {
                    System.out.println(dataFromDatabase);
                }
            }
            throw new Exception("Записи с ключом %s не существует в базе данных".formatted(key));
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

    @Override
    public Data[] getDataByIndexRange(int startIndex, int endIndex) throws Exception {
        if (isOpenDatabaseConnection()) {
            Data[] data = JSONFileHandler.getData();
            if (startIndex < 0 || endIndex < 0 || startIndex > endIndex || endIndex > data.length - 1) {
                throw new IndexOutOfBoundsException("Некорректные индексы диапазона");
            }
            System.out.println(Arrays.toString(Arrays.copyOfRange(data, startIndex, endIndex + 1)));
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

    @Override
    public int countDatabaseSize() throws Exception {
        if (isOpenDatabaseConnection()) {
            return JSONFileHandler.getData().length;
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

    @Override
    public void addDataToDatabase(Data data) throws Exception {
        if (data.getKey() == null || data.getValue() == null) {
            throw new Exception("Ключ и значение не должны быть пустыми");
        }
        if (isOpenDatabaseConnection()) {
            JSONFileHandler.writeData(data);
            System.out.println("Данные успешно записаны");
            return;
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

    @Override
    public void updateDataByIndex(int index, Data data) throws Exception {
        if (isOpenDatabaseConnection()) {
            Data[] dataFromDatabase = JSONFileHandler.getData();
            if (index < 0 || index > dataFromDatabase.length - 1) {
                throw new IndexOutOfBoundsException(String.format("Индекса с %d не существует в базе данных", index));
            }
            for (int i = 0; i < dataFromDatabase.length; i++) {
                if (i == index) {
                    dataFromDatabase[i].setKey(data.getKey());
                    dataFromDatabase[i].setValue(data.getValue());
                    JSONFileHandler.writeData(dataFromDatabase);
                    return;
                }
            }
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

    @Override
    public void updateDataByKey(String key, Data data) throws Exception {
        if (isOpenDatabaseConnection()) {
            Data[] dataFromDatabase = JSONFileHandler.getData();
            for (Data d : dataFromDatabase) {
                if (d.getKey().equals(key)) {
                    d.setKey(data.getKey());
                    d.setValue(data.getValue());
                    JSONFileHandler.writeData(dataFromDatabase);
                    return;
                }
            }
            throw new Exception("Записи с ключом %s не существует в базе данных".formatted(key));
        }
        throw new Exception("Соединение с базой данных закрыто");
    }

}
