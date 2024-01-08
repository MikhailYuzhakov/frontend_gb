/**
 * Generic Box
 */
public class GBox<T> {
    private T value;

    public GBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    /**
     * 1. Невозможно внутри метода обобщённого класса создать экземпляр
     * параметризующего класса T, потому что на этапе компиляции об этом классе ничего
     * не известно. Это ограничение возможно обойти, используя паттерн проектирования
     * абстрактная фабрика.
     * 2. Нельзя создавать внутри обобщения массив из обобщённого типа данных. Но
     * всегда можно подать такой массив снаружи.
     * 3. По причине отсутствия информации о параметризируещем классе, невозможно
     * создать статическое поле типа. Конкретный тип для параметра T становится
     * известен только при создании объекта обобщённого класса.
     * 4. Нельзя создать исключение обобщённого типа.
     */
    public void setValue(T value) {
        this.value = value;
//        !!value = 23;
//        ??GBox<Integer> intBox = new GBox<>(20);
    }

    public void showType() {
        System.out.printf("Type is %s, with value %s\n",
                value.getClass().getName(), getValue());
    }

    @Override
    public String toString() {
        return "GBox{" +
                "value=" + value +
                '}';
    }
}
