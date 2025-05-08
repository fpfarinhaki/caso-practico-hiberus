package com.hiberus.casopractico.infrastructure.adapters.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hiberus.casopractico.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer salesUnits;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Set<ProductStockEntity> stocks;

    public ProductEntity() {
    }

    public Product toDomain() {
        Map<String, Integer> stockMap = stocks.stream()
                .collect(Collectors.toMap(
                        stock -> stock.getSize().getCode(),
                        ProductStockEntity::getQuantity
                ));
        return new Product(id, name, salesUnits, stockMap);
    }

}
