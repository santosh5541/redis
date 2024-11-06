package com.example.redis.controller;

import com.example.redis.model.Product;
import com.example.redis.repository.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@EnableCaching
public class ProductRestController {
    private final ProductDao productDao;

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productDao.saveProduct(product);
    }

    @GetMapping
    @Cacheable(value = "Product", unless = "#result.?[price < 3500].isEmpty() == false")
    public List<Product> getAllProducts() {
        return productDao.products();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "Product", key = "#id")
    public Product getProductById(@PathVariable int id) {
        return productDao.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDao.deleteProductById(id);
    }

}
