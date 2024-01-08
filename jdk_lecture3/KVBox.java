/**
 * Ограничений на количество параметризированных типов не накладывается. Часто
 * можно встретить обобщения с двумя типами, например, в коллекциях, хранящих
 * пары ключ-значение. Также, нет ограничений на использование типов внутри
 * угловых скобок.
 */
public class KVBox<K, V> {
    private K key;
    private V value;

    public KVBox(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void showType() {
        System.out.printf("""
                        Type of key is %s, key %s
                        Type of value is %s, value %s
                        """,
                key.getClass().getName(), getKey(),
                value.getClass().getName(), getValue());
    }
}

