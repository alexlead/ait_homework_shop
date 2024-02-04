package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.CustomerDto;
import de.aittr.g_31_2_shop.domain.jpa.JpaCustomer;
import de.aittr.g_31_2_shop.exception_handling.exceptions.IllegalCustomerIdRequestedException;
import de.aittr.g_31_2_shop.repositories.jpa.JpaCustomerRepository;
import de.aittr.g_31_2_shop.services.interfaces.CustomerService;
import de.aittr.g_31_2_shop.services.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaCustomerService implements CustomerService {

    private JpaCustomerRepository repository;
    private CustomerMappingService mappingService;

    public JpaCustomerService(JpaCustomerRepository repository, CustomerMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public CustomerDto save(CustomerDto customer) {

        try {
            JpaCustomer entity = mappingService.mapDtoToJpaCustomer(customer);
            entity.setId(0);
            entity = repository.save(entity);
            return mappingService.mapCustomerEntityToDto(entity);
        } catch (Exception e) {
            throw new IllegalCustomerIdRequestedException(e.getMessage());
        }


    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(c -> c.isActive())
                .map(c -> mappingService.mapCustomerEntityToDto(c))
                .toList();
    }

    @Override
    public CustomerDto getActiveCustomerById(int id) {
        JpaCustomer entity = repository.findById(id).orElse(null);

        if (entity != null && entity.isActive()) {
            return mappingService.mapCustomerEntityToDto(entity);
        }

        return null;
    }

    @Override
    public void update(CustomerDto customer) {
        JpaCustomer entity = mappingService.mapDtoToJpaCustomer(customer);
        repository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        JpaCustomer entity = repository.findById(id).orElse(null);

        if (entity != null && entity.isActive()) {
            entity.setActive(false);
        }
    }

    @Override
    public void deleteByName(String name) {
        JpaCustomer entity= repository.findByName(name);

        if (entity != null && entity.isActive()) {
            entity.setActive(false);
        }
    }

    @Override
    public void restoreById(int id) {
        JpaCustomer entity = repository.findById(id).orElse(null);

        if (entity != null && !entity.isActive()) {
            entity.setActive(true);
        }
    }

    @Override
    public int getActiveCustomersCount() {
        return 0;
    }

    @Override
    public double getCartTotalPriceById(int customerId) {
        return 0;
    }

    @Override
    public double getAverageProductPriceById(int customerId) {
        return 0;
    }

    @Override
    public void addProductToCart(int customerId, int productId) {

    }

    @Override
    public void deleteProductFromCart(int customerId, int productId) {

    }

    @Override
    public void clearCartById(int customerId) {

    }
}