package com.hiberus.caso_practico.service;

import com.hiberus.caso_practico.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.caso_practico.model.Product;
import com.hiberus.caso_practico.application.service.SortProductService;
import com.hiberus.caso_practico.application.port.out.persistance.ProductRepository;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SortProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SortProductService sortProductService;

    @ParameterizedTest
    @CsvSource({
            "10,Product B",
            "100,Product B",
            "200,Product A"
    })
    void sortsProductsByWeightedCriteriaCorrectly(Integer stockRatioWeight, String expectedProductName) {
        Product p1 = new Product(1, "Product A", 100, Map.of("S", 10, "M", 5, "L", 0));
        Product p2 = new Product(2, "Product B", 50, Map.of("S", 35, "M", 9, "L", 9));

        when(productRepository.findAllProducts()).thenReturn(List.of(p1, p2));

        List<Product> result = sortProductService.sort(List.of(
                new WeightedSortingCriteriaDto("salesUnits", 1),
                new WeightedSortingCriteriaDto("stockRatio", 1)
        ));

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isNotEmpty();
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result).first().is(new Condition<>(p -> p.name().equals(expectedProductName), "Product with criteriaName " + expectedProductName));
    }
}