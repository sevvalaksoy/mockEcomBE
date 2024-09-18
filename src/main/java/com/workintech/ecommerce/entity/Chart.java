package com.workintech.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "chart", schema = "ecommerce")
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "product_chart", schema = "ecommerce", joinColumns = @JoinColumn(name = "chart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public void addProduct(Product product){
        if(products==null){
            products = new ArrayList<>();
        }
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }

    public Double priceCalculator(List<Product> products){
        double total = 0;
        for(Product x:products){
            total+=x.getPrice();
        }
        return total;
    }
}
