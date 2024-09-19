package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void save() {
        Product product = new Product();
        product.setStock(45);
        product.setId(1L);
        product.setName("saat");
        product.setPrice(4559.99);
        Category category = new Category();
        category.setName("TAKI");
        product.setCategory(category);
        productService.save(product);
        verify(productRepository).save(product);
    }

    @Test
    void canNotSave() {
        Product product = new Product();
        product.setStock(45);
        product.setName("saat");
        product.setId(1L);
        product.setPrice(4559.99);
        Category category = new Category();
        category.setName("TAKI");
        product.setCategory(category);

        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
        assertThatThrownBy(()->productService.save(product)).isInstanceOf(AccountException.class).hasMessageContaining("Product already exists");

        verify(productRepository, never()).findAll();
    }

    @Test
    void findAll() {
        productService.findAll();
        verify(productRepository).findAll();
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }


    @Test
    void findMostAddedProductsToChart() {
    }

    @Test
    void getProductByName() {
    }

    @Test
    void findMostSoldProducts() {
    }
}