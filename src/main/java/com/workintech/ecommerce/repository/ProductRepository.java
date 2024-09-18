package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT p.* " +
            "FROM ecommerce.products p " +
            "INNER JOIN ecommerce.chart c ON p.id = c.product_id " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(c.id) DESC", nativeQuery = true)
    List<Product> findMostAddedProductsToChart();
    @Query("SELECT p FROM Product p WHERE p.name=:name")
    List<Product> getProductByName(String name);

    @Query(value = "SELECT p.* " +
            "FROM ecommerce.products p " +
            "INNER JOIN ecommerce.order_details AS od ON p.id = od.product_id " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(od.id) DESC", nativeQuery = true)
    List<Product> findMostSoldProducts();
}
