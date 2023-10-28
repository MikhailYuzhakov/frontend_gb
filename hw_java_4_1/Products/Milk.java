package Products;

public class Milk extends Product {
    private String expirationDate;
    private Float fatContent;
    public Milk(String name) {
        super(name);
    }
    public Milk(String name, Integer price, String expirationDate, Float fatContent) {
        super(name, price);
        this.expirationDate = expirationDate;
        this.fatContent = fatContent;
    }

    //Реализовать работу с пакетом CalendarDate
    public String getExpirationDate() {
        return expirationDate;
    }

    public Float getFatContent() {
        return fatContent;
    }

    @Override
    public String toString() {
        return String.format("Наименование: %s\nЦена: %d\nЖирность: %.1f\nСрок годности: %s\n", getName(),
                getPrice(), getFatContent(), getExpirationDate());
    }
}
