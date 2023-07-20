import java.util.ArrayList;
import java.util.Random;

/*
Написать метод, принимающий на вход массив чисел и параметр n. Метод должен осуществить
циклический (последний элемент при сдвиге становится первым) сдвиг всех элементов массива на n позиций;
 */
public class Task3 {
    public Task3() {
        super();
    }

    public int[] randomGen(Integer n) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(-100,100);
        }
        return array;
    }

    public int[] moveArray(int[] array, Integer N) {
        int len = array.length;
        int cap;
        cap = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[len - 1] = cap;
        return array;
    }

    public String printArray(int[] arr, int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }
}
