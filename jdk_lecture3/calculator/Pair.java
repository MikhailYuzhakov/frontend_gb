package calculator;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    K getFirst() {
        return key;
    }

    V getSecond() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + getFirst().toString() + " : " + getSecond() + "]";
    }
}
