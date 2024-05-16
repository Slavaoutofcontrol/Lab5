package consoleManagers;

public interface ReaderWriter {
    int readInt();
    Integer readInteger();
    long readLong();
    float readFloat();
    double readDouble();
    String readLine();
    void write(String text);
    String getValidatedValue(String message);
}
