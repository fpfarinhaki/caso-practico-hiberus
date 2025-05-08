package com.hiberus.casopractico.adapters.dto;

import com.hiberus.casopractico.commons.Size;
import com.hiberus.casopractico.infrastructure.adapters.dto.ProductEntity;
import com.hiberus.casopractico.infrastructure.adapters.dto.ProductStockEntity;
import com.hiberus.casopractico.model.Product;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {
    @Test
    void toDomain_ShouldMapCorrectly() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setName("V-NECK BASIC SHIRT");
        productEntity.setSalesUnits(100);

        Set<ProductStockEntity> stocks = new HashSet<>();
        stocks.add(createStock(Size.SMALL, 4));
        stocks.add(createStock(Size.MEDIUM, 9));
        stocks.add(createStock(Size.LARGE, 0));
        productEntity.setStocks(stocks);

        // Act
        Product product = productEntity.toDomain();

        // Assert
        assertAll(
                () -> assertEquals(1, product.id()),
                () -> assertEquals("V-NECK BASIC SHIRT", product.name()),
                () -> assertEquals(100, product.salesUnits()),
                () -> assertEquals(4, product.stock().get("S")),
                () -> assertEquals(9, product.stock().get("M")),
                () -> assertEquals(0, product.stock().get("L"))
        );
    }

    private ProductStockEntity createStock(Size size, int quantity) {
        ProductStockEntity stock = new ProductStockEntity();
        stock.setSize(size);
        stock.setQuantity(quantity);
        return stock;
    }

}