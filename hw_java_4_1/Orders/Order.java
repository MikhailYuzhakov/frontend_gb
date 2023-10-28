package Orders;

import Customers.Customer;
import Products.Product;
import com.sun.org.apache.xpath.internal.operations.Or;

public class Order {
    private Customer customer;
    private Product product;
    private Integer pcs;
    public Order() {}
    public Order(Customer customer, Product product, Integer pcs) {
        this.customer = customer;
        this.product = product;
        this.pcs = pcs;
    }

    public Order(Customer customer, Product product) {
        this(customer, product, 1);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getPcs() {
        return pcs;
    }

    public Product getProduct() {
        return product;
    }

    public Order setPcs(Integer pcs) {
        this.pcs = pcs;
        return this;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Order setProduct(Product product) {
        this.product = product;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Покупатель: %sТовар (x%d): \n%s", customer.toString(), getPcs(), product.toString());
    }
}
