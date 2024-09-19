package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.CategoryRepository;
import com.workintech.ecommerce.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Product> getProductsByCategory(String category){
        return categoryRepository.getProductsByCategory(category);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Category save(Category category) {
        Validation.existenceValidation(categoryRepository, category.getId(), false);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id) {
        Validation.isIdValid(id);
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return categoryRepository.save(optionalCategory.get());
        }
        throw new AccountException("There is no category with the given id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Category delete(Category category) {
        Optional<Category> optional = categoryRepository.findById(category.getId());
        if(optional.isPresent()){
            Category categoryToDelete = category;
            categoryRepository.delete(category);
            return categoryToDelete;
        }
        throw new AccountException("There is no such category.", HttpStatus.NOT_FOUND);
    }

    @Override
    public Category findById(long id) {
        Validation.isIdValid(id);
        Optional<Category> optionalCategories = categoryRepository.findById(id);
        if(optionalCategories.isPresent()){
            return optionalCategories.get();
        }
        throw new AccountException("There is no category with the given id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
