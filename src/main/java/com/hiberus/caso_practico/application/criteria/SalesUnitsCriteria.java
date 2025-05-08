package com.hiberus.caso_practico.application.criteria;

import com.hiberus.caso_practico.model.Product;

public class SalesUnitsCriteria extends WeightedSortingCriteria<Product> {
    public SalesUnitsCriteria(Integer weight) {
        super(weight);
    }

    @Override
    double calculateCriteriaScore(Product product) {
        return product.salesUnits() < 0 ? 0 : product.salesUnits();
    }
}
