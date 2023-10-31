import java.util.ArrayList;
import java.util.List;

public class TicTacToe {
    static char[] gameField = new char[] {  'O', '.', 'X',
                                            '.', '?', 'X',
                                            'O', '.', 'X' };

    public static void saveAndLoadGameField() {
        GameField field1 = new GameField(gameField); //создаем новое игровое поле
        System.out.println("Исходное игровое поле: ");
        field1.printGameField(); //печатаем игровое поле
//        field1.printGameFieldConditions(); //печатаем поля состояний игрового поля
        field1.exportGameFieldCondition(); //сохраняем игровое поле одним числом int в файл game_field.txt
        System.out.println("Игровое поле после сохранения и загрузки: ");
        field1.importGameFieldCondition(); //загружаем игровое поле
//        field1.printGameFieldConditions();  //печатаем поля состояний игрового поля
        field1.printGameField(); //печатаем игровое поле загруженное из файла game_field.txt
    }
}
