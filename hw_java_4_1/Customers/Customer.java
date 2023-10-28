package Customers;

public class Customer {
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer birthYear;
    private String phoneNumber;
    public Customer(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Customer(String lastName, String firstName, String middleName, Integer birthYear, String phoneNumber) {
        this(lastName, firstName, middleName);
        this.birthYear = birthYear;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Integer getAge() {
        return 2023 - birthYear;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return String.format("ФИО: %s %s %s\nВозраст: %d\nНомер телефона: %s\n", getLastName(), getFirstName(),
                getMiddleName(), getAge(), getPhoneNumber());
    }
}
