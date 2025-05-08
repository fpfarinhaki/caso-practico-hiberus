package com.hiberus.casopractico.infrastructure.adapters.out.persistance;

import com.hiberus.casopractico.infrastructure.adapters.dto.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("SELECT p FROM ProductEntity p")
    Stream<ProductEntity> streamAll();
}
