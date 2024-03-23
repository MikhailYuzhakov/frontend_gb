package ru.geekbrains.lesson3.homework1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.geekbrains.lesson3.task2.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonXmlSerialize {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static void saveStudentToFile(String fileName, Student student) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), student);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(student);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Student loadStudentFromFile(String fileName) {
        Student student = new Student();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    student = objectMapper.readValue(file, Student.class);
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        student = (Student) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    student = xmlMapper.readValue(file, Student.class);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return student;
    }
}
