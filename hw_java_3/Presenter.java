import person.Employee;
import person.Supervisor;

import java.util.ArrayList;

public class Presenter {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setFullname("Иванов Сергей Владимирович").setPosition("Советник").setPhoneNumber("8-999-124" +
                "-13-23").setSalary(35000).setAge(27).setSupervisor(false);
        employees.add(e1);
        System.out.println(employees.get(0));

        Employee e2 = new Employee();
        e2.setFullname("Сергеев Иван Тимофеевич").setPosition("Консультант").setPhoneNumber("8-919-134" +
                "-15-21").setSalary(27000).setAge(24).setSupervisor(false);
        employees.add(e2);
        System.out.println(employees.get(1));

        Employee e3 = new Employee();
        e3.setFullname("Казаков Иван Сергеевич").setPosition("Заместитель директора").setPhoneNumber("8-913-234" +
                "-25-41").setSalary(52000).setAge(46).setSupervisor(false);;
        employees.add(e3);
        System.out.println(employees.get(2));

        Employee e4 = new Employee();
        e4.setFullname("Алексеев Дмитрий Иванович").setPosition("Ведущий советник").setPhoneNumber("8-909-214" +
                "-35-71").setSalary(41000).setAge(28).setSupervisor(false);
        employees.add(e4);
        System.out.println(employees.get(3));

        Employee e5 = new Employee();
        e5.setFullname("Алиманов Тахир Иванович").setPosition("Консультант").setPhoneNumber("8-905-114" +
                "-38-41").setSalary(34000).setAge(29).setSupervisor(false);
        employees.add(e5);
        System.out.println(employees.get(4));

        Supervisor sv1 = new Supervisor();
        sv1.setFullname("Петров Семен Александрович").setPosition("Руководитель").setPhoneNumber("8-905-114-13-23").setSalary(95000).setAge(49).setSupervisor(true);
        employees.add(sv1);

        increaseSalary(employees, 45, 5000);
        Employee.increaseSalary(employees, 45, 5000);
        Supervisor.increaseSalary(employees, 36, 10000);

        System.out.println(employees);
        System.out.println(average(employees));
    }

    public static void increaseSalary(ArrayList<Employee> employees, Integer age, Integer inc) {
        for (Employee employee : employees) {
            if (employee.getAge() > age) {
                employee.setSalary(employee.getSalary() + inc);
            }
        }
    }

    public static String average(ArrayList<Employee> employees) {
        Integer avgSalary = 0;
        Integer avgAge = 0;
        Integer N = employees.size();
        for (Employee employee : employees) {
            avgSalary += employee.getSalary();
            avgAge += employee.getAge();
        }
        return String.format("Средний возраст сотрудников: %d, средняя зарплата сотрудников: %d",
                avgAge/N, avgSalary/N);
    }
}
