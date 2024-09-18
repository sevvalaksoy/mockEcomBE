package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    Product update(long id);
    Product delete(Product product);
    Product findById(long id);
    List<Product> findAll();
    List<Product> findMostAddedProductsToChart();
    List<Product> getProductByName(String name);
    List<Product> findMostSoldProducts();
}
