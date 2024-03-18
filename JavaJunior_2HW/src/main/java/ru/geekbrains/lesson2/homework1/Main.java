package ru.geekbrains.lesson2.homework1;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Object> animals = new ArrayList<>();
        Class<?> dog = Class.forName("ru.geekbrains.lesson2.homework1.Dog");
        Class<?> cat = Class.forName("ru.geekbrains.lesson2.homework1.Cat");
        Constructor<?>[] constructorDog = dog.getConstructors();
        Constructor<?>[] constructorCat = cat.getConstructors();

        animals.add(constructorDog[0].newInstance("Bobik", 12));
        animals.add(constructorDog[0].newInstance("Sharik", 9));
        animals.add(constructorDog[0].newInstance("Dima", 1));
        animals.add(constructorDog[0].newInstance("Ruslan", 6));
        animals.add(constructorCat[0].newInstance("Vasya", 2));
        animals.add(constructorCat[0].newInstance("Tima", 9));
        animals.add(constructorCat[0].newInstance("Misha", 13));
        System.out.println(animals);

        for (Object obj : animals) {
            Method method = obj.getClass().getMethod("voice");
            method.setAccessible(true);
            method.invoke(obj);
        }
    }
}
