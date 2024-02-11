package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.dto.CartDto;
import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.domain.interfaces.Cart;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.jdbc.CommonCustomer;
import de.aittr.g_31_2_shop.domain.jpa.JpaCustomer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMappingService {

    private CartMappingService cartMappingService;

    public CustomerMappingService(CartMappingService cartMappingService) {
        this.cartMappingService = cartMappingService;
    }

    /*
    Домашнее задание 15
    1. Добавить покупателю два дополнительных поля - возраст и емейл (и в БД тоже).
     */
    public CustomerDto mapCustomerEntityToDto(Customer customer) {
        int id = customer.getId();
        String name = customer.getName();
        CartDto cartDto = cartMappingService.mapCartEntityToDto(customer.getCart());
        int age = customer.getAge();
        String email = customer.getEmail();
        return new CustomerDto(id, name, cartDto, age, email);
    }

    public CommonCustomer mapDtoToCommonCustomer(CustomerDto dto) {
        int id = dto.getId();
        String name = dto.getName();
        Cart cart = cartMappingService.mapDtoToCommonCart(dto.getCart());
        int age = dto.getAge();
        String email = dto.getEmail();
        return new CommonCustomer(id, true, name, cart, age, email);
    }

    public JpaCustomer mapDtoToJpaCustomer(CustomerDto dto) {
        String name = dto.getName();
        int age = dto.getAge();
        String email = dto.getEmail();
        return new JpaCustomer(0, true, name, age, email, null);
    }
}