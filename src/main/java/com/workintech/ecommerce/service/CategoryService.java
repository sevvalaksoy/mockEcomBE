package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface CategoryService {
    Category save(Category category);
    Category update(long id);
    Category delete(Category category);
    Category findById(long id);
    List<Category> findAll();
    List<Product> getProductsByCategory(String name);
    Category findByName(String name);
}
