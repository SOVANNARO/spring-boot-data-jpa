package org.tutorials.springbootdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tutorials.springbootdatajpa.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
