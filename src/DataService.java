public interface DataService {
    void openDatabaseConnection();

    void closeDatabaseConnection();

    boolean isOpenDatabaseConnection();

    Data getDataByIndex(int index) throws Exception;

    boolean isDataByKey(String key);

    Data getDataByKey(String key) throws Exception;

    Data[] getDataByIndexRange(int startIndex, int endIndex) throws Exception;

    int countDatabaseSize() throws Exception;

    void addDataToDatabase(Data data) throws Exception;

    void updateDataByIndex(int index, Data data) throws Exception;

    void updateDataByKey(String key, Data data) throws Exception;
    //default void toString(String key, String value) throws Exception;
}
