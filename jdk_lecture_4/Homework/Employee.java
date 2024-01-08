package Homework;

public class Employee {
    private final String number;
    private String phoneNumber;
    private final String name;
    private Integer experience;

    public Employee(String number, String name, String phoneNumber, Integer experience) {
        this.number = number;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "number='" + number + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
