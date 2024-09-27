import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //String filePath = "./data.json";
        JsonDatabase database = new JsonDatabase("./data.json");

        try {
            while (true) {
                System.out.println("Введите 1 для открытия подключения, 2 для проверки подключения или 0 для закрытия подключения");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        database.closeConnection();
                        return;
                    case 1:
                        database.openConnection();
                        getNumber(database);
                        break;
                    case 2:
                        database.isConnected();
                        if (database.isConnected()){
                            getNumber(database);
                        }else {
                            System.out.println("Cоединение отсутствует");
                        }
                        break;
                    default:
                        System.out.println("Неправильный выбор, попробуйте снова.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели текст вместо числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Возвращает недопустимый аргумент.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getNumber(JsonDatabase database) {
        System.out.println("Введите 1 для вывода количества записей, 2 для просмотра по индексу \n" +
                "Введите 3 для добавления новой записи, 4 для проверки по ключу, \n" +
                "Введите 5 для изменения по ключу");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        try {
            switch (choice) {
                case 1:
                    System.out.println("Количество записей: " + database.getRecordCount());
                    break;
                case 2:
                    System.out.println("Введите индекс чтобы посмотреть");
                    int getIndex = sc.nextInt();
                    String valueByIndex = database.getRecordByIndex(getIndex);
                    System.out.println("Запись по индексу " + getIndex + " value: " + valueByIndex);
                    break;
                case 3:
                    System.out.println("Введите ключ для новой записи");
                    String newKey = sc.next();
                    System.out.println("Введите значение для новой записи");
                    String newValue = sc.next();
                    database.addRecord(newKey, newValue);
                    System.out.println("Новая запись добавлена.");
                    break;
                case 4:
                    System.out.println("Введите ключ для проверки");
                    String checkKey = sc.next();
                    boolean contains = database.containsRecordByKey(checkKey);
                    System.out.println("Содержит ключ " + checkKey + "? " + contains);
                    break;
                case 5:
                    System.out.println("Введите существующий ключ для изменения");
                    String oldKey = sc.next();
                    System.out.println("Введите новый ключ");
                    String changeKey = sc.next();
                    System.out.println("Введите новое значение");
                    String changeValue = sc.next();
                    database.updateRecordByKey(oldKey, changeKey, changeValue);
                    System.out.println("Запись обновлена.");
                    break;
                default:
                    System.out.println("Неправильный выбор, попробуйте снова.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели текст вместо числа.");
        } catch (IllegalArgumentException e) {
            System.out.println("Возвращает недопустимый аргумент.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
