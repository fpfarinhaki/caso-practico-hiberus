package com.hiberus.casopractico.core.domain.criteria;

import com.hiberus.casopractico.application.criteria.SalesUnitsCriteria;
import com.hiberus.casopractico.model.Product;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SalesUnitsCriteriaTest {
    @ParameterizedTest
    @MethodSource("provideProductsForSalesUnitsCriteria")
    void calculatesCorrectScoreBasedOnSalesUnits(Product product, double expectedScore) {
        SalesUnitsCriteria criteria = new SalesUnitsCriteria(1);

        double result = criteria.calculateScore(product);

        assertEquals(expectedScore, result);
    }

    private static Stream<Arguments> provideProductsForSalesUnitsCriteria() {
        return Stream.of(
                Arguments.of(new Product(1, "Product A", 100, Map.of("S", 10, "M", 5, "L", 0)), 100.0),
                Arguments.of(new Product(2, "Product B", 0, Map.of("S", 0, "M", 0, "L", 0)), 0.0),
                Arguments.of(new Product(3, "Product C", -10, Map.of("S", 5, "M", 0, "L", 0)), 0.0)
        );
    }

}