package person;

import java.util.ArrayList;

public class Employee {
    protected String fullname = null;
    protected String position = null;
    protected String phoneNumber = null;
    protected Integer salary = null;
    protected Integer age = 0;
    protected boolean isSupervisor = false;
    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return String.format("ФИО: %s\n Должность: %s\n Телефон: %s\n Зарплата: %d\n Возраст: %d\n",
                                fullname, position, phoneNumber, salary, age);
    }

    public Employee setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Employee setFullname(String fullname) {
        this.fullname = fullname;
        return this;
    }

    public Employee setSupervisor(boolean supervisor) {
        isSupervisor = supervisor;
        return this;
    }

    public Employee setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Employee setPosition(String position) {
        this.position = position;
        return this;
    }

    public Employee setSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public boolean getStatus() {
        return isSupervisor;
    }

    public boolean isSupervisor() {
        return isSupervisor;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public Integer getSalary() {
        return salary;
    }

    public static void increaseSalary(ArrayList<Employee> employees, Integer age, Integer inc) {
        for (Employee employee : employees) {
            if (employee.getAge() > age) {
                employee.setSalary(employee.getSalary() + inc);
            }
        }
    }

}
