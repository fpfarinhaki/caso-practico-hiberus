package com.hiberus.caso_practico.application.criteria;

public interface SortingCriteria<T, R> {
    R calculateScore(T entity);

}
