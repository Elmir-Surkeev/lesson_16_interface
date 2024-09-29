import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DataService dataService = new DataServiceImpl();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите 1 для открытия сервера, 2 для проверки подключения, 0 для выхода и закрытия подключения");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 0:
                        dataService.closeDatabaseConnection();
                        System.out.println("Подключение закрыто.");
                        return;
                    case 1:
                        dataService.openDatabaseConnection();
                        startFunctionalData(dataService, sc);
                        break;
                    case 2:
                        if (dataService.isOpenDatabaseConnection()) {
                            System.out.println("Все отлично, доступ открыт");
                            startFunctionalData(dataService, sc);
                        } else {
                            System.out.println("Доступ к базе данных отсутствует");
                        }
                        break;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Ошибка ввода. Попробуйте снова.");
                sc.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void startFunctionalData(DataService dataService, Scanner sc) throws Exception {
        System.out.println("Введите 1 для записи по индексу, 2 для проверки по ключу, 3 для записи по ключу\n" +
                "введите 4 для считывания по начальным и конечным индексам" + "\n" +
                "введите 5 для просмотра количества записей, введите 6 для того чтобы добавить запись в базу\n" +
                "введите 7 для обновления значения по индексу, введите 8 для обновления содержимого по ключу");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Введите индекс");
                int index = sc.nextInt();
                sc.nextLine();  // Очищаем буфер
                System.out.println(dataService.getDataByIndex(index));
                break;
            case 2:
                System.out.println("Введите ключ для просмотра по ключу");
                String str = sc.nextLine();
                System.out.println(dataService.isDataByKey(str));
                break;
            case 3:
                System.out.println("Введите ключ");
                String strr = sc.next();
                System.out.println(dataService.getDataByKey(strr));
                break;
            case 4:
                System.out.println("Введите начальный индекс");
                int startIndex = sc.nextInt();
                System.out.println("Введите конечный индекс");
                int endIndex = sc.nextInt();
                dataService.getDataByIndexRange(startIndex, endIndex);
                break;
            case 5:
                int count = dataService.countDatabaseSize();
                System.out.println("Количество записей: " + count);
                break;
            case 6:
                System.out.println("Введите значение");
                String value = sc.next();
                System.out.println("Введите ключ");
                String key = sc.next();
                dataService.addDataToDatabase(new Data(key, value));
                break;
            case 7:
                System.out.println("Введите индекс");
                int indexx = sc.nextInt();
                dataService.updateDataByIndex(indexx, new Data());
                break;
            case 8:
                System.out.println("Введите ключ");
                String values = sc.next();
                dataService.updateDataByKey(values, new Data());
                break;
            default:
                System.out.println("Вы ввели неправильное число");
        }
    }
}
