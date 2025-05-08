package com.hiberus.casopractico.application.criteria;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class WeightedSortingCriteria<T> implements com.hiberus.casopractico.application.criteria.SortingCriteria<T,Double> {
    private final Integer weight;

    public WeightedSortingCriteria(Integer weight) {
        this.weight = weight;
    }

    abstract double calculateCriteriaScore(T entity);

    public Double calculateScore(T entity) {
        var result = calculateCriteriaScore(entity) * weight;
        log.trace("Weighted score for entity {}: {}", entity, result);
        return result;
    }
}
