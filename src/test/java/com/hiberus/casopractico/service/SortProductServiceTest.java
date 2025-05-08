package com.hiberus.casopractico.service;

import com.hiberus.casopractico.infrastructure.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.casopractico.application.mapping.WeightedSortingCriteriaFactory;
import com.hiberus.casopractico.application.port.out.persistance.ProductRepository;
import com.hiberus.casopractico.application.service.SortProductService;
import com.hiberus.casopractico.commons.ApiConstants;
import com.hiberus.casopractico.model.Product;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SortProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private WeightedSortingCriteriaFactory weightedSortingCriteriaFactory;

    @InjectMocks
    private SortProductService sortProductService;


    @ParameterizedTest
    @CsvSource({
            "1, Product B",
            "10,Product B",
            "100,Product B",
            "200,Product A" // this makes the stock ratio make a difference for the final score
    })
    void sortsProductsByWeightedCriteriaCorrectly(Integer stockRatioWeight, String expectedProductName) {
        Product p1 = new Product(1, "Product A", 100, Map.of("S", 10, "M", 5, "L", 0));
        Product p2 = new Product(2, "Product B", 50, Map.of("S", 35, "M", 9, "L", 9));

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));
        when(weightedSortingCriteriaFactory.create(any())).thenCallRealMethod();

        List<Product> result = sortProductService.sort(List.of(
                new WeightedSortingCriteriaDto(ApiConstants.CRITERIA_SALES_UNITS, 1),
                new WeightedSortingCriteriaDto(ApiConstants.CRITERIA_STOCK_RATIO, stockRatioWeight)
        ));

        Assertions.assertThat(result).isNotNull().isNotEmpty().hasSize(2);
        Assertions.assertThat(result).first().is(new Condition<>(p -> p.name().equals(expectedProductName), "Product with criteriaName " + expectedProductName));
    }
}