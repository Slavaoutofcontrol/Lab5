package commands;

import collectionManagers.CollectionManager;
import collectionClasses.Movie;

public class AddIfMinCommand implements Command{
    CollectionManager collectionManager;
    public AddIfMinCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1){
            try{
                Movie movie = collectionManager.getClientManager().getMovie();
                collectionManager.addIfMax(movie);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы.Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное число аргументов");
    }
    @Override
    public String getDescription() {
        return "add_If_Min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем значение наименьшего элемента этой коллекции";
    }
}
