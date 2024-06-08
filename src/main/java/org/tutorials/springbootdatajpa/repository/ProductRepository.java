package org.tutorials.springbootdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutorials.springbootdatajpa.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find by single field name use name
    Product findByName(String name);

    // Find by single field value use id
    Optional<Product> findById(Long id);

    // Find by multiple field names use OR operator
    List<Product> findByNameOrDescription(String name, String description);

    // Find by multiple field names use AND operator
    List<Product> findByNameAndDescription(String name, String description);

    // Find by Distinct field names use Distinct keyword
    Product findDistinctByName(String name);

    // Find by GreaterThen price
    List<Product> findByPriceGraterThen(BigDecimal price);

    // Find by LessThen price
    List<Product> findByPriceLessThen(BigDecimal price);

    // Find by Containing field names use Containing keyword
    List<Product> findByNameContaining(String name);

    // Find by Like field names use Like keyword
    List<Product> findByNameLike(String name);

    // Find by Between field startPrice, endPrice
    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    // Find by between DateCreated between startDate, endDate
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find by In multiple values use In keyword
    List<Product> findByNameIn(List<String> names);

    // Find by Limiting Query Results By First 2 records use First keyword
    List<Product> findFirst2ByOrderByNameAsc();

    // Find by Limiting Query Results By Top 2 records use Top keyword
    List<Product> findTop2ByOrderByPriceDesc();
}
