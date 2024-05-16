package collectionSupport;



import collectionClasses.Movie;

import java.util.HashSet;

/**
 * The {@code IdGenerator} class генерирует уникальное значение идентификатора
 *
 */
public class IdGenerator {
    /**
     * HashSet с уже использованными значениями
     */
    private static HashSet<Long> generatedIds = new HashSet<>();

    /**
     * Генерирует идентификатор с помощью {@code System.nanoTime}.
     * @return id
     */
    public static long generateId(){
        long id = System.nanoTime();
        while (generatedIds.contains(id)){
            id = System.nanoTime();
        }
        generatedIds.add(id);
        return id;
    }
}
