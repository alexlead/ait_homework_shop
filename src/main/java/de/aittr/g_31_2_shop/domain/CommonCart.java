package de.aittr.g_31_2_shop.domain;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Product;

import java.util.ArrayList;
import java.util.List;

public class CommonCart implements Cart {

    private int id;
    private List<Product> products = new ArrayList<>();

    public CommonCart() {
    }

    public CommonCart(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void deleteProductById(int productId) {
        products.removeIf(p -> p.getId() == productId );
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public double getTotalPrice() {
        return products.stream()
                .filter(p -> p.isActive())
                .mapToDouble( p -> p.getPrice())
                .sum();
    }

    @Override
    public double getAveragePrice() {
        return products.stream()
                .filter(p -> p.isActive())
                .mapToDouble( p -> p.getPrice())
                .average()
                .orElse(0);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
