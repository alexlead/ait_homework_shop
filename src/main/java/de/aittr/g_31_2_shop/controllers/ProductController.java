package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.services.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto product) {
        return service.save(product);
    }

    @PostMapping("/update")
    public void update(@RequestBody ProductDto product) {
        service.update(product);
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return service.getAllActiveProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getActiveProductById(@PathVariable int id) {
        return service.getActiveProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        service.deleteById( id );
    }


    @DeleteMapping("/byname/{name}")
    public void deleteById(@PathVariable String name) {
        service.deleteByName(name);
    }

    @DeleteMapping("/restore/{id}")
    public void restoreById(@PathVariable int id) {
        service.restoreById(id);
    }

    @GetMapping("/count")
    public int getActiveProductCount () {
        return service.getActiveProductCount();
    }
    @GetMapping("/total")
    public double getActiveProductsTotalPrice () {
        return service.getActiveProductsTotalPrice();
    }
    @GetMapping("/average")
    public double getActiveProductAveragePrice () {
        return service.getActiveProductAveragePrice();
    }


}