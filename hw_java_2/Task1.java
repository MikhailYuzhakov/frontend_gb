/*
Сортировка подсчетом через промежуточный массив
*/
public class Task1 {
    public Task1() {
        super();
    }

    public int[] countSort(int[] array) {
        int k = 0;
        int[] sortedArray = new int[array.length + 1];
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = 0;
        }

        for (int i = 0; i < array.length - 1; i++) {
            k = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i] < array[j]) {
                    k += 1;
                }
            }
            sortedArray[k] = array[i];
        }
        return sortedArray;
    }
}
