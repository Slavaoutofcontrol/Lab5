package collectionManagers;

import collectionClasses.Movie;
import collectionSupport.LocalDateAdapter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import consoleManagers.ConsoleManager;


import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;


/**
 * the {@code StorageManager} class управляет хранением коллекции
 */
public class StorageManager {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    /**
     * Имя файла
     */
    private String fileName;

    /**
     * Конструктор класса
     * @param fileName fileName
     */
    public StorageManager(String fileName) {
        this.fileName = fileName;
    }



    /**
     * Записывает коллекцию в файл.
     * @param collection collection
     */
    public void writeCollection(Collection<Movie> collection) {
        try (PrintWriter collectionPrintWriter = new PrintWriter(fileName)) {
            collectionPrintWriter.println(gson.toJson(collection));
            System.out.println("Коллекция успешна сохранена в файл!");
        } catch (IOException exception) {
            System.out.println("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Считывает коллекцию из файла.
     * @return HashSet<Movie>
     */
    public HashSet<Movie> readCollection(){
        if (fileName != null && !fileName.isEmpty()) {
                try (var fileReader = new FileReader(fileName)){
                var collectionType = new TypeToken<HashSet<Movie>>() {
                }.getType();
                var reader = new BufferedReader(fileReader);

                var jsonString = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.isEmpty()) {
                    jsonString = new StringBuilder("[]");
                }

                HashSet<Movie> collection = gson.fromJson(jsonString.toString(),
                        collectionType);
                return collection;
            } catch (FileNotFoundException e){
                return null;

            } catch (IllegalStateException | IOException exception) {
                System.out.println("Непредвиденная ошибка!");
                System.exit(0);
            }
            }
            return new HashSet<>();
        }
}



