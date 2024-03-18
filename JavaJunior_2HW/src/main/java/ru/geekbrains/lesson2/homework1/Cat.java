package ru.geekbrains.lesson2.homework1;

public class Cat extends Animal {
    public Cat(String name, Integer age) {
        super.setAge(age);
        super.setName(name);
    }

    public void voice() {
        System.out.println("Мяу!");
    }

    public void walk() {
        System.out.println("Гуляет сама по себе");
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "type='" + type + '\'' +
                "name='" + getName() + '\'' +
                "age='" + getAge() + '\'' +
                "}\n";
    }

    private String type;
}
