package com.hiberus.casopractico.application.mapping;

import com.hiberus.casopractico.infrastructure.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.casopractico.application.criteria.SalesUnitsCriteria;
import com.hiberus.casopractico.application.criteria.StockRatioCriteria;
import com.hiberus.casopractico.application.criteria.WeightedSortingCriteria;
import com.hiberus.casopractico.model.Product;
import io.micrometer.common.util.StringUtils;

public class WeightedSortingCriteriaFactory {

    public WeightedSortingCriteria<Product> create(WeightedSortingCriteriaDto criteriaDto) {
        if(StringUtils.isEmpty(criteriaDto.criteriaName()) || criteriaDto.weight() == null) {
            throw new IllegalArgumentException("Name and weight cannot be null");
        }
        return switch (criteriaDto.criteriaName()) {
            case "salesUnits" -> new SalesUnitsCriteria(criteriaDto.weight());
            case "stockRatio" -> new StockRatioCriteria(criteriaDto.weight());
            default -> throw new IllegalArgumentException("Invalid sorting criteria criteriaName: " + criteriaDto.criteriaName());
        };
    }
}
