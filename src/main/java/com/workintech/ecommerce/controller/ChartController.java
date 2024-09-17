package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.entity.Chart;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
public class ChartController {
    private ChartService chartService;
    @Autowired
    public ChartController(ChartService chartService){
        this.chartService = chartService;
    }
    @GetMapping("/{id}")
    public Chart getChart(@PathVariable long id){
        return chartService.findById(id);
    }
    @GetMapping
    public List<Chart> getAllCharts(){
        return chartService.findAll();
    }
    @GetMapping("/products/{id}")
    public List<Product> getAllProducts(@PathVariable long id){
        return chartService.getItemsInChart(id);
    }
    @PutMapping("/add/{chartId}/{productId}")
    public Chart addItem(@PathVariable long chartId, @PathVariable long productId, @RequestBody int quantity){
        return chartService.addItemToChart(chartId,productId,quantity);
    }
    @PutMapping("/update/{chartId}/{productId}")
    public Chart updateQuantity(@PathVariable long chartId, @PathVariable long productId, @RequestBody int quantity){
        return chartService.updateItemQuantity(chartId,productId,quantity);
    }
    @DeleteMapping("/item/{chartId}/{productId}")
    public Chart deleteItem(@PathVariable long chartId, @PathVariable long productId){
        return chartService.removeItemFromChart(chartId, productId);
    }
    @DeleteMapping("/{id}")
    public Chart delete(@PathVariable long id){
        Chart chartToDelete = chartService.findById(id);
        chartService.delete(chartToDelete);
        return chartToDelete;
    }
}
