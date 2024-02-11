package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Integer> {

    JpaProduct findByName(String name);

    /*
    Домашнее задание 18
    2. Реализовать вывод в консоль последнего добавленного в БД товара.
    Вывод должен производиться в 15 и 45 секунд каждой минуты.
    Задача должна быть сохранена в БД.
    Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
    Пример значения поля description - "Последний добавленный в БД продукт - Банан".
     */

    @Query(value = "select * from product order by id desc limit 1;", nativeQuery = true)
    JpaProduct getLastProduct();
}