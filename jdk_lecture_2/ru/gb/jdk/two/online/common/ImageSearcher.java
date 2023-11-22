package ru.gb.jdk.two.online.common;

import ru.gb.jdk.two.online.circles.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ImageSearcher {
    private final String directory;
    private static int imgPosition = 0;
    private final List<Path> imagePaths;
    public ImageSearcher(String directory) {
        this.directory = directory;
        this.imagePaths = getImagesPaths();
    }

    public Path getNextImagePath() {
        imgPosition++;
        return imagePaths.get(imgPosition);
    }

    public int getImagesCount() {
        return imagePaths.size();
    }

    /**
     * This method return all images paths in directory.
     * @param directory
     * @return
     */
    private List<Path> getImagesPaths() {
        Path imgPath = Paths.get(directory);
        try {
                Stream<Path> pathStream = Files.walk(imgPath);
                Files.walk(imgPath).close();
                return pathStream.toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
