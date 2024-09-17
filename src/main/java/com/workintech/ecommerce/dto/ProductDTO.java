package com.workintech.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String name;
    private Double price;
    private String description;
    private Integer stock;
    private String category;
}
