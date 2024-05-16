package commands;

import collectionManagers.CollectionManager;
import collectionClasses.MovieGenre;

public class CountGreaterThanGenreCommand implements Command{
    CollectionManager collectionManager;
    public CountGreaterThanGenreCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            MovieGenre genre;
            try {
                genre = MovieGenre.valueOf(args[1].toUpperCase());
                collectionManager.CountGreaterThanGenre(genre);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы, попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }
    @Override
    public String getDescription() {
        return "count_greater_than_genre genre : вывести количество элементов, значение поля genre которых больше заданного";
    }
}
