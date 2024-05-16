package commands;

import collectionManagers.CollectionManager;

public class ClearCommand implements Command{
    CollectionManager collectionManager;
    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.clear();
            System.out.println("Коллекция очищена");
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
