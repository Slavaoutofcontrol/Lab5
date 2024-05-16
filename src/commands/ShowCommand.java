package commands;

import collectionManagers.CollectionManager;

public class ShowCommand implements Command{
    CollectionManager collectionManager;
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            collectionManager.show();
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }


    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
