package com.hiberus.casopractico.application.criteria;

import com.hiberus.casopractico.model.Product;

public class SalesUnitsCriteria extends com.hiberus.casopractico.application.criteria.WeightedSortingCriteria<Product> {
    public SalesUnitsCriteria(Integer weight) {
        super(weight);
    }

    @Override
    double calculateCriteriaScore(Product product) {
        return product.salesUnits() < 0 ? 0 : product.salesUnits();
    }
}
