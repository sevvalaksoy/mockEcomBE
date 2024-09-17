package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Chart;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.AccountException;
import com.workintech.ecommerce.repository.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartServiceImpl implements ChartService{

    private ChartRepository chartRepository;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public ChartServiceImpl(ChartRepository chartRepository, ProductService productService, UserService userService){
        this.chartRepository = chartRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public Chart save(long userId) {
        Chart chart = new Chart();
        User user = userService.findById(userId);
        chart.setUser(user);
        return chart;
    }

    @Override
    public Chart delete(Chart chart) {
        Optional<Chart> optional = chartRepository.findById(chart.getId());
        if(optional.isPresent()){
            Chart chartToDelete = optional.get();
            chartRepository.delete(chart);
            return chartToDelete;
        }
        throw new AccountException("There is no such chart.", HttpStatus.NOT_FOUND);
    }

    @Override
    public Chart findById(long id) {
        Optional<Chart> optional = chartRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new AccountException("There is no such chart.", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Chart> findAll() {
        return chartRepository.findAll();
    }

    @Override
    public Chart addItemToChart(long chartId, long productId, int quantity) {
        Optional<Chart> optionalChart = chartRepository.findById(chartId);
        Product product = productService.findById(productId);
        Integer stock = product.getStock();
        if(optionalChart.isPresent() && product!=null && stock>quantity){
            product.setStock(stock-quantity);
            optionalChart.get().addProduct(product);
            productService.update(product.getId());
            chartRepository.save(optionalChart.get());
            return optionalChart.get();
        } else {
            throw new AccountException("Chart or Product Id is invalid.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Product> getItemsInChart(long chartId) {
        Optional<Chart> optional = chartRepository.findById(chartId);
        if(optional.isPresent()){
            List<Product> list = optional.get().getProducts();
            return list;
        } else {
            throw new AccountException("There is no chart with the given Id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chart updateItemQuantity(long chartId, long productId, int quantity) {
        Optional<Chart> optionalChart = chartRepository.findById(chartId);
        Product product = productService.findById(productId);
        Integer stock = product.getStock();
        if(optionalChart.isPresent() && product!=null && stock>quantity){
            product.setStock(stock-quantity);
            optionalChart.get().addProduct(product);
            productService.update(product.getId());
            chartRepository.save(optionalChart.get());
            return optionalChart.get();
        } else {
            throw new AccountException("Chart or Product Id is invalid.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Chart removeItemFromChart(long chartId, long productId) {
        Optional<Chart> optionalChart = chartRepository.findById(chartId);
        Product product = productService.findById(productId);
        if(optionalChart.isPresent() && product!=null){
            optionalChart.get().removeProduct(product);
            product.setStock(product.getStock()+1);
            return optionalChart.get();
        } else {
            throw new AccountException("Chart or Product Id is invalid.", HttpStatus.NOT_FOUND);
        }
    }
}
