package consoleManagers;

import collectionClasses.*;

/**
 * the {@code ClientManager} управляет введенными с консоли данными о фильме
 */
public class ClientManager {
    ReadManager readManager = new ReadManager();

    /**
     * Возвращает введенный фильм
     * @return movie
     */
    public Movie getMovie(){
        String name = readManager.readName();
        Integer x = readManager.readCoordinateX();
        float y = readManager.readCoordinateY();
        int oscarCount = readManager.readOscarCount();
        MovieGenre movieGenre = readManager.readMovieGenre();
        MpaaRating mpaaRating = readManager.readMpaaRating();
        String directorsName = readManager.readDirectorsName();
        double directorsWeight = readManager.readDirectorsWeight();
        Colour directorsHairColour = readManager.readDirectorsHairColour();
        Country directorsNationality = readManager.readDirectorsNationality();
        return new Movie(name, new Coordinates(x, y), oscarCount, movieGenre, mpaaRating, new Person(directorsName, directorsWeight, directorsHairColour, directorsNationality));
    }
}
