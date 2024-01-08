/**
 * Параметризированные классы поддеривают ограничения по возможным типам
 * (bounded type parameters). При необходимости использования класса только
 * для числе и строк, но запрета использования на этапе компиляции объктов котиков
 * или потоков ввода вывода.
 */
//extends - указывает верхнюю границу абстрактным классмо Number
public class Box<V extends Number> {
    private V value;

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
