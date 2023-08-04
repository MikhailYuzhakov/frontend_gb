/*
Напишите два наследника класса Exception: ошибка преобразования строки
и ошибка преобразования столбца
разработайте исключения-наследники так, чтобы они информировали пользователя в формате
ожидание/реальность
напишите программу преобразующего массив 5х5 в сумму числе массива, программа должна выбросить
исключение если кол-во строк или столбцов окажется не 5
*/
public class Task1 {
    Integer sum = 0;
    public Task1() {}

    public int matrixSum(int[][] matrix) throws RowConversionException, ColumnConversionException {
        if (matrix[0].length != 5) {
            throw new RowConversionException(matrix[0].length);
        }
        if (matrix.length != 5) {
            throw new ColumnConversionException(matrix.length);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}

class RowConversionException extends Exception {
    int length;
    public RowConversionException(int length) {
        this.length = length;
    }

    @Override
    public void printStackTrace() {
        System.out.printf("Ожидалось, что количество столбцов в массиве будет 5, а оказалось %d", length);
    }
}

class ColumnConversionException extends Exception {
    int length;
    public ColumnConversionException(int length) {
        this.length = length;
    }

    @Override
    public void printStackTrace() {
        System.out.printf("Ожидалось, что количество строк в массиве будет 5, а оказалось %d", length);
    }
}

