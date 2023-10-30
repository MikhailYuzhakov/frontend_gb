import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*
Написать метод, осуществляющий конкатенацию файлов.
 */
public class Task2 {
    public static void run() {
        List<String> mergedContent = new ArrayList<String>();
        try {
            Path file = Paths.get("test_file.txt");
            mergedContent.addAll(read(file));
            file = Paths.get("test_file1.txt");
            mergedContent.addAll(read(file));
            file = Paths.get("test_file2.txt");
            mergedContent.addAll(read(file));
            file = Files.createFile(Paths.get("merged_file.txt"));
            merge(file, mergedContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> read(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    private static void merge(Path file, List<String> content) throws IOException {
        Files.write(file, content, StandardCharsets.UTF_8);
    }
}
