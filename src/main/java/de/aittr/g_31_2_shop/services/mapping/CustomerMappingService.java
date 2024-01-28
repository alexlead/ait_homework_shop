package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCustomer;
import de.aittr.g_31_2_shop.domain.jpa.JpaCustomer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMappingService {

    public CustomerDto mapEntityToCustomerDto(Customer customer) {
        int id = customer.getId();
        String name = customer.getName();
        Cart cart = customer.getCart();
        return new CustomerDto(id, name, cart);
    }

    public CommonCustomer mapDtoToCommonCustomer(CustomerDto dto) {
        int id = dto.getId();
        String name = dto.getName();
        Cart cart = dto.getCart();
        return new CommonCustomer(id, true, name, cart);
    }

    public JpaCustomer mapDtoToJpaCustomer(CustomerDto dto) {
        int id = dto.getId();
        String name = dto.getName();
        Cart cart = dto.getCart();
        return new JpaCustomer(id, name, true, cart);
    }

}
