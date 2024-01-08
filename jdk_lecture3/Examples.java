import common.Animal;
import common.Cat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Examples {
    /**
     * в дженерики можно класть, что угодно, но необходмио указать тип данных
     * в треугольных скобках
     */
    static void exmpl01() {
        GBox<String> stringGBox = new GBox<>("Hello");
        stringGBox.showType();
        GBox<Integer> integerGBox = new GBox<>(12);
        integerGBox.showType();
    }

    static void exmpl02() {
        KVBox<Integer, String> kvb0 = new KVBox<>(1, "Hello");
        kvb0.showType();
        KVBox<String, GBox<String>> kvb1 = new KVBox<>("World", new GBox<>("Java"));
        kvb1.showType();
    }

    /**
     * Сырой тип – это имя обобщённого класса или интерфейса без аргументов типа, то
     * есть это, фактически, написание идентификатора и вызов конструктора
     * обобщённого класса как обычного, без треугольных скобок. При использовании
     * сырых типов, программируется поведение, которое существовало до введения
     * обобщений в Java. Геттеры сырых типов возвращают объекты. Это логично, потому
     * что ни на одном из этапов не указан аргумент типа.
     */
    static void exmpl03() {
        GBox<Integer> intBox = new GBox<>(1);
        GBox box = intBox; //сырой тип обобщенного типа GBox, такие действия допустимы
        box.showType(); //можно исп. все методы параметризированного класса

        //здесь компилятор не имеет достаточного количества информации для обеспечения безопасности типов.
        GBox box1  = new GBox(1); //так будет работать, но все ошибки переносим в рантайм
        box1.showType();
        GBox<Integer> integerGBox = box1;
        integerGBox.showType();
    }

    /**
     * Обобщённые методы синтаксически схожи с обобщёнными классами, но параметры
     * типа относятся к методу, а не к классу. Допустимо делать обобщёнными
     * статические и не статические методы, а также конструкторы. Синтаксис
     * обобщённого метода включает параметры типа внутри угловых скобок, которые
     * указываются перед возвращаемым типом.
     */
    private static <T> void setIfNull(GBox<T> box, T t) {
        if (box.getValue() == null) {
            box.setValue(t);
        }
    }

    private static <T extends Number> void setIfNull(Box<T> box, T t) {
        if (box.getValue() == null) {
            box.setValue(t);
        }
    }

    static void exmpl04() {
        GBox<Integer> box = new GBox<>(null);
        setIfNull(box, 13);
        System.out.println(box.getValue());
        GBox<Integer> box1 = new GBox<>(2);
        setIfNull(box1, 15);
        System.out.println(box1.getValue());
    }

    static void exmpl05() {
        Box<Integer> iBox = new Box<>();
//        Box<String> sBox = new Box<>(); //будет ошибка при комплияции, потому что String не наследуется от Number

        setIfNull(iBox, 4);
//        setIfNull(sBox, "hello");
    }

    private static void copyTo(ArrayList src, ArrayList dst) {
        for (Object o : src) dst.add(o);
    }

    //Получается, что источником может быть список из заданного типа или его
    //наследников, а приёмником – тип или его родители
    private static <T> void newCopyTo(
            ArrayList<? extends T> src, ArrayList<? super T> dst) {
        for (T o : src) dst.add(o);
    }

    static void exmpl06() {
        //массив с более точным типом данных
        ArrayList<Integer> ial = new ArrayList<>(Arrays.asList(1, 2, 3));
        //массив с менее точным типом данных
        ArrayList<Number> nal = new ArrayList<>(Arrays.asList(1f, 2, 3.0));
        copyTo(ial, nal); //копируем из точного типа в более общий список числе
        System.out.println(nal);
        copyTo(nal, ial); //так уже не корректно, но работать будет
        System.out.println(ial);
    }

    static void exmpl07() {
        ArrayList<Cat> cats = new ArrayList<>(Arrays.asList(new Cat()));
        ArrayList<Animal> animals = new ArrayList<>(Arrays.asList(new Animal()));
        //        copyTo(animals, cats); //ошибка!
        newCopyTo(cats, animals); //такое поведение проходит без ошибок
        System.out.println(cats);
        //вот тут ошибка проявится только в рантайм
//        animals.get(1).voice(); //пробуем вызвать метод voice у Animal,но определенный только у Cat
    }

    /**
     * Входная переменная. Предоставляет данные для кода. Для метода copy(src, dst)
     * параметр src предоставляет данные для копирования, поэтому он считается
     * входной переменной. Выходная переменная. Содержит данные для использования
     * в другом месте. В примере copy(src, dst) параметр dst принимает данные и будет
     * считаться выходной переменной.
     * 1. Входная переменная определяется с ограничением сверху.
     * 2. Выходная переменная определяется с ограничением снизу.
     * 3. Если ко входной переменной можно обращаться только как к Object –
     * неограниченный подстановочный символ.
     * 4. Если переменная должна использоваться как входная и как выходная
     * одновременно, НЕ использовать подстановочный символ.
     * 5. Не использовать подстановочные символы в возвращаемых типах.
     */
}
