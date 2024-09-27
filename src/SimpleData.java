import java.util.Arrays;

public class SimpleData implements Connectable{
    private Object[] recods;
    private boolean connected;
    public SimpleData(){
        this.recods = new Object[0];
        this.connected = false;
    }

    @Override
    public void  OpenConnect(){
        System.out.println("Соединение с базой данных открыто");
        this.connected = true;
    }

    @Override
    public void CloseConnect(){
        System.out.println("Соединение с базой данных закрыто");
        this.connected = false;
    }
    @Override
    public boolean CheckConnect(){
        return this.connected;
    }

    @Override
    public Object GetIndex(int index){
        if (index >=0 && index < recods.length){
            return recods[index];
        };
        return null;
    }
    @Override
    public boolean containsRecordByKey(String key){
        for(Object record: recods){
            if(record.toString().contains(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getRecordByKey(String key){
        for(Object record: recods){
            if(record.toString().contains(key)){
                return record;
            }
        }
        return false;
    }

    @Override
    public Object[] getRecords(int startIndex, int endIndex){
        if(startIndex<0 || endIndex>recods.length || startIndex>endIndex){
            return new Object[0];
        }
        return Arrays.copyOfRange(recods, startIndex, endIndex+1);
    }

    @Override
    public int getRecordCount(){
        return recods.length;
    }

    @Override
    public void addRecord(Object record){
        recods = Arrays.copyOf(recods, recods.length+1);
        recods[recods.length-1] = record;
    }

    @Override
    public void updateRecordByIndex(int index, Object newRecord){
        if(index >=0 && index < recods.length){
            recods[index] = newRecord;

        }
    }

    @Override
    public void updateRecordByKey(String key, Object newRecord) {
        for(int i = 0; i<recods.length; i++){
            if (recods[i].toString().contains(key)){
                recods[i] = newRecord;
            }
        }
    }
}
