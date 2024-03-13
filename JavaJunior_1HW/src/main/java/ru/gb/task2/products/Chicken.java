package ru.gb.task2.products;

import ru.gb.task2.commons.*;

/**
 * Курица
 */
public class Chicken implements HealthyFood{
    @Override
    public boolean getProteins() {
        return true;
    }

    @Override
    public boolean getFats() {
        return false;
    }

    @Override
    public boolean getCarbohydrates() {
        return false;
    }

    @Override
    public String getName() {
        return "Курица";
    }
}

