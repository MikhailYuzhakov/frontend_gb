package ru.gb.task2.products;

import ru.gb.task2.commons.*;

/**
 * Замороженные ягоды
 */
public class DumplingsBerries implements SemiFinishedFood{
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
        return "Замороженные ягоды";
    }
}
