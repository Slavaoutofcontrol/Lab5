package commands;

import collectionManagers.CollectionManager;
import collectionClasses.MpaaRating;

public class CountLessThanMpaaRatingCommand implements Command{
    CollectionManager collectionManager;
    public CountLessThanMpaaRatingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            MpaaRating mpaaRating;
            try {
                mpaaRating = MpaaRating.valueOf(args[1].toUpperCase());
                collectionManager.CountLessThanMpaaRating(mpaaRating);
            }catch (IllegalArgumentException e){
                System.out.println("Введены неверные аргументы, попробуйте еще раз");
            }
        }else throw new IllegalArgumentException("Неверное количество аргументов");
    }

    @Override
    public String getDescription() {
        return "count_less_than_mpaa_rating mpaaRating : вывести количество элементов, значение поля mpaaRating которых меньше заданного";
    }
}
