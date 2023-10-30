import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/* 1. Написать функцию,создающую резервную копию всех файлов
в директории (без поддиректорий) во вновь созданную папку ./backup
 */
public class Task1 {
    public static void backup() {
        File folder = new File("./backup_folder");
        Path backup = Paths.get("./backup");
        try {
            Files.createDirectory(backup);
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                Files.copy(file.toPath(), Paths.get("./backup/" + file.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
