import java.util.ArrayList;

public class Presenter {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        // задание № 1
        System.out.println("\nЗадание № 1: Сортировка подсчётом");
        int[] newArray = task3.randomGen(10);
        System.out.println("Начальный массив: \n" + task3.printArray(newArray, 10));
        System.out.println("Отсортированный массив: \n" + task3.printArray(task1.countSort(newArray), newArray.length - 1));

        // задание № 2
        System.out.println("\nЗадание № 2: Шифр Цезаря");
        System.out.println(task2.crypting(task2.getPhrase(), task2.getKey(), task2.getDirection()));

        // задание № 3
        System.out.println("\nЗадание № 3: Сдвиг массива");
        int N = 15;
        int[] array = new int[N];
        array = task3.randomGen(N);
        System.out.println("Начальный массив: \n" + task3.printArray(array, array.length));
        System.out.println("Массив со сдвигом: \n" + task3.printArray(task3.moveArray(array, 1), array.length));
    }
}
