package commands;

import collectionManagers.CollectionManager;
import collectionClasses.Movie;

public class AddCommand implements Command{
    CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            try{
                Movie movie = collectionManager.getClientManager().getMovie();
                boolean flag = true;
                for (Movie movie1 : collectionManager.getMovies()){
                    if (movie1.equals(movie)) {
                        flag = false;
                    }
                }
                if (flag){
                    collectionManager.add(movie);
                    System.out.println("Элемент успешно добавлен в коллекцию");
                }else{
                    System.out.println("Введенный элемент уже есть в коллекции");
                }
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы.Попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }
    @Override
    public String getDescription() {
        return "add {element}: добавить новый элемент в коллекцию";
    }
}
