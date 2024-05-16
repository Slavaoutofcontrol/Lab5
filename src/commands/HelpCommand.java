package commands;

import collectionManagers.CollectionManager;

public class HelpCommand implements Command {
    CollectionManager collectionManager;
    public HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            collectionManager.help();
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }


    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
