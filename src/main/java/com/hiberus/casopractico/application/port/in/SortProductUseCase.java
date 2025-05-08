package com.hiberus.casopractico.application.port.in;

import com.hiberus.casopractico.infrastructure.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.casopractico.model.Product;

import java.util.List;

public interface SortProductUseCase {
    List<Product> sort(List<WeightedSortingCriteriaDto> sortingCriteria);
}
