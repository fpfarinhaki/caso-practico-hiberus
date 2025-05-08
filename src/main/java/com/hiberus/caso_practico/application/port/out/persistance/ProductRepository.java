package com.hiberus.caso_practico.application.port.out.persistance;

import com.hiberus.caso_practico.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
}
