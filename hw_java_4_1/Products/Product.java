package Products;

public abstract class Product {
    private final String name; //если необходимо изменить имя товара, то создаем новый объект
    private Integer price;

    protected Product(String name) {
        this.name = name;
    }

    protected Product(String name, Integer price) {
        this(name);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Наименование: %s\nЦена: %d\n", getName(), getPrice());
    }
}
