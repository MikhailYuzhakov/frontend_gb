package ru.gb.task1;

import java.util.stream.IntStream;

/*
Напишите программу, которая использует Stream API для обработки списка чисел.
Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Main {
    public static void main(String[] args) {
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        double oddAverage = stream.filter(number -> number % 2 == 0).average().getAsDouble();
        System.out.println("Среднее арифметическое четных чисел = " + oddAverage);
    }
}
