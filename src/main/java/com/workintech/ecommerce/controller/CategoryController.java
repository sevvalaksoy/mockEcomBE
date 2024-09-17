package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/products/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName){
        return categoryService.getProductsByCategory(categoryName);
    }
    @GetMapping()
    public List<Category> getAll(){
        return categoryService.findAll();
    }
    @GetMapping("/name/{name}")
    public Category getByName(@PathVariable String name){
        return categoryService.findByName(name);
    }
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return categoryService.findById(id);
    }
    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryService.save(category);
    }
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        Category categoryToUpdate = categoryService.findById(id);
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setProducts(category.getProducts());
        return categoryService.update(id);
    }
    @DeleteMapping
    public Category delete(@RequestBody Category category){
        return categoryService.delete(category);
    }

}
