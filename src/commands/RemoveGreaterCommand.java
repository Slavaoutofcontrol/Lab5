package commands;

import collectionManagers.CollectionManager;
import collectionClasses.Movie;

public class RemoveGreaterCommand implements Command{
    CollectionManager collectionManager;
    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try{
                Movie movie = collectionManager.getClientManager().getMovie();
                collectionManager.removeGreater(movie);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов");

    }

    @Override
    public String getDescription() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
}