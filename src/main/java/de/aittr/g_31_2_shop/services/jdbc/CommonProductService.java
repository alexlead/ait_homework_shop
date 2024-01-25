package de.aittr.g_31_2_shop.services.jdbc;

import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.repositories.interfaces.ProductRepository;
import de.aittr.g_31_2_shop.services.intefaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;

import java.util.List;

//@Service
public class CommonProductService implements ProductService {

    private ProductRepository repository;

    private ProductMappingService mappingService;

    public CommonProductService(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto product) {
        CommonProduct commonProduct = mappingService.mapDtoToCommonProduct(product);
        Product saveProduct = repository.save(commonProduct);
        return mappingService.mapEntityToDto(saveProduct);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {

        return repository.getAll()
                .stream()
                .map(product -> mappingService.mapEntityToDto(product) )
                .toList();
    }

    @Override
    public ProductDto getActiveProductById(int id) {
        return null;
    }

    @Override
    public void update(ProductDto product) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(int id) {

    }

    @Override
    public int getActiveProductCount() {
        return 0;
    }

    @Override
    public double getActiveProductTotalPrice() {
        return 0;
    }

    @Override
    public double getActiveProductAveragePrice() {
        return 0;
    }
}
