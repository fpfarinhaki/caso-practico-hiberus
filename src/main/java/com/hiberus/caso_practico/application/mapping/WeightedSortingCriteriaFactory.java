package com.hiberus.caso_practico.application.mapping;

import com.hiberus.caso_practico.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.caso_practico.application.criteria.SalesUnitsCriteria;
import com.hiberus.caso_practico.application.criteria.StockRatioCriteria;
import com.hiberus.caso_practico.application.criteria.WeightedSortingCriteria;
import com.hiberus.caso_practico.model.Product;
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
