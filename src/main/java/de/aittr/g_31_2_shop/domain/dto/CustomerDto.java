package de.aittr.g_31_2_shop.domain.dto;

import java.util.Objects;

public class CustomerDto {

    /*
    Домашнее задание 15
    1. Добавить покупателю два дополнительных поля - возраст и емейл (и в БД тоже).
    */
    private int id;
    private String name;
    private CartDto cart;
    private int age;
    private String email;

    public CustomerDto() {
    }

    public CustomerDto(int id, String name, CartDto cart, int age, String email) {
        this.id = id;
        this.name = name;
        this.cart = cart;
        this.age = age;
        this.email = email;
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
        return id == that.id && age == that.age && Objects.equals(name, that.name) && Objects.equals(cart, that.cart) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cart, age, email);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}