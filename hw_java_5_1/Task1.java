import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
Создать три текстовых файла.
 */
public class Task1 {
    private static final List<String> lines = Arrays.asList("smth text", "another text");
    public static void run() {
        try {
            Path file = Files.createFile(Paths.get("test_file.txt")); //создаст файл
            write(file);
            file = Files.createFile(Paths.get("test_file1.txt"));
            write(file);
            file = Files.createFile(Paths.get("test_file2.txt"));
            write(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void write(Path file) throws IOException {
        if (Files.exists(file)) {
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
    }
}
