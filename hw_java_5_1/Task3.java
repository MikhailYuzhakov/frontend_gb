import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
Написать метод поиска слова внутри файла.
*/
public class Task3 {
    public static boolean run(String keyWord) {
        boolean isFound = false;
        Path file = Paths.get("test_file1.txt");
        try {
            List<String> content = Files.readAllLines(file, StandardCharsets.UTF_8);
            for (String str: content) {
                for (String strs: str.split(" ")) {
                    if (strs.equals(keyWord)) {
                        isFound = true;
                        return isFound;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isFound;
    }
}
