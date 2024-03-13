package ru.gb.task2;

import ru.gb.task2.commons.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart <T extends Food>{

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market)
    {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs(){
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    /**
     * Балансировка корзины
     */
    public void cardBalancing()
    {
        boolean proteins = false;
        boolean fats = false;
        boolean carbohydrates = false;

        for (var food : foodstuffs)
        {
            if (!proteins && food.getProteins())
                proteins = true;
            else
            if (!fats && food.getFats())
                fats = true;
            else
            if (!carbohydrates && food.getCarbohydrates())
                carbohydrates = true;
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
        {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        for (var thing : market.getThings(Food.class))
        {
            if (!proteins && thing.getProteins())
            {
                proteins = true;
                foodstuffs.add((T)thing);
            }
            else if (!fats && thing.getFats())
            {
                fats = true;
                foodstuffs.add((T)thing);
            }
            else if (!carbohydrates && thing.getCarbohydrates())
            {
                carbohydrates = true;
                foodstuffs.add((T)thing);
            }
            if (proteins && fats && carbohydrates)
                break;
        }

        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }


    public void cardBalancingStream() {
        boolean[] proteins = {false};
        boolean[] fats = {false};
        boolean[] carbohydrates = {false};
        Random random = new Random();

        foodstuffs.stream().forEach(nutrient -> {
            proteins[0] |= nutrient.getProteins();
            fats[0] |= nutrient.getFats();
            carbohydrates[0] |= nutrient.getCarbohydrates();
        });

        List<Food> onlyCarbon = market.getThings(Food.class).stream().
                filter(nutrient -> nutrient.getCarbohydrates() && !carbohydrates[0]).toList();
        List<Food> onlyFats = market.getThings(Food.class).stream().
                filter(nutrient -> nutrient.getFats() && !fats[0]).toList();
        List<Food> onlyProteins = market.getThings(Food.class).stream().
                filter(nutrient -> nutrient.getProteins() && !proteins[0]).toList();

        int size = onlyCarbon.size();
        if (size != 0) foodstuffs.add((T) onlyCarbon.get(random.nextInt(size)));

        size = onlyFats.size();
        if (size != 0) foodstuffs.add((T) onlyFats.get(random.nextInt(size)));

        size = onlyProteins.size();
        if (size != 0) foodstuffs.add((T) onlyProteins.get(random.nextInt(size)));

        System.out.println("Корзина сбалансирована по БЖУ.");
    }
}
