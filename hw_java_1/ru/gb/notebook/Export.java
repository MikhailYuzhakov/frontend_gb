package ru.gb.notebook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Export {
    String filename;
    File file;
    public Export(String filename) {
        this.filename = filename;
        this.file = new File(filename + ".txt");
    }

    public void saveToFile(Note note) {
        try  {
            if (file.exists()) {
                FileWriter writer = new FileWriter(file, true);
                writer.write(note.toString());
                writer.close();
            } else {
                FileWriter writer = new FileWriter(file);
                writer.write(note.toString());
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
