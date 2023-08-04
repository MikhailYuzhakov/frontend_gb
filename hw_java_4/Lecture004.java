import org.w3c.dom.ls.LSOutput;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Lecture004 {
    public Lecture004() {

    }
    /*
    Исключения в Java:
    Перечисления - уопминание объектов объединенных по какому-либо признаку enum.
    enum Season {WINTER, SPRING, SUMMER, AUTUMN} - статические объекты
    values() - достает все значения из enum
    ordinal() - возрвщает поорядковый номер
     */
    public void enums() {
        enum Season {WINTER, SPRING, SUMMER, AUTUMN};
        System.out.println(Season.AUTUMN);
        Season[] seasons = Season.values();
        for (Season s : seasons) {
            System.out.printf("%s ", s);
            System.out.println(s.ordinal());
        }
    }

    public void enums1() {

    }
}
    /*
    Вложенные классы (Nested Classes)
        - Inner Class
            - Non-Static Inner Class (внутренний класс в другом классе)
                * внутренний объект не существует без внешнего
                * внутренний имеет доступ ко всему внешнему
                * внешний не имеет доступа ко внутреннему без создания объекта
                * у внутренних классов есть модификаторы доступа
                * внутренний класс не может называться как внешний
                * во внутреннем классе нельзя иметь не-final статические поля
                * объект внутреннго класса нельзя создать в статическом методе "внешнего" класса
                * со внутренними классами работает наследование и полиморфизм
            - Method-local Inner Class (класс внутри методов)
                * сохраняет доступ ко всем полям и методам внешнего класс
                * должен иметь свои внутренние копии всех локальных переменных
                * имеют ссылку на окружающий экземпляр
            - Anonymous Inner Class
        - Static Nested Classes
            * объект статического класса не хранит ссылка на окружающий его класс
            * может существовать отдельно от окружающего
    */
// пример статического вложенного класса
// у нас открыто окно, мы слышим звук, но не знаем кто его точно издает
// то есть экземпляр класса кот не создан, но Voice мы можем слышать
class Cat {
    private String name, color;

    public Cat() {}

    static class Voice {
        public Voice() {}

        public void sayMur() {
            System.out.println("Murr");
        }
    }
}

    /*
        Создание внутренних классов внутри классов структуру эту называют композицией.
        Целесообразно создавать внутренний класс, если он будет иметь отношение к основному классу.

        Исключения
        Исключение - это отступление от общего правила, несоответствие обычному порядку вещей.
        Все наследуются от класса Throwable
     */
class MyException {
    public MyException() {}

    // простой вариант, можно создать класс исключения
    public int div0 (int a, int b) {
        if (b == 0) {
            throw new RuntimeException("parameter error");
        } else {
            return a / b;
        }
    }

    // при использовании throw исключения буду пробрасываться по стеку вызовов
    // до ближайщего обработчика
    public void methodA() {
        RuntimeException e = new RuntimeException();
        throw e;
    }
    // пробуем ловить исключения
    //  нужно писать как можно более узкий обработчик исключения
    // все методы которые не являются наследниками RuntimeException
    // необходимо обязательно обработать
    public void methodB() {
        try {
            throw new IOException();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class TestStream implements Closeable {
    TestStream() throws IOException {
        System.out.println("construct OK");
//        throw new IOException();
    }

    int read() throws FileNotFoundException {
        new FileInputStream("file.txt");
        System.out.println("read OK");
        return 1;
    }

    public void close() throws IOException {
        System.out.println("close OK");
        throw new IOException();
    }
}



