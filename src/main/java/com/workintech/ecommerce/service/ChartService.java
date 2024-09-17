package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Chart;
import com.workintech.ecommerce.entity.Product;

import java.util.List;

public interface ChartService {
    Chart save(long userId);
    Chart delete(Chart chart);
    Chart findById(long id);
    List<Chart> findAll();
    Chart addItemToChart(long chartId, long productId, int quantity);
    List<Product> getItemsInChart(long chartId);
    Chart updateItemQuantity(long chartId, long productId, int quantity);
    Chart removeItemFromChart(long chartId, long productId);
}
