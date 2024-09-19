package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.ProductDTO;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.CategoryService;
import com.workintech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    @Autowired
    public ProductController(ProductService productService,CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.findAll();
    }
    @GetMapping("/mostlyAdded")
    public List<Product> getMostlyAddedProducts(){
        return productService.findMostAddedProductsToChart();
    }
    @GetMapping("/mostSold")
    public List<Product> getMostSold(){
        return productService.findMostSoldProducts();
    }
    @GetMapping("/name/{name}")
    public List<Product> getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id){
        return productService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product post(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setCategory(categoryService.findByName(productDTO.getCategory()));

        return productService.save(product);
    }
    @PutMapping("/{id}")
    public Product update(@PathVariable long id, @RequestBody Product product){
        Product productToUpdate = productService.findById(id);
        productToUpdate.setStock(product.getStock());
        productToUpdate.setName(product.getName());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setOrderDetails(product.getOrderDetails());
        productToUpdate.setCharts(product.getCharts());
        return productService.save(productToUpdate);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product delete(@PathVariable long id){
        Product productToDelete = productService.findById(id);
        productService.delete(productToDelete);
        return productToDelete;
    }
}
