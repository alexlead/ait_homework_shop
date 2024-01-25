package de.aittr.g_31_2_shop.services.mapping;

import de.aittr.g_31_2_shop.domain.jdbc.CommonProduct;
import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import org.springframework.stereotype.Service;

@Service
public class ProductMappingService {

    public ProductDto mapEntityToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }


    public CommonProduct mapDtoToCommonProduct (ProductDto product) {
        return new CommonProduct(
                product.getId(),
                true,
                product.getName(),
                product.getPrice()
        );
    }

    public JpaProduct mapDpoToJpaProduct (ProductDto product) {
        return new JpaProduct(
                product.getId(),
                product.getName(),
                product.getPrice(),
                true
        );
    }

}
