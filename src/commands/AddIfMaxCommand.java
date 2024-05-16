package commands;

import collectionManagers.CollectionManager;
import collectionClasses.Movie;

public class AddIfMaxCommand implements Command{
    CollectionManager collectionManager;
    public AddIfMaxCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try{
                Movie movie = collectionManager.getClientManager().getMovie();
                collectionManager.addIfMax(movie);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное число аргументов");
    }
    @Override
    public String getDescription() {
        return "add_If_Max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }
}
