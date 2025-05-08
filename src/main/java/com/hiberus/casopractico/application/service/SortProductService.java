package com.hiberus.casopractico.application.service;

import com.hiberus.casopractico.application.mapping.WeightedSortingCriteriaFactory;
import com.hiberus.casopractico.application.port.in.SortProductUseCase;
import com.hiberus.casopractico.application.port.out.persistance.ProductRepository;
import com.hiberus.casopractico.infrastructure.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.casopractico.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SortProductService implements SortProductUseCase {

    private final ProductRepository productRepository;
    private final WeightedSortingCriteriaFactory weightedSortingCriteriaFactory;

    public SortProductService(ProductRepository productRepository,
                              WeightedSortingCriteriaFactory weightedSortingCriteriaFactory) {
        this.productRepository = productRepository;
        this.weightedSortingCriteriaFactory = weightedSortingCriteriaFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> sort(List<WeightedSortingCriteriaDto> weightedSortingCriteria) {
        try(Stream<Product> productStream = productRepository.streamAll()) {
            return productStream
                    .sorted(Comparator.comparingDouble(product -> calculateScore(product, weightedSortingCriteria)))
                    .toList();
        }
    }

    private double calculateScore(Product product, List<WeightedSortingCriteriaDto> weightedSortingCriteria) {
        return weightedSortingCriteria.stream()
                .map(weightedSortingCriteriaFactory::create)
                .map(criteria -> criteria.calculateScore(product))
                .reduce(0.0, Double::sum);
    }

}
