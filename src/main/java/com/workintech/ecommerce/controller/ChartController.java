package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.entity.Chart;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.service.ChartService;
import com.workintech.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chart")
public class ChartController {
    private ChartService chartService;
    private UserService userService;

    @Autowired
    public ChartController(ChartService chartService,UserService userService){
        this.chartService = chartService;
        this.userService = userService;
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

    @PostMapping("/{userId}")
    public Chart createChart(@PathVariable long userId){
        User user = userService.findById(userId);
        Chart chart = new Chart();
        chart.setUser(user);
        return chartService.save(chart);
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
