import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "./data.json";

        try {
            while (true) {
                JsonDatabase database = new JsonDatabase(filePath);

                System.out.println("Введите 1 для открытия подключения, 2 для проверки подключения или 0 для закрытия подключения");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                    switch (choice){
                        case 0:
                            database.closeConnection();
                            break;
                        case 1:
                            database.openConnection();
                            getNumber(database);
                        case 2:
                            database.isConnected();
                            getNumber(database);

                    }
            }
        }
        catch (InputMismatchException e){
            System.out.println("Вы ввели текст");
        }
        catch (IllegalArgumentException e){
            System.out.println("Возвращает недопустимый аргумент ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getNumber (JsonDatabase database){
        System.out.println("введите 1 для вывода количества записей, введите 2 для записи по индексу \n" +
                "введите 3 для добавление новой записи введите 4 для проверки по ключу\n" +
                "введите 5 для изменения по ключу");
        Scanner sc = new Scanner(System.in);
        int choicce = sc.nextInt();
        try {
            switch (choicce){
                case 1:
                    System.out.println("Количество записей: " + database.getRecordCount());
                    break;
                case 2:
                    System.out.println("Введите индекс чтобы посмотреть");
                    int getIndex = sc.nextInt();
                    String valueByIndex = database.getRecordByIndex(getIndex);
                    System.out.println("Запись по индексу "+getIndex+" value: " + valueByIndex);
                    break;
                case 3:
//                    System.out.println("введите - key");
//                    String firstLane = sc.next();
//                    System.out.println("Введите - value");
//                    String secondLane = sc.next();
                    database.addRecord("newKey", "newValue");
                    System.out.println("Новая запись добавлена.");
                    break;
                case 4:
                    System.out.println("Проверить содержания по ключу");
                    String checkKey = sc.next();
                    boolean contains = database.containsRecordByKey(checkKey);
                    System.out.println("Содержит ключ "+checkKey+"? " + contains);
                    break;
                case 5:
                    System.out.println("Введите существующий key для изменения");
                    String oldKey = sc.next();
                    System.out.println("Введите новый ключ");
                    String changeKey = sc.next();
                    System.out.println("Введите новое значение");
                    String changeValue = sc.next();
                    database.updateRecordByKey(oldKey, changeKey, changeValue);
                    System.out.println("Запись обновлена.");
                    break;
            }
        }  catch (InputMismatchException e){
            System.out.println("Вы ввели текст");
        }
        catch (IllegalArgumentException e){
            System.out.println("Возвращает недопустимый аргумент ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
