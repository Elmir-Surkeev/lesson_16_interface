public interface Connectable {
    void openConnect() throws Exception;
    void closeConnect() throws Exception;
    boolean isConnect(){
        return connected;
    };
    Object getIndex(int index)throws Exception;
    boolean containsRecordByKey(String key)throws Exception;
    Object getRecordByKey(String key)throws Exception;
    Object[] getRecords(int startIndex, int endIndex)throws Exception;
    int getRecordCount()throws Exception;
    void addRecord(Object record)throws Exception;
    void updateRecordByIndex(int index, Object newRecord)throws Exception;
    void updateRecordByKey(String key, Object newRecord)throws Exception;
}
