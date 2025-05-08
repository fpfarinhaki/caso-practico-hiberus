package com.hiberus.casopractico.infrastructure.adapters.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hiberus.casopractico.commons.Size;
import com.hiberus.casopractico.infrastructure.adapters.out.persistance.SizeConverter;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_stocks")
public class ProductStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Convert(converter = SizeConverter.class)
    private Size size;

    private Integer quantity;
}
