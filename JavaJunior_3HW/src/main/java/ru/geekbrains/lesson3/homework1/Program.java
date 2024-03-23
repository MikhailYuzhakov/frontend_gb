package ru.geekbrains.lesson3.homework1;

/*
1. Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
Обеспечьте поддержку сериализации для этого класса. Создайте объект класса Student и инициализируйте его данными.
Сериализуйте этот объект в файл. Десериализуйте объект обратно в программу из файла. Выведите все поля объекта, включая GPA,
и обсудите, почему значение GPA не было сохранено/восстановлено.
 */


import java.io.*;


public class Program {
    static String FILE_NAME = "students.json";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File f = new File(FILE_NAME);
        Student student1 = new Student("Станислав", 22, 4.31);
        Student student2 = new Student();

        try (FileOutputStream fileOutputStream = new FileOutputStream("student-data.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(student1);
            JsonXmlSerialize.saveStudentToFile(FILE_NAME, student1);
            System.out.println("Объект Student сериализован.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        try (FileInputStream fileInputStream = new FileInputStream("student-data.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            student1 = (Student)objectInputStream.readObject();
            student2 = JsonXmlSerialize.loadStudentFromFile(FILE_NAME);
            System.out.println("Объект Student десериализован.");
        }

        System.out.println("Объект Student десериализован.");
        System.out.println("Имя: " + student1.getName());
        System.out.println("Возраст: " + student1.getAge());
        System.out.println("Средний балл (должен быть null, так как transient): " + student1.getGPA());

        System.out.println("Объект Student десериализован.");
        System.out.println("Имя: " + student2.getName());
        System.out.println("Возраст: " + student2.getAge());
        System.out.println("Средний балл (должен быть null, так как transient): " + student2.getGPA());
    }
}
