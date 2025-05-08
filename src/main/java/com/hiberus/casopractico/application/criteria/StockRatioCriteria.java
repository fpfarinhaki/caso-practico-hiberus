package com.hiberus.casopractico.application.criteria;

import com.hiberus.casopractico.model.Product;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class StockRatioCriteria extends com.hiberus.casopractico.application.criteria.WeightedSortingCriteria<Product> {
    public StockRatioCriteria(Integer weight) {
        super(weight);
    }

    @Override
    double calculateCriteriaScore(Product entity) {
        return calculateStockRatio(entity);
    }

    private double calculateStockRatio(Product product) {
        var stock = product.stock();
        if (stock == null || stock.isEmpty()) {
            return 0.0;
        }
        long sizesWithStock = stock.values().stream()
                .filter(amount -> amount > 0)
                .count();
        var stockRatio =  (double) sizesWithStock / stock.size();
        log.debug("Stock ratio for product {}: {}", product.name(), stockRatio);
        return stockRatio;
    }
}
