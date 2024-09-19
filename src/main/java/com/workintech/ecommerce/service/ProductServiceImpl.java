package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(long id) {
        Validation.isIdValid(id);
        Validation.existenceValidation(productRepository, id, true);
        Optional<Product> optionalProduct = productRepository.findById(id);
        return productRepository.save(optionalProduct.get());
    }

    @Override
    public Product delete(Product product) {
        Validation.existenceValidation(productRepository, product.getId(), true);
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        Product productToDelete = optionalProduct.get();
        productRepository.delete(product);
        return productToDelete;
    }

    @Override
    public Product findById(long id) {
        Validation.isIdValid(id);
        Validation.existenceValidation(productRepository,id,true);
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findMostAddedProductsToChart() {
        return productRepository.findMostAddedProductsToChart();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public List<Product> findMostSoldProducts() {
        return productRepository.findMostSoldProducts();
    }
}
