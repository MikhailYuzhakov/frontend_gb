package Homework;

public class Homework {
    public static void main(String[] args) {
        EmployeeList eList = addEmployees();
        System.out.println(eList.findByExperience(5));
        System.out.println(eList.findNumberByName("Алексей"));
        System.out.println(eList.findByNumber("1456"));
    }

    static EmployeeList addEmployees() {
        EmployeeList eList = new EmployeeList();
        eList.add(new Employee("3214", "Алексей", "123", 5));
        eList.add(new Employee("4213", "Вадим", "4223", 7));
        eList.add(new Employee("4564", "Ольга", "66541", 2));
        eList.add(new Employee("1456", "Иван", "12344", 10));
        eList.add(new Employee("2334", "Алексей", "7654", 12));
        eList.add(new Employee("6786", "Виктория", "23565", 5));
        return eList;
    }
}
