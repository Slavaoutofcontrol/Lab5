package consoleManagers;

import collectionManagers.CollectionManager;
import commands.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The {@code CommandManager} class управляет исполнением команд
 *

 */
public class CommandManager {
    /**
     * Условие работы
     */
    private boolean inProsess = true;
    /**
     * HashMap сщ всеми командами
     */
    private static HashMap<String, Command> commands = new HashMap<>();
    /**
     * Имя файла
     */
    private String filename;

    /**
     * Конструктор класса, создающий объект класса {@code CommandManager} и заполняет HashMap командами
     * @param collectionManager collectionManager
     * @see CollectionManager
     */
    public CommandManager(CollectionManager collectionManager){
        commands.put("help", new HelpCommand(collectionManager));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager));
        commands.put("update", new UpdateIdCommand(collectionManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager));
        commands.put("exit", new ExitCommand(collectionManager));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager));
        commands.put("group_counting_by_director", new GroupCountingByDirectorCommand(collectionManager));
        commands.put("count_less_than_mpaa_rating", new CountLessThanMpaaRatingCommand(collectionManager));
        commands.put("count_greater_than_genre", new CountGreaterThanGenreCommand(collectionManager));
    }


    /**
     * Устанавливает имя файла с главной коллекцией
     * @param filename filename
     */
    public void setFilename(String filename){
        this.filename = filename;
    }
    /**
     * Возвращает команды из HashMap
     * @return commands HashMap
     */
    public static HashMap<String, Command> getCommands(){
        return commands;
    }

    /**
     * Возвращает состояние программы
     * @return boolean
     */
    public boolean getWork(){
        return this.inProsess;
    }

    /**
     * Считывает команду из файла и исполняет ее
     */
    public void existCommand(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.flush();
            System.out.println("Введите команду: ");
            String command = scanner.nextLine().trim().toLowerCase();
            String[] args = command.split(" ");
            if (commands.containsKey(args[0])){
                try{
                    commands.get(args[0]).execute(args);
                }catch (IllegalArgumentException e){
                    System.out.println("Что-то пошло не так. " + e.getMessage() + "Попробуйте еще раз.");
                }
            }else{
                System.out.println("Команда \"" + args[0] + "\" не найдена.");
            }
        }catch (Exception e){
            System.out.println("Что-то пошло не так " + e.getMessage() + ".Пока, надеюсь еще увидимся.");
            this.inProsess = false;
            System.exit(0);
        }
    }
}