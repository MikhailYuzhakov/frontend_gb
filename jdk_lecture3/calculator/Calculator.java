package calculator;

import java.util.ArrayList;

public class Calculator {
    public static <V extends Number> double sum(V number1, V number2) {
        return number1.doubleValue() + number2.doubleValue();
    }

    public static <V extends Number> double multiply(V number1, V number2) {
        return number1.doubleValue() * number2.doubleValue();
    }

    public static <V extends Number> double divide(V number1, V number2) {
        if (number2.equals(0))
            throw new ArithmeticException("Делить на ноль нельзя");
        return number1.doubleValue() / number2.doubleValue();
    }

    public static <V extends Number> double substract(V number1, V number2) {
        return number1.doubleValue() - number2.doubleValue();
    }

    public static <T> Boolean compare(
            ArrayList<T> array1, ArrayList<T> array2)
    {
        return array1.size() == array2.size();
    }
}
