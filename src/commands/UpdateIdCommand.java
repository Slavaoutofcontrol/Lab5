package commands;

import collectionManagers.CollectionManager;
import collectionClasses.Movie;

public class UpdateIdCommand implements Command{
    CollectionManager collectionManager;
    public UpdateIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            long id;
            try {
                id = Long.parseLong(args[1]);
                boolean flag = false;
                for (Movie movie: collectionManager.getMovies()) {
                    if (movie.getId() == id) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    Movie movie = collectionManager.getClientManager().getMovie();
                    collectionManager.updateId(movie, id);
                } else {
                    System.out.println("Элемента с таким id нет в коллекции");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Введены неверные аргументы.Попробуйте еще раз");
            }
        } else throw new IllegalArgumentException("Неверное количество аргументов");
    }

    @Override
    public String getDescription() {
        return "update_id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
