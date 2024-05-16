package consoleManagers;

import collectionClasses.Colour;
import collectionClasses.Country;
import collectionClasses.MovieGenre;
import collectionClasses.MpaaRating;

import java.util.Arrays;

/**
 * The {@code ReadManager} class управляет вводимыми данными
 */
public class ReadManager {
    /**
     * @see ConsoleManager
     */
    static ConsoleManager consoleManager = new ConsoleManager();

    /**
     * Возвращает название фильма
     * @return name
     */

    public String readName(){
        System.out.println("Введите название фильма: ");
        String name = consoleManager.readLine();
        while (true){
            if (name == null || name.isEmpty()){
                System.out.println("Имя не может быть пустой строкой.Пожалуйста,введите еще раз: ");
                name = consoleManager.readLine();
            }else{
                return name;
            }
        }
    }

    /**
     * Возвращает координату x
     * @return x
     */
    public static Integer readCoordinateX(){
        System.out.println("Введите координату X: ");
        Integer coordinateX = consoleManager.readInteger();
        while (true){
            if (coordinateX == null){
                System.out.println("Координата X не может быть пустой.Пожалуйста, введите еще раз: ");
                coordinateX = consoleManager.readInteger();
            }else{
                return coordinateX;
            }
        }
    }

    /**
     * Возвращает координату y
     * @return y
     */
    public float readCoordinateY(){
        System.out.println("Введите координату Y: ");
        float coordinateY = consoleManager.readFloat();
        while (true){
            if (coordinateY >= 214){
                System.out.println("Координата Y не может превышать значения:214.Пожалуйста, введите еще раз: ");
                coordinateY = consoleManager.readFloat();
            }else{
                return coordinateY;
            }
        }
    }
    /**
     * Возвращает кол-во оскаров
     * @return oscarCount
     */
    public int readOscarCount(){
        System.out.println("Введите количество оскаров у фильма: ");
        int oscarCount = consoleManager.readInt();
        while (true){
            if (oscarCount <= 0){
                System.out.println("Количество оскаров не может быть меньше 1.Пожалуйста, введите еще раз: ");
                oscarCount = consoleManager.readInt();
            }else{
                return oscarCount;
            }
        }
    }
    /**
     * Возвращает жанр фильма
     * @return genre
     */
    public MovieGenre readMovieGenre(){
        System.out.println("Вы должны ввести один из перечисленных жанров: " + Arrays.toString(MovieGenre.values()));
        while (true){
            try{
                return MovieGenre.valueOf(consoleManager.getValidatedValue("\nВведите жанр: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Жанр введён неверно. Введите ещё раз: ");
            }
        }
    }
    /**
     * Возвращает рейтинг фильма
     * @return mpaaRating
     */
    public MpaaRating readMpaaRating(){
        System.out.println("Вы должны ввести один из перечисленных рейтингов: " + Arrays.toString(MpaaRating.values()));
        while (true){
            try{
                return MpaaRating.valueOf(consoleManager.getValidatedValue("\nВведите рейтинг: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Рейтинг введён неверно. Введите ещё раз: ");
            }
        }
    }
    /**
     * Возвращает имя директора
     * @return directorsname
     */
    public String readDirectorsName(){
        System.out.println("Введите имя режиссера:");
        String directorsName = consoleManager.readLine();
        while (true){
            if (directorsName == null || directorsName.isEmpty()){
                System.out.println("Имя не может быть пустой строкой.Пожалуйста, введите еще раз: ");
                directorsName = consoleManager.readLine();
            }else{
                return directorsName;
            }
        }
    }
    /**
     * Возвращает вес режиссера
     * @return weight
     */
    public double readDirectorsWeight(){
        System.out.println("Внимание!\n Очень важная информация, введите вес режиссера:");
        double directorsWeight = consoleManager.readDouble();
        while (true){
            if (directorsWeight <= 0){
                System.out.println("Вес режиссера не может быть меньше 1.Пожалуйста,введите еще раз: ");
                directorsWeight = consoleManager.readDouble();
            }else{
                return directorsWeight;
            }
        }
    }
    /**
     * Возвращает цвет волос режиссера
     * @return haircolour
     */
    public Colour readDirectorsHairColour(){
        System.out.println("Вы должны ввести один из перечисленных цветов волос: " + Arrays.toString(Colour.values()));
        while (true){
            try{
                return Colour.valueOf(consoleManager.getValidatedValue("\nВведите цвет волос: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("Цвет волос введён неверно. Введите ещё раз: ");
            }
        }
    }
    /**
     * Возвращает национальность режиссера
     * @return nationality
     */
    public Country readDirectorsNationality(){
        System.out.println("Вы должны ввести один из перечисленных национальностей: " + Arrays.toString(Country.values()));
        while (true){
            try{
                return Country.valueOf(consoleManager.getValidatedValue("\nВведите национальность: ").toUpperCase());
            }catch (IllegalArgumentException e){
                System.out.println("национальность введена неверно. Введите ещё раз: ");
            }
        }
    }
}
