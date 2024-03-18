package ru.geekbrains.lesson2.homework1;

public class Dog extends Animal {
    public Dog(String name, Integer age) {
        super.setAge(age);
        super.setName(name);
    }

    public void voice() {
        System.out.println("Гав!");
    }

    public void walkWithOwner() {
        System.out.println("Гуляю с хозяином");
    }

    public void setPoroda(String poroda) {
        this.poroda = poroda;
    }


    public String getPoroda() {
        return poroda;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "poroda='" + poroda + '\'' +
                "name='" + getName() + '\'' +
                "age='" + getAge() + '\'' +
                "}\n";
    }

    private String poroda;
}
