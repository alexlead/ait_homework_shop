package de.aittr.g_31_2_shop.services.jpa;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.exception_handling.exceptions.*;
import de.aittr.g_31_2_shop.repositories.jpa.JpaProductRepository;
import de.aittr.g_31_2_shop.scheduling.ScheduleExecutor;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import de.aittr.g_31_2_shop.services.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaProductService implements ProductService {

    private JpaProductRepository repository;
    private ProductMappingService mappingService;
    //    private Logger logger = LogManager.getLogger(JpaProductService.class);
    private Logger logger = LoggerFactory.getLogger(JpaProductService.class);

    public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        try {
            JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
            entity.setId(0);
            entity = repository.save(entity);
            return mappingService.mapProductEntityToDto(entity);
        } catch (Exception e) {
            throw new ProductValidationException("Incorrect values of product fields", e);
        }
    }

    /*
    Домашнее задание 15
    3. Подумать, какие нештатные ситуации могут возникать при работе сервиса продуктов (для этого реализовать его до конца),
    создать для них соответствующие эксепшены (минимум 3). Эксепшены должны быть
    названы правильно, то есть название должно отражать суть причины ошибки.
    4. Выбросить эти эксепшены в нужных местах и обработать при помощи адвайса.
     */
    @Override
    public List<ProductDto> getAllActiveProducts() {
//        Task task = new Task("Method getAllActiveProducts called");
//        ScheduleExecutor.scheduleAndExecuteTask(task);
        // здесь будет JoinPoint, сюда будет внедряться вспомогательный код

        List<ProductDto> products = repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .map(p -> mappingService.mapProductEntityToDto(p))
                .toList();

        if (products.isEmpty()) {
            throw new NoActiveProductsFoundException("There are no active products in the database");
        }

        return products;
    }

    @Override
    public ProductDto getActiveProductById(int id) {

//        logger.log(Level.INFO, String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.log(Level.WARN, String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.log(Level.ERROR, String.format("Запрошен продукт с идентификатором %d.", id));

//        logger.info(String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.warn(String.format("Запрошен продукт с идентификатором %d.", id));
//        logger.error(String.format("Запрошен продукт с идентификатором %d.", id));

        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException(String.format(
                    "There is no product with id [%d] in the database", id));
        }

        if (!product.isActive()) {
            throw new InactiveProductException(String.format(
                    "Product with id [%d] is inactive and cannot be retrieved", id));
        }

        return mappingService.mapProductEntityToDto(product);
    }

    @Override
    public void update(ProductDto dto) {
        JpaProduct entity = mappingService.mapDtoToJpaProduct(dto);
        repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Product product = repository.findById(id).orElse(null);

        if (product != null && product.isActive()) {
            product.setActive(false);
        }
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Product product = repository.findByName(name);

        if (product != null && product.isActive()) {
            product.setActive(false);
        }
    }

    @Override
    @Transactional
    public void restoreById(int id) {
        Product product = repository.findById(id).orElse(null);

        if (product != null && !product.isActive()) {
            product.setActive(true);
        }
    }

    @Override
    public int getActiveProductCount() {
        return (int) repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .count();
    }

    @Override
    public double getActiveProductsTotalPrice() {
        return repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .mapToDouble(p -> p.getPrice())
                .sum();
    }

    @Override
    public double getActiveProductAveragePrice() {
        return repository.findAll()
                .stream()
                .filter(p -> p.isActive())
                .mapToDouble(p -> p.getPrice())
                .average()
                .orElse(0);
    }

    /*
    Домашнее задание 18
    2. Реализовать вывод в консоль последнего добавленного в БД товара.
    Вывод должен производиться в 15 и 45 секунд каждой минуты.
    Задача должна быть сохранена в БД.
    Вывод в консоль должен быть осуществлён через логирование поля description созданной задачи.
    Пример значения поля description - "Последний добавленный в БД продукт - Банан".
     */

    public JpaProduct getLastProduct() {
        return repository.getLastProduct();
    }
}