package ru.gb.homework;

import ru.gb.seminar.models.Student;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "schooldb.courses")
public class Course {
    private static final String[] titles = new String[] { "Математика", "Java Junior", "Базовый язык C",
            "Русский язык", "Введение в программирование", "OpenCV", "Численные методы", "Электродинамика",
            "Электроника", "Математический анализ" };
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private int duration;

    public Course() {

    }

    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public static Course create(){
        return new Course(titles[random.nextInt(titles.length)], random.nextInt(10, 90));
    }

    public void updateDuration() {
        setDuration(random.nextInt(10, 90));
    }

    public void updateTitle(){
        setTitle(titles[random.nextInt(titles.length)]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
