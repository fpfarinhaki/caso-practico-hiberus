package com.hiberus.caso_practico.adapters.out.persistance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.caso_practico.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ProductRepositoryFileStorageTest {
    @Test
    void loadsProductsCorrectlyFromJsonFile() {
        ProductRepositoryFileStorage repository =
                new ProductRepositoryFileStorage(new ObjectMapper(), "products.json");

        List<Product> result = repository.findAllProducts();

        Assertions.assertThat(result).isNotEmpty().hasSize(2);
        Assertions.assertThat(result).first().isEqualTo(
                new Product(1, "V-NECH BASIC SHIRT", 100, Map.of("S", 4, "M", 9, "L", 0)));
    }
}