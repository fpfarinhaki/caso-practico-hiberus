package com.hiberus.casopractico.application.port.out.persistance;

import com.hiberus.casopractico.model.Product;

import java.util.List;
import java.util.stream.Stream;

public interface ProductRepository {
    List<Product> findAll();
    Stream<Product> streamAll();
}
