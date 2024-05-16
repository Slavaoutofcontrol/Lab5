package commands;

import collectionManagers.CollectionManager;

public class InfoCommand implements Command{
    CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            collectionManager.info();
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции";
    }
}
