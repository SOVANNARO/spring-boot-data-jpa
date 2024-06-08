package org.tutorials.springbootdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tutorials.springbootdatajpa.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        Product product = productRepository.findByName("Apple");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod() {
        Product product = productRepository.findById(1L).get();

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionMethod() {
        List<Product> products = productRepository.findByNameOrDescription("Apple", "Apple description");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameAndDescription("Apple", "Apple description");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByDistinctByNameMethod(){
        Product product = productRepository.findDistinctByName("Apple");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByPriceGraterThenMethod(){
        List<Product> products = productRepository.findByPriceGraterThen(new BigDecimal(100));

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByPriceLessThenMethod(){
        List<Product> products = productRepository.findByPriceLessThen(new BigDecimal(100));

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByNameContainingMethod() {
        List<Product> products = productRepository.findByNameContaining("Apple");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByNameLikeMethod() {
        List<Product> products = productRepository.findByNameLike("%Apple%");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(100), new BigDecimal(200));

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        LocalDateTime startDate = LocalDateTime.of(2024, 12, 12, 12, 12, 12);
        LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 23, 59, 59);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByNameInMethod() {
        List<Product> products = productRepository.findByNameIn(List.of("Apple", "Banana"));

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findFirst2ByOrderByNameAscMethod() {
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findTop2ByOrderByPriceDescMethod() {
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }
}
