package de.aittr.g_31_2_shop.domain.dto;

import jakarta.persistence.Column;

import java.util.Objects;

public class CustomerDto {

    private int id;
    private String name;
    private int age;
    private String email;
    private CartDto cart;


    public CustomerDto(int id, String name, CartDto cart) {
        this.id = id;
        this.name = name;
        this.cart = cart;
    }

    public CustomerDto(int id, String name, int age, String email, CartDto cart) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CartDto getCart() {
        return cart;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cart);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }
}