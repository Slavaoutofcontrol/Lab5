package commands;

import collectionManagers.*;
import collectionClasses.*;
import collectionSupport.Validator;
import consoleManagers.*;
import fileManagers.FileReaderManager;
import fileManagers.FileStack;


import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static java.lang.Long.parseLong;

public class ExecuteScriptCommand implements Command {

    private final CollectionManager collectionManager;

    private final HashMap<String, Command> commands;

    public ExecuteScriptCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.commands = CommandManager.getCommands();
    }

    Validator validator = new Validator();

    @Override
    public void execute(String[] args){
        if (args.length == 2) {
            String filename = args[1];
            if (!FileStack.getFileStack().contains(filename)) {
                try {
                    FileStack.addFile(filename);
                    FileReaderManager fileReaderManager = new FileReaderManager(filename);
                    while (fileReaderManager.getScanner().hasNextLine()) {
                        try {
                            String[] commandAndArgument = fileReaderManager.readCommandAndArgument();
                            String command = commandAndArgument[0];
                            if (commands.containsKey(command)) {
                                if (command.equals("add") || command.equals("add_if_max") || command.equals("add_if_min") || command.equals("remove_greater") || command.equals("update")) {
                                    String name = fileReaderManager.readName();
                                    Integer x = fileReaderManager.readCoordinateX();
                                    float y = fileReaderManager.readCoordinateY();
                                    int oscarsCount = fileReaderManager.readoscarsCount();
                                    MovieGenre movieGenre = fileReaderManager.readMovieGenre();
                                    MpaaRating mpaaRating = fileReaderManager.readMpaaRating();
                                    String dname = fileReaderManager.readDirectorsName();
                                    double weight = fileReaderManager.readDirectorsWeight();
                                    Colour colour = fileReaderManager.readDirectorsHairColour();
                                    Country country = fileReaderManager.readDirectorsNationality();
                                    Movie movie = validator.getValidatedElement(new Movie(name, new Coordinates(x, y), oscarsCount,
                                            movieGenre, mpaaRating, (new Person(dname, weight, colour, country))));
                                    if (movie != null) {
                                        switch (command) {
                                            case "add" -> {
                                                collectionManager.add(movie);
                                                System.out.println("Элемент успешно добавлен в коллекцию");
                                            }
                                            case "add_if_max" -> collectionManager.addIfMax(movie);
                                            case "add_if_min" -> collectionManager.addIfMin(movie);
                                            case "remove_greater" -> collectionManager.removeGreater(movie);
                                            case "update" -> {
                                                collectionManager.updateId(movie, parseLong(commandAndArgument[1]));
                                            }
                                        }
                                    }
                                }  else {
                                    commands.get(command).execute(commandAndArgument);
                                }
                            }
                        } catch (IllegalArgumentException | NoSuchElementException ignored) {}
                    }
                }catch (AccessDeniedException e) {
                System.out.println("У меня нет прав, только лапки");}
                catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } finally {
                    FileStack.removeFile();
                }
            }
        }
    }

    @Override
    public String getDescription() {
        return "execute_script file_name: считать и исполнить скрипт из указанного файла";
    }
}