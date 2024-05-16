package collectionManagers;


import collectionClasses.Movie;
import collectionClasses.MovieGenre;
import collectionClasses.MpaaRating;
import collectionClasses.Person;
import consoleManagers.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * The {@code CollectionManager} class управляет коллекцией
 *

 */
public class CollectionManager {
    /**
     * Главная коллекция фильмов
     */
    HashSet<Movie> movies = new HashSet<>();
    /**
     * Дата инициализации коллекции
     */
    private LocalDate localDateTime = LocalDate.now();
    /**
     * @see ClientManager
     */
    private ClientManager clientManager = new ClientManager();
    /**
     * Название файл, содержащий коллекцию
     */
    private String filename;


    /**
     * Конструктор для класса
     */
    public CollectionManager() {
    }

    /**
     * Устанавливает главную коллекцию
     * @param movies movies
     */
    public void setCollection(HashSet<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Устанавливает файл, содержащий коллекцию
     * @param filename filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Возвращает ClientManager
     * @return ClientManager
     */
    public ClientManager getClientManager() {
        return this.clientManager;
    }

    /**
     * Возвращает главную коллекцию
     * @return HashSet</Movie>
     */
    public HashSet<Movie> getMovies() {
        return movies;
    }

    /**
     * Выводит информацию о коллекции
     */
    public void info() {
        String info = "Тип коллекции: " + movies.getClass().getSimpleName() + "\nДата инициализации: " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\nКоличество элементов: " + movies.size();
        System.out.println(info);
    }

    /**
     * Полностью выводит коллекцию
     */
    public void show() {
        if (!movies.isEmpty()) {
            for (Movie movie : movies) {
                System.out.println(movie.toString());
            }
        } else {
            System.out.println("Коллекция не содержит элементов");
        }
    }

    /**
     * Выводит доступные команды
     */
    public void help() {
        CommandManager.getCommands().values().forEach(command -> System.out.println(command.getDescription()));
    }

    /**
     * Добавляет элемент в коллекцию
     * @param movie movie
     */
    public void add(Movie movie) {
        movies.add(movie);
    }

    /**
     * Обновляет элемент по переданному идентификатору
     * @param newMovie newMovie
     * @param id id
     */
    public void updateId(Movie newMovie, Long id){
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movie.setName(newMovie.getName());
                movie.setCoordinates(newMovie.getCoordinates());
                movie.setOscarsCount(newMovie.getOscarsCount());
                movie.setGenre(newMovie.getGenre());
                movie.setMpaaRating(newMovie.getMpaaRating());
                movie.setDirector(newMovie.getDirector());
                System.out.println("Элемент успешно обновлен");
                break;
            }
        }

    }

    /**
     * Удаляет элемент по идентификатору
     * @param id id
     */
    public void removeById(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                movies.remove(movie);
                System.out.println("Элемент удален из коллекции");
                break;
            }
        }
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        movies.clear();
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void save() {
        StorageManager storageManager = new StorageManager(this.filename);
        try {
            storageManager.writeCollection(this.movies);
        } catch (NullPointerException e) {
            System.out.println("Что-то пошло не так");
        }
    }

    /**
     * Окончание работы
     */
    public void exit() {
        System.out.println("Ciao!");
        System.exit(0);
    }

    /**
     * Добавляет элемент, если он наибольший
     * @param newMovie newMovie
     */
    public void addIfMax(Movie newMovie) {
        int maxNumberOfOscars = 0;
        for (Movie movie : movies) {
            if (movie.getOscarsCount() > maxNumberOfOscars) {
                maxNumberOfOscars = movie.getOscarsCount();
            }
        }
        if (newMovie.getOscarsCount() > maxNumberOfOscars) {
            add(newMovie);
            System.out.println("Элемент успешно добавлен в коллекцию");
        } else {
            System.out.println("Элемент не добавлю, он не наибольший");
        }
    }

    /**
     * Добавляет элемент, если он наименьший
     * @param newMovie newMovie
     */
    public void addIfMin(Movie newMovie) {
        int maxNumberOfOscars = 2147483647;
        for (Movie movie : movies) {
            if (movie.getOscarsCount() < maxNumberOfOscars) {
                maxNumberOfOscars = movie.getOscarsCount();
            }
        }
        if (newMovie.getOscarsCount() < maxNumberOfOscars) {
            add(newMovie);
            System.out.println("Элемент успешно добавлен в коллекцию");
        } else {
            System.out.println("Элемент не добавлю, он не наименьший");
        }
    }

    /**
     * Удаляет все элементы, больше переданного
     * @param newMovie newMovie
     */
    public void removeGreater(Movie newMovie) {
        movies.removeIf(movie -> movie.getOscarsCount() > newMovie.getOscarsCount());
        movies.add(newMovie);
        System.out.println("Элементы,больше чем заданный, удалены");
    }

    /**
     * Группирует элементы коллекции по режиссеру
     */
    public void GroupCountingByDirector() {
        Map<Person, Integer> directorCount = new HashMap<>();
        for (Movie movie : movies) {
            directorCount.put(movie.getDirector(), directorCount.getOrDefault(movie.getDirector(), 0) + 1);
        }

        for (Map.Entry<Person, Integer> entry : directorCount.entrySet()) {
            System.out.println("Режиссер: " + entry.getKey() + ", количество фильмов: " + entry.getValue());
        }
    }

    /**
     * Выводит элементы коллекции, значение поля MpaaRating которых меньше переданного значения
     * @param mpaaRating mpaaRating
     */
    public void CountLessThanMpaaRating(MpaaRating mpaaRating) {
        Map<MpaaRating, Integer> ratingCount = new HashMap<>();
        for (Movie movie : movies) {
            ratingCount.put(movie.getMpaaRating(), ratingCount.getOrDefault(movie.getMpaaRating(), 0) + 1);
        }
        int i = ratingCount.get(mpaaRating);
        int result = 0;
        for (MpaaRating key: ratingCount.keySet())
        {
            if(key == mpaaRating){
                continue;
            }
            if(i > ratingCount.get(key)){
                result += ratingCount.get(key);
            }
        }
        System.out.println("Количество элементов, значение рейтинга которых меньше рейтинга " + mpaaRating +  " : " + result);
    }

    /**
     * Выводит элементы коллекции, значение поля Genre которых больше переданного значения
     * @param genre genre
     */
    public void CountGreaterThanGenre(MovieGenre genre) {
        Map<MovieGenre, Integer> genreCount = new HashMap<>();
        for (Movie movie : movies) {
           genreCount.put(movie.getGenre(), genreCount.getOrDefault(movie.getGenre(), 0) + 1);
        }
        int i = genreCount.get(genre);
        int result = 0;
        for (MovieGenre key: genreCount.keySet())
        {
            if(key == genre){
                continue;
            }
            if(i < genreCount.get(key)){
                result += genreCount.get(key);
            }
        }
        System.out.println("Количество элементов, значение жанра которых больше жанра " + genre +  " : " + result);
    }
}

