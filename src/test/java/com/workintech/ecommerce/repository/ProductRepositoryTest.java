package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Chart;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {
    private ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setStock(45);
        product.setName("saat");
        product.setPrice(4559.99);
        Category category = new Category();
        category.setName("TAKI");
        product.setCategory(category);
        productRepository.save(product);
        List<Product> productToGet = productRepository.getProductByName(product.getName());
    }

    @AfterEach
    void tearDown() {
        List<Product> products = productRepository.getProductByName("saat");
        productRepository.deleteAll(products);
    }

    @DisplayName("Find the products that are added to chart the most")
    @Test
    void findMostAddedProductsToChart() {
        List<Product> products = productRepository.findMostAddedProductsToChart();
        assertNotNull(products);
    }
    @DisplayName("Find the products with the given product name")
    @Test
    void getProductByName() {
        List<Product> productToGet = productRepository.getProductByName("saat");
        assertNotNull(productToGet);
    }
    @DisplayName("Find the mostly sold products")
    @Test
    void findMostSoldProducts() {
        List<Product> products = productRepository.findMostSoldProducts();
        assertNotNull(products);
    }
}