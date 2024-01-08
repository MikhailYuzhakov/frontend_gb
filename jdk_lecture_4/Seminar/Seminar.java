package Seminar;

import java.util.*;

public class Seminar {
    public static void main(String[] args) {
//        task01();
//        task02();
        task03();
    }

    /**
     * В рамках выполнения задачи необходимо:
     * 1. Создайте коллекцию мужских и женских имен с помощью интерфейса List
     * 2. Отсортируйте коллекцию в алфавитном порядке
     * 3. Отсортируйте коллекцию по количеству букв в слове
     * 4. Разверните коллекцию
     */
    static void task01() {
        List<String> list = generateList();
        System.out.println(list);
//        sortByAlphabet(list);
        sortByLength(list);
        System.out.println(list);
        Collections.reverse(list); //O(n)
        System.out.println(list);
    }

    /**
     * 1. Создать коллекцию мужских и женских имен через List с повторами.
     * 2. Получаить уникальный список Set на основании List.
     * 3. Определить наименьшеий элемент (алфавит).
     * 4. Определить наибольший элемент (по кол-ву букв в слове, но в обратном порядке).
     * 5. Удалить все элементы содержащие букву 'A'
     */
    static void task02() {
        List<String> list = generateList();
        System.out.println(list);
        Set<String> uniqueList = new HashSet<>(list);
        System.out.println(uniqueList);
        System.out.println(Collections.min(uniqueList) + " or " + getMinByAlphabet(uniqueList));
        System.out.println(Collections.max(uniqueList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }) + " or " + getMaxByLength(uniqueList));

        removeByChar(uniqueList, "О");
        System.out.println(uniqueList);
    }

    /**
     * 1. Создать телефонный справочник
     * 2. Найти человека с самым маленьким номером телефона
     * 3. Найдите номер телефона человека чье имя самое большое в алф. порядке.
     */
    static void task03() {
        Map<String, String> phoneNumbers = generateMap();
        System.out.println(phoneNumbers);
        System.out.println(getMinPhoneNumber(phoneNumbers));
        System.out.println(getPhoneByMaxName(phoneNumbers));
    }

    static String getMinPhoneNumber(Map<String, String> map) {
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        return entrySet.stream().min((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get().getKey();
    }

    static String getPhoneByMaxName(Map<String, String> map) {
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        return entrySet.stream().max((e1, e2) -> e1.getValue().compareTo(e2.getValue())).get().getValue();
    }

    private static String getMaxByLength(Set<String> set) {
        return set.stream().max(Comparator.comparingInt(String::length)).get();
    }

    /**
     * Удаляет если встречает символ в слове.
     * @param list
     * @param c
     */
    private static void removeByChar(Set<String> list, String c) {
        list.removeIf(s -> s.contains(c));
    }

    private static String getMinByAlphabet(Set<String> list) {
        TreeSet<String> treeSet = new TreeSet<>(list);
        return treeSet.iterator().next();
    }

    /**
     * Сортирует в обратном порядке
     * @param list
     */
    private static void sortByAlphabet(List<String> list) {
        list.sort(new Comparator<String>() { //анонимный класс
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
    }

    private static void sortByLength(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
//                return o1.length() - o2.length();
            }
        });
    }

    static List<String> generateList() {
        List<String> list = new ArrayList<>();
        list.add("Константин");
        list.add("Константин");
        list.add("Василий");
        list.add("Анна");
        list.add("Анна");
        list.add("Тимофей");
        list.add("Оксана");
        list.add("Оксана");
        list.add("Ксения");
        return list;
    }

    static Map<String, String> generateMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Алексей", "8-999-632-21-12");
        map.put("Андрей", "8-939-732-21-31");
        map.put("Иван", "8-919-622-29-52");
        map.put("Денис", "8-913-212-25-92");
        map.put("Олеся", "8-909-362-31-22");
        map.put("Яков", "8-983-922-42-13");
        return map;
    }
}
