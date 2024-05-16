package commands;

import collectionManagers.CollectionManager;
import collectionClasses.Movie;

public class RemoveByIdCommand implements Command{
    CollectionManager collectionManager;
    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2){
            try {
                int id = Integer.parseInt(args[1]);
                boolean flag = false;
                for (Movie movie : collectionManager.getMovies()){
                    if (movie.getId() == id) {
                        flag = true;
                        break;
                    }
                }
                if (flag){
                    collectionManager.removeById(id);
                }else{
                    System.out.println("Элемента с таким id нет в коллекции");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы. Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }

    @Override
    public String getDescription() {
        return "remove_by_id : удалить элемент из коллекции по его id";
    }
}
