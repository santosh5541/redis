package com.example.redis.repository;

import com.example.redis.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDao {
    private final RedisTemplate redisTemplate;

    public Product saveProduct(Product product) {
        redisTemplate.opsForHash().put("Product", String.valueOf(product.getId()), product);
        return product;
    }

    public List<Product> products() {
        System.out.println("from database");
        return redisTemplate.opsForHash().values("Product");
    }

    public Product getProductById(int id) {
        System.out.println("fetchted from database");
        return (Product) redisTemplate.opsForHash().get("Product", String.valueOf(id));
    }

    public String deleteProductById(int id) {
        redisTemplate.opsForHash().delete("Product", String.valueOf(id));
        return "Product deleted successfully";
    }
}
