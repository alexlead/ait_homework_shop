package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaProductService implements ProductService {

    private JpaProductRepository repository;
    private ProductMappingService mappingService;

    public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
        entity.setId(0);
        entity = repository.save(entity);
        return mappingService.mapProductEntityToDto(entity);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {

        return repository.getAllActiveProducts()
                .stream()
                .map(product -> mappingService.mapProductEntityToDto(product))
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        return mappingService.mapProductEntityToDto(repository.getActiveProductById(id));
    }

    @Override
    public void update(ProductDto dto) {
        JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
        repository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        JpaProduct entity = repository.getById(id);
        entity.setActive(false);
        repository.save(entity);
    }

    @Override
    public void deleteByName(String name) {
        JpaProduct entity = repository.findByName(name);
        entity.setActive(false);
        repository.save(entity);
//        repository.deactivateProductByName(name);

    }

    @Override
    public void restoreById(int id) {
        JpaProduct entity = repository.getById(id);
        entity.setActive(true);
        repository.save(entity);
    }

    @Override
    public int getActiveProductCount() {
        return repository.getActiveProductCount();
    }

    @Override
    public double getActiveProductsTotalPrice() {
        return repository.getActiveProductsTotalPrice();
    }

    @Override
    public double getActiveProductAveragePrice() {
        return repository.getActiveProductAveragePrice();
    }
}