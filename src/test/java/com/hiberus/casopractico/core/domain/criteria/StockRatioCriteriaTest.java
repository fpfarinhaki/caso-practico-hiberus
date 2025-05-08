package com.hiberus.casopractico.core.domain.criteria;

import com.hiberus.casopractico.application.criteria.StockRatioCriteria;
import com.hiberus.casopractico.model.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StockRatioCriteriaTest {
    private static final String S_SIZE = "S";
    private static final String M_SIZE = "M";
    private static final String L_SIZE = "L";

    @ParameterizedTest
    @MethodSource("provideStockForVariousScenarios")
    void calculatesStockRatioCorrectlyForVariousScenarios(Map<String, Integer> stock, double expectedRatio) {
        Product product = new Product(1, "Sample Product", 100, stock);

        double result = new StockRatioCriteria(1).calculateScore(product);

        assertEquals(expectedRatio, result);
    }

    private static Stream<Arguments> provideStockForVariousScenarios() {
        return Stream.of(
                Arguments.of(Map.of(S_SIZE, 10, M_SIZE, 5, L_SIZE, 0), 2.0 / 3), // Some sizes with stock
                Arguments.of(Map.of(S_SIZE, 0, M_SIZE, 0, L_SIZE, 0), 0.0), // All sizes out of stock
                Arguments.of(Map.of(S_SIZE, 1, M_SIZE, 1, L_SIZE, 1), 1.0), // All sizes in stock
                Arguments.of(Map.of(), 0.0), // No sizes defined
                Arguments.of(Map.of(S_SIZE, -1, M_SIZE, -5, L_SIZE, -10), 0.0) // Negative stock values
        );
    }

}