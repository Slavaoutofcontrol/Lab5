package consoleManagers;

import collectionManagers.CollectionManager;
import collectionManagers.StorageManager;

import java.util.Scanner;

/**
 * the {@code ConsoleManager} class управляет данными введенными с консоли
 */
public class ConsoleManager implements ReaderWriter{

    /**
     * Возвращает int, считанный из консоли
     * @return int
     */
    @Override
    public int readInt(){Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().trim());}

    /**
     * Возвращает integer, считанный из консоли
     * @return integer
     */
    @Override
    public Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().trim());
    }


    /**
     * Возвращает long, считанный из консоли
     * @return long
     */
    @Override
    public long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.parseLong(scanner.nextLine().trim());
    }

    /**
     * Возвращает float, считанный из консоли
     * @return float
     */
    @Override
    public float readFloat() {
        Scanner scanner = new Scanner(System.in);
        return Float.parseFloat(scanner.nextLine().trim());
    }

    /**
     * Возвращает double, считанный из консоли
     * @return double
     */
    @Override
    public double readDouble() {
        Scanner scanner = new Scanner(System.in);
        return Double.parseDouble(scanner.nextLine().trim());
    }

    /**
     * Возвращает string, считанный из консоли
     * @return string
     */
    @Override
    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    /**
     * Выводит переданный текст
     * @param text text
     */
    @Override
    public void write(String text) {
        System.out.print(text);
    }

    /**
     *
     * @param message message
     * @return validated message
     */
    @Override
    public String getValidatedValue(String message) {
        write(message);
        while (true) {
            String userPrint = readLine();
            if (!userPrint.isEmpty() && !userPrint.isBlank()) {
                return userPrint;
            }
        }
    }

    /**
     * Считывает название файла
     */
    public void fileRead() {
        int maxAttempts = 3; // Максимальное количество попыток ввода имени файла
        int attempts = 0; // Переменная для подсчета попыток

        while (attempts < maxAttempts) { // Пока количество попыток меньше максимального
            System.out.println("Введите название файла");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();

            if (path.isEmpty()) {
                System.out.println("Имя файла не может быть пустым.");
                attempts++; // Увеличиваем количество попыток
            } else {
                StorageManager storageManager = new StorageManager(path);
                if (storageManager.readCollection() == null){
                    System.out.println("Загрузочный файл не найден!");
                    attempts++;
                }else {
                    CollectionManager collectionManager = new CollectionManager();
                    collectionManager.setFilename(path);
                    collectionManager.setCollection(storageManager.readCollection());
                    CommandManager commandManager = new CommandManager(collectionManager);
                    commandManager.setFilename(path);
                    System.out.println("Коллекция успешна загружена!");
                    System.out.println("Чтобы узнать какие команды доступны воспользуйтесь командой help");
                    while (commandManager.getWork()) {
                        commandManager.existCommand();
                    }
                    break;
                    }
                    //break; // Перерываем цикл, если файл успешно найден и обработан
                }
            }
        if (attempts == maxAttempts) { // Если достигнуто максимальное количество попыток
            System.out.println("Так дела не делаются. Caio!");
            System.exit(0);
        }
    }
}

