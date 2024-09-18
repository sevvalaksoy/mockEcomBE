package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return productRepository.save(optionalProduct.get());
        }
        throw new AccountException("There is no product with the given id", HttpStatus.NOT_FOUND);
    }

    @Override
    public Product delete(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()){
            Product productToDelete = optionalProduct.get();
            productRepository.delete(product);
            return productToDelete;
        }
        throw new AccountException("There is no such product", HttpStatus.NOT_FOUND);
    }

    @Override
    public Product findById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        } else {
            throw new AccountException("There is no such product with the given id", HttpStatus.NOT_FOUND);
        }
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
