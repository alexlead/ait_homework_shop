package de.aittr.g_31_2_shop.repositories.jpa;

import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Integer> {



    @Transactional
    JpaProduct findByName(String name);

    @Query(value = "SELECT * FROM product WHERE is_active = 1", nativeQuery = true)
    List<JpaProduct> getAllActiveProducts();

    @Query(value = "SELECT * FROM product WHERE is_active = 1 AND id = :id", nativeQuery = true)
    JpaProduct getActiveProductById(@Param("id") int id);

    @Query(value = "UPDATE product SET is_active = 0 WHERE id = :id", nativeQuery = true)
    void deactivateProductById(@Param("id") int id);

    @Modifying
    @Query( value = "DELETE product WHERE name LIKE ':name'", nativeQuery = true)
    void deactivateProductByName(@Param("name") String name);
    @Query(value = "UPDATE product SET is_active = 1 WHERE id = :id", nativeQuery = true)
    void activateProductById(@Param("id") int id);

    @Query(value = "SELECT COUNT(*) FROM product WHERE is_active = 1", nativeQuery = true)
    int getActiveProductCount();

    @Query(value = "SELECT SUM(price) FROM product WHERE is_active = 1", nativeQuery = true)
    double getActiveProductsTotalPrice ();

    @Query(value = "SELECT AVG(price) FROM product WHERE is_active = 1", nativeQuery = true)
    double getActiveProductAveragePrice ();


}