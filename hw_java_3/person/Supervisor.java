package person;

import java.util.ArrayList;

public class Supervisor extends Employee {
    protected boolean isSupervisor;
    public Supervisor() {
        super();
    }

    public static void increaseSalary(ArrayList<Employee> employees, Integer age, Integer inc) {
        for (Employee employee : employees) {
            if (employee.getAge() > age && !employee.isSupervisor) {
                employee.setSalary(employee.getSalary() + inc);
            }
        }
    }
}
