package com.hiberus.caso_practico.application.port.in;

import com.hiberus.caso_practico.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.caso_practico.model.Product;

import java.util.List;

public interface SortProductUseCase {
    List<Product> sort(List<WeightedSortingCriteriaDto> sortingCriteria);
}
