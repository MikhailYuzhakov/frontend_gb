package ru.gb;

public class Door {

    //region Методы
    private String getSurprice() {
        return surprice;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String open() {
        this.isOpen = true;
        return getSurprice();
    }

    public void pick() {
        isPicked = true;
    }

    public void unpick() {
        isPicked = false;
    }

    public String viewSurprice() {
        return surprice;
    }

    public boolean isPicked() {
        return isPicked;
    }

    //endregion

    //region Конструкторы
    public Door(String surprice) {
        this.surprice = surprice;
    }
    //endregion

    //region Поля
    private final String surprice;
    private boolean isOpen = false;
    private boolean isPicked = false;
    //endregion
}
