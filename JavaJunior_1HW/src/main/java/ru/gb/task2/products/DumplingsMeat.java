package ru.gb.task2.products;

import ru.gb.task2.commons.*;

/**
 * Пельмени
 */
public class DumplingsMeat implements SemiFinishedFood{
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
        return "Пельмени";
    }
}

