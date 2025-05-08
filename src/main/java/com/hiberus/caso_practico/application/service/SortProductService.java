package com.hiberus.caso_practico.application.service;

import com.hiberus.caso_practico.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.caso_practico.application.mapping.WeightedSortingCriteriaFactory;
import com.hiberus.caso_practico.model.Product;
import com.hiberus.caso_practico.application.port.in.SortProductUseCase;
import com.hiberus.caso_practico.application.port.out.persistance.ProductRepository;

import java.util.Comparator;
import java.util.List;

public class SortProductService implements SortProductUseCase {

    private final ProductRepository productRepository;
    private final WeightedSortingCriteriaFactory weightedSortingCriteriaFactory;

    public SortProductService(ProductRepository productRepository,
                              WeightedSortingCriteriaFactory weightedSortingCriteriaFactory) {
        this.productRepository = productRepository;
        this.weightedSortingCriteriaFactory = weightedSortingCriteriaFactory;
    }

    @Override
    public List<Product> sort(List<WeightedSortingCriteriaDto> weightedSortingCriteria) {
        return productRepository.findAllProducts().stream()
                .sorted(Comparator.comparingDouble(product -> calculateScore(product, weightedSortingCriteria)))
                .toList();
    }

    private double calculateScore(Product product, List<WeightedSortingCriteriaDto> weightedSortingCriteria) {
        return weightedSortingCriteria.stream()
                .map(weightedSortingCriteriaFactory::create)
                .map(criteria -> criteria.calculateScore(product))
                .reduce(0.0, Double::sum);
    }

}
