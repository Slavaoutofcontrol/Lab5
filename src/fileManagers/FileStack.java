package fileManagers;
import java.nio.file.AccessDeniedException;
import java.util.Stack;
public class FileStack {




    /**
     * The {@code FilesStack} class хранит стек файлов, используемых пользователем
     *
     */

        /**
         * Стек файлов
         */
        private static final Stack<String> filesStack = new Stack<>();

        /**
         * Возвращает стек
         * @return filesStack
         */
        public static Stack<String> getFileStack() {
            return filesStack;
        }

        /**
         * Добавляет файлы в стек
         * @param filename filename
         */
        public static void addFile(String filename) throws AccessDeniedException {
            filesStack.push(filename);
        }

        /**
         * Удаляет файл из стека
         */
        public static void removeFile(){
            filesStack.pop();
        }
    }

