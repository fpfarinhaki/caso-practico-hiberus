package com.hiberus.casopractico.application.criteria;

public interface SortingCriteria<T, R> {
    R calculateScore(T entity);

}
