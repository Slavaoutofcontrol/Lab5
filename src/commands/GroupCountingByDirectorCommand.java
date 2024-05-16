package commands;

import collectionManagers.CollectionManager;

public class GroupCountingByDirectorCommand implements Command{
    CollectionManager collectionManager;
    public GroupCountingByDirectorCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 1) {
            System.out.println("Неверное количество аргументов");
            return;
        }
        collectionManager.GroupCountingByDirector();
    }

    @Override
    public String getDescription() {
        return "group_counting_by_director : сгруппировать элементы коллекции по значению поля director, вывести количество элементов в каждой группе";
    }
}