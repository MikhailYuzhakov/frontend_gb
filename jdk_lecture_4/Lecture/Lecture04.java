package Lecture;

import java.util.*;

/**
 * Интерфейс List - для организации хранения "обычных списков"
 * Интерфейс Set - для хранения уникальных объектов
 * Интерфейс Queue - для реализации задачи FIFO LIFO
 * Интерфейс Mao - для реализации ассоциативного массива
 */
public class Lecture04 {
    public static void main(String[] args) {
//        example01();
//        example02();
//        example03();
//        example04();
//        example05();
//        example06();
//        example07();
    }

    /**
     * Обход коллекции с помощью объекта iterator. Ниже приведены примеры внешнего итерирования (получаем объект и
     * взаимодействуем с ним). Внутреннее итерирование - stream API.
     */
    static void example01() {
        Collection<String> colors = List.of("Белый", "Черный", "Краный","Зеленый","Синий");
        Iterator<String> iterator = colors.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //foreach тоже использует объект iterator
        for (String color : colors) {
            System.out.printf("%s ", color);
        }
    }

    /**
     * ArrayList - обычный массив обернутый в интерфейс, но эта реализация динамических массивов.
     */
    static void example02() {
        //пустой констурктор - начальная емкость 10
        ArrayList<String> list = new ArrayList<>();

        //можно передать в конструктор другую коллекцию
        ArrayList<String> listFromCollection = new ArrayList<>(list);

        //в качестве параметра выступает размер внутреннего массива
        ArrayList<String> listWithCapacity = new ArrayList<>(1000);
    }

    /**
     * LinkedList - реализует интерфейсы List и Deque. Содержит в каждом элементе ссылки на пред. и след. элементы
     * Вставка элемента O(1) - в некоторых случаях работает вставка в середину быстрее чем в ArrayList
     * Поиск O(n)
     */
    static void example03() {
        LinkedList<String> linkedList = new LinkedList<>();
        //создает список в который добавляет все элементы другой
        LinkedList<String> linkedListFromCollection = new LinkedList<>(linkedList);
    }

    /**
     * В качестве ключа используется хэш.
     * И так HashMap — структура из пар «ключ-значение» — или динамический массив
     * ключей. Каждый элемент массива — это bucket (корзина), в каждом из которых
     * хранится связанные списки со значением Map.Entry (если корзина пустая то
     * связанный список будет состоять из одного элемента, а ссылаться этот элемент
     * будет на null). Количество корзин может меняться со временем (по умолчанию их 16
     * и каждый раз их количество увеличивается в два раза).
     * У него есть два метода, которые надо переопределять - hashCode() и equals()
     */
    static void example04() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);

        for (Map.Entry<String, Integer> entry: hashMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    /**
     * Set гарантирует уникальность элементов.
     * HashSet - это частная реализация интерфейса HashMap — вместо значения value
     * используется константа.
     */
    static void example05() {
        //емкость по-умолчанию - 16, коэф. загрузки - 0.75 (кол-во занятых корзин/кол-во общее корзин)
        HashSet defaultConstructor = new HashSet();
        HashSet constructorWithCapacity = new HashSet(32);
        HashSet constructorWithCapacityFactor = new HashSet(32, 0.6f);
        HashSet fromCollections = new HashSet(defaultConstructor);
    }

    /**
     * SortedSet - отсортированные и уникальные элементы
     * NavigableSet - униакльный элементы и позволяет извлекать элементы по их значению
     * TreeSet - структура в виде дерева в отсортированном по возрастанию виде
     */
    static void example06() {
        TreeSet defaultConstructor = new TreeSet();

        //TreeSet в котором элементы будут отсортированы переопределнным компаратором (или по вовзрастанию)
        TreeSet withComporator = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }

    /**
     * PriorityQueue - обычная однонаправленная очередь FIFO, но с приоритетом.
     * Приоритет в очереди можно задать компаратором, по-умолчанию он естественный (по алфавиту, по возрастанию и тд)
     */
    static void example07() {
        PriorityQueue<Integer> integerPriorityQueueWithComparator =
                new PriorityQueue<>((Integer c1, Integer c2) -> c2 - c1);

        integerPriorityQueueWithComparator.add(3);
        integerPriorityQueueWithComparator.add(2);
        integerPriorityQueueWithComparator.add(1);

        System.out.println(integerPriorityQueueWithComparator.poll());
        System.out.println(integerPriorityQueueWithComparator.poll());
        System.out.println(integerPriorityQueueWithComparator.poll());
    }
}
