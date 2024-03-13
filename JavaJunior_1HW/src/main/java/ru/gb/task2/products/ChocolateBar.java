package ru.gb.task2.products;

import ru.gb.task2.commons.*;

/**
 * Шоколадный батончик
 */
public class ChocolateBar implements Snack{
    @Override
    public boolean getProteins() {
        return false;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public boolean getCarbohydrates() {
        return true;
    }

    @Override
    public String getName() {
        return "Шоколадный батончик";
    }
}