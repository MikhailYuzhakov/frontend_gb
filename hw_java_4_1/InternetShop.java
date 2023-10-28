import Customers.*;
import Orders.*;
import Products.*;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class InternetShop {
    static List<Customer> customers = new ArrayList<Customer>();
    static List<Product> products = new ArrayList<Product>();
    static List<Order> cart = new ArrayList<Order>(5);

    public static void main(String[]args) {
        /*
        2. Создать массив покупателей (инициализировать 2 элемента), массив товаров (инициализировать 5 элементов)
        и массив заказов (пустой на 5 элементов).
        */
        Customer customer1 = new Customer("Иванов", "Сергей", "Алексеевич",
                1987, "88805553535");
        Customer customer2 = new Customer("Васильев", "Иван", "Артемович",
                2003, "88815653535");
        customers.add(customer1);
        customers.add(customer2);

        Product milk1 = new Milk("Простоквашино", 93, "12.11.2023", 2.5f);
        Product milk2 = new Milk("Домик в деревне", 79, "10.11.2023", 3.2f);
        Product showerGel1 = new showerGel("Head & Shoulders", 399);
        Product showerGel2 = new showerGel("OldSpice", 449);
        Product juice1 = new someProduct("Добрый", 99);
        products.add(milk1);
        products.add(milk2);
        products.add(showerGel1);
        products.add(showerGel2);
        products.add(juice1);

        try {
            //5.1 если был передан неверный товар – вывести в консоль сообщение об ошибке не совершать данную покупку;
            cart.add(InternetShop.purchase("Васильев", "Томское", "2"));
            System.out.println(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5.2 если было передано неверное количество – купить товар в количестве 1;
        try {
            cart.add(InternetShop.purchase("Васильев", "Head & Shoulders", "2"));
            cart.add(InternetShop.purchase("Иванов", "Домик в деревне", "3"));
            cart.add(InternetShop.purchase("Васильев", "Простоквашино", "1"));
            cart.add(InternetShop.purchase("Васильев", "Добрый", "1"));
            cart.add(InternetShop.purchase("Иванов", "OldSpice", "6"));
        } catch (Exception e) {
            e.printStackTrace();
            try {
                cart.add(InternetShop.purchase("Иванов", "OldSpice", "1"));
                System.out.println(cart);
                //5.3 если был передан неверный пользователь – завершить работу приложения с исключением.
                cart.add(InternetShop.purchase("Сергеев", "Добрый", "1"));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        while (true); //для проверки остановится ли программа средствами RuntimeException
    }

    /*
    3. Создать статический метод «совершить покупку» со строковыми параметрами, соответствующими полям объекта
    заказа. Метод должен вернуть объект заказа.
    */
    public static Order purchase(String customerLastName, String productName, String pcs) throws Exception {
        Order order = new Order();
        //ищем покупателя по фамилии, товар по наименованию, и заполняем поля заказа
        for (Customer customer : customers) {
            if (customerLastName.equals(customer.getLastName())) {
                order.setCustomer(customer);
            }
        }
        if (order.getCustomer() == null) {
            throw new CustomerException(String.format("Указанного покупателя (%s) не существует", customerLastName));
        }

        for (Product product : products) {
            if (productName.equals(product.getName())) {
                order.setProduct(product);
            }
        }
        if (order.getProduct() == null) {
            throw new ProductException(String.format("Указанного товара (%s) не существует.", productName));
        }

        if (pcs != null && Integer.parseInt(pcs) <= 5) {
            order.setPcs(Integer.parseInt(pcs));
        } else {
            throw new AmountException(String.format("Укажите кол-во товара менее 5 ед. (=%s)", pcs));
        }
        return order;
    }
}


