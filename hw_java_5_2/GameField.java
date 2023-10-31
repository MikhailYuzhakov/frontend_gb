import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

//0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом, 3 - резервное значение
//храним в 3-х блоках по 9 бит информацию об игровом поле
//старшие 9 бит ([27-18]) в int - это места где стоит X (крестик): 1 - в поле крестик, 0 - в поле не крестик
//следующие 9 бит ([17-9]) в int - это места где стоит 0 (нолик): 1 - в поле нолик, 0 - в поле не нолик
//[8-0] в int - это места где стоит ? (резервеное значение): 1 - в поле рез. знач, 0 - в поле не рез. знач
//все оставшиеся незаполненными поля являются пустыми
public class GameField {
    private int[] gameFieldCondition = new int[9]; //для хранения полей состояний игрового поля
    private char[] gameField; //для хранения игрового поля в символьном представлении
    public GameField(char[] gameField) {
        this.gameField = gameField;
        //преобразуем игровое поле в поля состояний и записываем в соответствующее поле класса GameField
        convertCharToCondition();
    }

    //выводит игровое поле
    public void printGameField() {
        for (int i = 0; i < gameField.length; i++) {
            System.out.print(gameField[i] + " ");
            if ((i + 1) % 3 == 0) System.out.println();
        }
    }

    //выводит поля состояний игрового поля
    public void printGameFieldConditions() {
        for (int i = 0; i < gameFieldCondition.length; i++) {
            System.out.print(gameFieldCondition[i] + " ");
            if ((i + 1) % 3 == 0) System.out.println();
        }
    }

    //преобразует поля состояний в игровое поле
    private void convertConditionToChar() {
        for (int i = 0; i < gameFieldCondition.length; i++) {
            switch (gameFieldCondition[i]) {
                case 0 -> this.gameField[i] = '.';
                case 1 -> this.gameFieldCondition[i] = 'X';
                case 2 -> this.gameFieldCondition[i] = 'O';
                default -> this.gameFieldCondition[i] = '?'; //если попалось любое другое значение, то ставим резервное
            }
        }
    }

    //преобразует игровое поле в поле состояний
    private void convertCharToCondition() {
        for (int i = 0; i < gameField.length; i++) {
            switch (gameField[i]) {
                case '.' -> this.gameFieldCondition[i] = 0;
                case 'X' -> this.gameFieldCondition[i] = 1;
                case 'O' -> this.gameFieldCondition[i] = 2;
                default -> this.gameFieldCondition[i] = 3; //если попалось любое другое значение, то ставим резервное
            }
        }
    }

    //загружаем сохраненное игровое поле
    public void importGameFieldCondition() {
        Arrays.fill(gameFieldCondition, 0); //очистим старое игровое поле
        int gameFieldCompressed = readFromFile(); //прочитаем поле из файла
        convertIntToArray(gameFieldCompressed); //преобразуем поле из int в int[]
        reverseArray(); //развернем поле состояний
        convertConditionToChar(); //преобразуем поля состояний в игровое поле
    }

    //сохраняем текущее игровое поле
    public void exportGameFieldCondition() {
        //сжимаем массив из 9 состояний игрового поля в одно числа типа int
        int gameFieldCompressed = convertArrayToInt();
        //записываем в файл
        writeToFile(gameFieldCompressed);
    }

    //преобразуем поля состояний игрового поля в целочисленное число типа int
    private int convertArrayToInt() {
        /*
        пробегаем по всем полям сотояния и переносим в 3 блока по 9 бит значения ноликов, крестиков и резерва
        то, что осталось нулями в 3 блоках внутри int - пустые поля
         */
        int gameFieldCompressed = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                switch (i) {
                    case 0 -> {
                        //записываем только крестики
                        gameFieldCompressed <<= 1;
                        if (gameFieldCondition[j] == 1) {
                            gameFieldCompressed += 1;
                        }
                    }
                    case 1 -> {
                        //записываем только нолики
                        gameFieldCompressed <<= 1;
                        if (gameFieldCondition[j] == 2) {
                            gameFieldCompressed += 1;
                        }
                    }
                    case 2 -> {
                        //записываем только резервные значения
                        gameFieldCompressed <<= 1;
                        if (gameFieldCondition[j] == 3) {
                            gameFieldCompressed += 1;
                        }
                    }
                }
            }
        }
        return gameFieldCompressed;
    }

    //преобразуем целочисленное число типа int в поля состояний игрового поля
    private void convertIntToArray(int gameFieldCompressed) {
        /*
        пробегаем по всему числу и проверяем первый бит, записываем в поля состояний и сдвигаем вправо число
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                switch (i) {
                    case 0 -> {
                        //считываем только резервные значения
                        if ((gameFieldCompressed & 1) == 1) { //если младщий бит равен 1
                            gameFieldCondition[j] = 3; //то в j-ой ячейке поля хранится резервное значение
                        }
                        gameFieldCompressed >>= 1;
                    }
                    case 1 -> {
                        //считываем только нолики
                        if ((gameFieldCompressed & 1) == 1) { //если младщий бит равен 1
                            gameFieldCondition[j] = 2; //то в j-ой ячейке поля хранится резервное значение
                        }
                        gameFieldCompressed >>= 1;
                    }
                    case 2 -> {
                        //считываем только крестики
                        if ((gameFieldCompressed & 1) == 1) { //если младщий бит равен 1
                            gameFieldCondition[j] = 1; //то в j-ой ячейке поля хранится резервное значение
                        }
                        gameFieldCompressed >>= 1;
                    }
                }
            }
        }
    }

    //запись в файл
    private void writeToFile(int gameFieldCompressed) {
        try {
            Path file = Paths.get("game_field.txt");
            if (!Files.exists(file)) Files.createFile(file);
            Files.writeString(file, String.valueOf(gameFieldCompressed), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //чтение из файла
    private int readFromFile() {
        Path file = Paths.get("game_field.txt");
        try {
            return Integer.parseInt(Files.readString(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //развернуть массив (необходимо после импорта)
    private void reverseArray() {
        for(int i = 0; i < gameFieldCondition.length / 2; i++)
        {
            int temp = gameFieldCondition[i];
            gameFieldCondition[i] = gameFieldCondition[gameFieldCondition.length - i - 1];
            gameFieldCondition[gameFieldCondition.length - i - 1] = temp;
        }
    }
}
