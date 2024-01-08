package homework03;

abstract class Fruit {
    private final Float weight;
    Fruit(Float weight) {
        this.weight = weight;
    }

    Float getWeight() {
        return weight;
    }
}
