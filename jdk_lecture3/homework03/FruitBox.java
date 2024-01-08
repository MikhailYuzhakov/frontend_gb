package homework03;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

class FruitBox<V extends Fruit> {
    private ArrayList<V> content;

    FruitBox() {
        content = new ArrayList<>();
    }

    void put(V fruit) {
        content.add(fruit);
    }

    Float getWeight() {
        Float boxWeight = 0.0f;
        for (V fruit : content)
            boxWeight += fruit.getWeight();
        return boxWeight;
    }

    Boolean compare(FruitBox<?> fruitBox) {
        return Objects.equals(this.getWeight(), fruitBox.getWeight());
    }

    void dropTo(FruitBox<V> fruitBox) {
        for (V fruit : content) {
            fruitBox.put(fruit);
        }
        content.clear();
    }
}
