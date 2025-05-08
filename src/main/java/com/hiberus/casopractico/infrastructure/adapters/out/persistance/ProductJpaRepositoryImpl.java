package com.hiberus.casopractico.infrastructure.adapters.out.persistance;

import com.hiberus.casopractico.application.port.out.persistance.ProductRepository;
import com.hiberus.casopractico.infrastructure.adapters.dto.ProductEntity;
import com.hiberus.casopractico.model.Product;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class ProductJpaRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    public ProductJpaRepositoryImpl(@Lazy ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll().stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public Stream<Product> streamAll() {
        return productJpaRepository.streamAll().map(ProductEntity::toDomain);
    }
}
