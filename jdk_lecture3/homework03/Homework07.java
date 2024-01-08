package homework03;

import calculator.Calculator;
import calculator.Pair;

import java.util.ArrayList;
import java.util.Arrays;

public class Homework07 {
    public static void main(String[] args) {
//        homeworkFromLecture();
        homeworkFromSeminar();
    }

    static FruitBox<Apple> createAppleBox() {
        FruitBox<Apple> appleBox = new FruitBox<>();
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        appleBox.put(new Apple());
//        System.out.println(appleBox.getWeight());
        return appleBox;
    }

    static FruitBox<Orange> createOrangeBox() {
        FruitBox<Orange> orangeBox = new FruitBox<>();
        orangeBox.put(new Orange());
        orangeBox.put(new Orange());
//        System.out.println(orangeBox.getWeight());
        return orangeBox;
    }

    static void homeworkFromLecture() {
        FruitBox<Apple> appleBox = createAppleBox();
        FruitBox<Apple> appleBox2 = createAppleBox();
        FruitBox<Orange> orangeBox = createOrangeBox();
        FruitBox<Orange> orangeBox2 = createOrangeBox();
        System.out.println(appleBox.compare(orangeBox));

        appleBox.dropTo(appleBox2);
        orangeBox.dropTo(orangeBox2);
    }

    static void homeworkFromSeminar() {
//        task01();
//        task02();
        task03();
    }

    private static void task01() {
        System.out.println(Calculator.divide(6, 4.0f));
        System.out.println(Calculator.divide(6, 4));
        System.out.println(Calculator.divide(6, 4.0));
    }

    private static void task02() {
        ArrayList<Integer> iArray = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> iArray2 = new ArrayList<>(Arrays.asList(1, 2, 3, 5));
        ArrayList<Float> fArray = new ArrayList<>(Arrays.asList(1.0f, 2.0f, 3.0f));
        System.out.println(Calculator.compare(iArray, iArray2));
        System.out.println(Calculator.compare(fArray, fArray));
    }

    private static void task03() {
        Pair<String, Integer> myPair = new Pair<>("first", 1);
        System.out.println(myPair);
    }
}
