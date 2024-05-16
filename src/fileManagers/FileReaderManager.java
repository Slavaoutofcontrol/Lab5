package fileManagers;

import collectionClasses.Colour;
import collectionClasses.Country;
import collectionClasses.MovieGenre;
import collectionClasses.MpaaRating;
import consoleManagers.CommandManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
     * The {@code FileReaderManager} class управляет чтением из файлов
     */
    public class FileReaderManager {
        private final FileReader fileReader;
        private  Scanner scanner;
        /**
         * Конструктор класса
         * @param filename filemame
         * @throws FileNotFoundException if there is no such file
         */
        public FileReaderManager(String filename) throws FileNotFoundException {
            this.fileReader = new FileReader(filename);
            this.scanner = new Scanner(fileReader);
        }

        /**
        * @return scanner
        */
        public Scanner getScanner() {
            return this.scanner;
        }


        /**
         * Возвращает название фильма
         * @return name
         * @throws IllegalArgumentException if string id empty
         */
        public String readName() throws IllegalArgumentException{
            String name = scanner.nextLine();
            if (name.isEmpty() || name.isBlank()) {
                throw new IllegalArgumentException("");
            } else {
                return name;
            }
        }



        /**
         * Возвращает координату x
         * @return x
         */
        public Integer readCoordinateX() {
            return scanner.nextInt();
        }

        /**
         * Возвращает координату y
         * @return y
         */
        public float readCoordinateY() {
            return scanner.nextFloat();
        }
        /**
        * Возвращает кол-во оскаров
        * @return oscarCount
        */
        public int readoscarsCount() throws IllegalArgumentException{
            int oscarsCount = scanner.nextInt();
            if (oscarsCount > 0) {
                return oscarsCount;
            } else {
                throw new IllegalArgumentException("");
            }
        }
        /**
         * Возвращает жанр фильма
         * @return genre
         */
        public MovieGenre readMovieGenre() {
            String musicGenre = scanner.next().trim().toUpperCase();
            scanner.nextLine();
            return switch (musicGenre) {
                case "WESTERN" -> MovieGenre.WESTERN;
                case "DRAMA" -> MovieGenre.DRAMA;
                case "ADVENTURE" -> MovieGenre.ADVENTURE;
                case "TRAGEDY" -> MovieGenre.TRAGEDY;
                default -> null;
            };
        }
        /**
        * Возвращает рейтинг фильма
        * @return mpaaRating
        */
        public MpaaRating readMpaaRating() {
            String mpaarating = scanner.next().trim().toUpperCase();
            scanner.nextLine();
            return switch (mpaarating) {
                case "G" -> MpaaRating.G;
                case "PG" -> MpaaRating.PG;
                case "PG_13" -> MpaaRating.PG_13;
                case "R" -> MpaaRating.R;
                case "NC_17" -> MpaaRating.NC_17;
                default -> null;
            };
        }
        /**
        * Возвращает имя режиссера фильма
        * @return directorsname
        */
        public String readDirectorsName(){
            String dname = scanner.nextLine();
            if (dname.isEmpty() || dname.isBlank()) {
                throw new IllegalArgumentException("");
            } else {
                return dname;
            }
        }
        /**
        * Возвращает вес режиссера
        * @return weight
        */
        public double readDirectorsWeight(){
            double weight = scanner.nextInt();
            if (weight > 0) {
                return weight;
            } else {
                throw new IllegalArgumentException("");
            }
        }
        /**
        * Возвращает цвет волос режиссера
        * @return haircolour
        */
        public Colour readDirectorsHairColour(){
            String colour = scanner.next().trim().toUpperCase();
            scanner.nextLine();
            return switch (colour) {
                case "WHITE" -> Colour.WHITE;
                case "ORANGE" -> Colour.ORANGE;
                case "YELLOW" -> Colour.YELLOW;
                case "GREEN" -> Colour.GREEN;
                default -> null;
            };
        }
        /**
        * Возвращает национальность режиссера
        * @return nationality
        */
        public Country readDirectorsNationality(){
            String country = scanner.next().trim().toUpperCase();
            return switch (country) {
                case "GERMANY" -> Country.GERMANY;
                case "NORTH_KOREA" -> Country.NORTH_KOREA;
                case "VATICAN" -> Country.VATICAN;
                case "UNITED_KINGDOM" -> Country.UNITED_KINGDOM;
                default -> null;
            };
        }

        /**
         * Возвращает команду и ее аргументы, если они есть
         * @return commandAndArgument
         * @throws IllegalArgumentException if line is empty or there is too many args
         */
        public String[] readCommandAndArgument() throws IllegalArgumentException{
            String[] commandAndArgument = scanner.nextLine().trim().toLowerCase().split(" ");
            String command = commandAndArgument[0].trim();
            if (CommandManager.getCommands().containsKey(command)) {
                if (command.equals("execute_script") || command.equals("count_greater_than_genre")||
                        command.equals("count_less_than_mpaarating") || command.equals("remove_by_id") ||
                        command.equals("update")) {
                    if (commandAndArgument.length == 2) {
                        return new String[]{command, commandAndArgument[1].trim()};
                    } else throw new IllegalArgumentException("");
                } else {
                    if (commandAndArgument.length == 1) {
                        return new String[]{command};
                    } else throw new IllegalArgumentException("");
                }
            } else throw new IllegalArgumentException("");
        }
    }

