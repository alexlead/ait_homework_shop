package de.aittr.g_31_2_shop.repositories;

import de.aittr.g_31_2_shop.domain.CommonCart;
import de.aittr.g_31_2_shop.domain.CommonProduct;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static de.aittr.g_31_2_shop.repositories.DBConnector.getConnection;

public class CommonCartRepository {


    public static Cart createCart (int customerId) {

        try (Connection connection = getConnection()) {

            String query = String.format("INSERT INTO `cart` (`customer_id`) VALUES (%d);", customerId);
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet key = statement.getGeneratedKeys();
            key.next();

            int cartId = key.getInt(1);

            return (new CommonCart( cartId )) ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static Cart fillEmptyCart (Cart cart) {

        try (Connection connection = getConnection()) {

            String query = String.format("SELECT p.id AS id, p.name AS name, p.price AS price, COUNT(cp.product_id) AS qty " +
                    "FROM cart_product AS cp INNER JOIN product AS p " +
                    "ON cp.product_id = p.id " +
                    "WHERE cp.cart_id = %d AND p.is_active = 1 " +
                    "GROUP BY cp.product_id;",  cart.getId())  ;
            System.out.println( query );
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            while (resultSet.next()) {

                cart.addProduct( new CommonProduct(
                        resultSet.getInt("id"),
                        true,
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                ));
            }


            return cart ;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
