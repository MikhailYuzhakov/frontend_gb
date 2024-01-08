package Homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EmployeeList {
    private List<Employee> employees;

    public EmployeeList() {
        employees = new ArrayList<>();
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> findByExperience(Integer experience) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getExperience().equals(experience)) employeeList.add(employee);
        }
        return employeeList;
    }

    public List<String> findNumberByName(String name) {
        List<String> phones = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) phones.add(employee.getPhoneNumber());
        }
        return phones;
    }

    public Employee findByNumber(String number) {
        for (Employee employee : employees) {
            if (employee.getNumber().equals(number))
                return employee;
        }
        return null;
    }
}
