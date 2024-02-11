package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class JpaCart implements Cart {

    /*
    Домашнее задание 17
    1. Применить логирование в энтити-классах, залогировать вызов конструкторов, методов (без применения АОП).
    Добавил только пару примеров.
     */
    private static Logger logger = LoggerFactory.getLogger(JpaCart.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<JpaProduct> products = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "customer_id")
    private JpaCustomer customer;

    public JpaCart() {
        logger.info("Cart without id specification created");
    }

    public JpaCart(int id, List<JpaProduct> products) {
        logger.info("Cart with id [{}] created", id);
        this.id = id;
        this.products = products;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Product> getProducts() {
        // TODO посмотреть, как будет на практике, потом переделать
        return new ArrayList<>(products);
    }

    @Override
    public void addProduct(Product product) {
        try {
            products.add((JpaProduct) product);
            logger.info("Product id [{}] added to cart id [{}]",
                    product.getId(), id);
        } catch (Exception e) {
            throw new IllegalArgumentException("В корзину JpaCart помещён несовместимый тип продукта!");
        }
    }

    @Override
    public void deleteProductById(int productId) {
        // TODO проверить работу на практике и при необходимости переделать
        products.removeIf(p -> p.getId() == productId);
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public double getTotalPrice() {
        return products
                .stream()
                .filter(p -> p.isActive())
                .mapToDouble(p -> p.getPrice())// [Apple:90, Banana:120, Orange:200] -> [90, 120, 200]
                .sum();
    }

    @Override
    public double getAveragePrice() {
        return products
                .stream()
                .filter(p -> p.isActive())
                .mapToDouble(p -> p.getPrice())// [Apple:90, Banana:120, Orange:200] -> [90, 120, 200]
                .average()
                .orElse(0);
    }

    public void setProducts(List<JpaProduct> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaCart jpaCart = (JpaCart) o;
        return id == jpaCart.id && Objects.equals(products, jpaCart.products) && Objects.equals(customer, jpaCart.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products, customer);
    }

    @Override
    public String toString() {
        return "JpaCart{" +
                "id=" + id +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}