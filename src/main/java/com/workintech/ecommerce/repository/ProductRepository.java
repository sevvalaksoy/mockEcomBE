package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "JOIN p.charts c " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(c.id) DESC")
    List<Product> findMostAddedProductsToChart();
}
