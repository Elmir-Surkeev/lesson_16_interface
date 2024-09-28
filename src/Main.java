
public class Main {
    public static void main(String[] args) throws Exception {
        DataService dataService = new DataServiceImpl();
        dataService.openDatabaseConnection();
        Data data = new Data("qwe", "asd");

        dataService.updateDataByIndex(0, data);
    }
}
