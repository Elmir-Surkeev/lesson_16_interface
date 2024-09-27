public class AccesData {
    private String key;
    private String value;
    public AccesData(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private class Key{
        public void writeKey(){
        }
        public void saveKey(){
        }
    }
    private class Value{
        public void writeValue(){

        }
        public void saveValue(){

        }
    }
}
