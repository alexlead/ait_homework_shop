package de.aittr.g_31_2_shop.domain.jpa;

import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.exception_handling.exceptions.IncorrectEntityTypeException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "name")
    @Pattern(
            regexp = "[A-ZА-Я][a-zа-я]{2,}",
            message = "Имя должно начинаться с заглавной буквы и содержать ещё минимум 2 строчных буквы"
    )
    private String name;

    /*
    Домашнее задание 15
    1. Добавить покупателю два дополнительных поля - возраст и емейл (и в БД тоже).
    2. Провалидировать все поля покупателя.
     */
    @Column(name = "age")
    @Min(value = 12, message = "Возраст не должен быть менее 12")
    private int age;

    @Column(name = "email")
    @Email(message = "Некорректные значения email не допускаются")
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private JpaCart cart;

    public JpaCustomer() {
    }

    public JpaCustomer(int id, boolean isActive, String name, int age, String email, JpaCart cart) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.age = age;
        this.email = email;
        this.cart = cart;
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
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public JpaCart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        try {
            this.cart = (JpaCart) cart;
        } catch (ClassCastException e) {
            throw new IncorrectEntityTypeException("Incorrect type of cart was set to JpaCustomer.", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaCustomer customer = (JpaCustomer) o;
        return id == customer.id && isActive == customer.isActive && age == customer.age && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, name, age, email, cart);
    }

    @Override
    public String toString() {
        return "JpaCustomer{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", cart=" + cart +
                '}';
    }
}