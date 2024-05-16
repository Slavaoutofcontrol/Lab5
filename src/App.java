import collectionManagers.CollectionManager;
import collectionManagers.StorageManager;
import consoleManagers.*;
import java.io.File;


public class App {
    public static void main(String[] args) {
        try {
            CollectionManager collectionManager = new CollectionManager();
            ConsoleManager consoleManager = new ConsoleManager();
            while (true) {
                try {
                    if (args.length > 0) {
                        String link = args[0];
                        File file = new File(link);
                        if (file.exists() && !file.isDirectory()) {
                            StorageManager storageManager = new StorageManager(link);
                            collectionManager.setCollection(storageManager.readCollection());
                            CommandManager commandManager = new CommandManager(collectionManager);
                            commandManager.setFilename(link);
                            collectionManager.setFilename(link);
                            while (commandManager.getWork()) {
                                commandManager.existCommand();
                            }
                        } else {
                            consoleManager.fileRead();
                        }
                    } else {
                        consoleManager.fileRead();
                    }
                } catch (IllegalArgumentException e) {
                    consoleManager.fileRead();
                }
            }
        } catch (Exception e) {
            System.out.println("Упс! \n Что-то пошло не так.До встречи!");
        }
    }
}


