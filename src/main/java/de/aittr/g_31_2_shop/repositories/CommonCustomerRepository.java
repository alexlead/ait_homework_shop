package de.aittr.g_31_2_shop.repositories;

import de.aittr.g_31_2_shop.domain.CommonCart;
import de.aittr.g_31_2_shop.domain.CommonCustomer;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.repositories.interfaces.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static de.aittr.g_31_2_shop.repositories.DBConnector.getConnection;

@Repository
public class CommonCustomerRepository implements CustomerRepository {

    @Override
    public Customer save(Customer customer) {
        try (Connection connection = getConnection()) {
            String query = String.format( "INSERT INTO `customer` (`name`, `is_active`) VALUES ('%s', '1');", customer.getName());

            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            ResultSet key = statement.getGeneratedKeys();
            key.next();
                customer.setId(key.getInt(1));

            Cart customerCart = CommonCartRepository.createCart( customer.getId());

            customer.setCart(customerCart);

            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {
        try (Connection connection = getConnection()) {

            String query = "SELECT cus.id AS id, cus.name AS name, cart.id AS cartId FROM customer AS cus INNER JOIN cart AS cart ON cus.id = cart.customer_id WHERE cus.is_active = 1;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            List<Customer> customers = new ArrayList<>();

            while (resultSet.next()) {
                Cart cutomerCart = CommonCartRepository.fillEmptyCart( new CommonCart( resultSet.getInt("cartId")) );

                customers.add(new CommonCustomer(
                        resultSet.getInt("id"),
                        true,
                        resultSet.getString("name"),
                        cutomerCart
                ));
            }

            return customers;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void deleteById(int id) {

    }
}
