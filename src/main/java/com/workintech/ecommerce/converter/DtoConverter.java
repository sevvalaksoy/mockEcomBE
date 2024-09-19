package com.workintech.ecommerce.converter;

import com.workintech.ecommerce.dto.ProductResponse;
import com.workintech.ecommerce.entity.Product;

public class DtoConverter {
    public static ProductResponse converter(Product product){
        ProductResponse productResponse = new ProductResponse(product.getName(),product.getDescription(),product.getPrice());
        return productResponse;
    }
}
