package com.hiberus.caso_practico.adapters.out.persistance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiberus.caso_practico.application.port.out.persistance.ProductRepository;
import com.hiberus.caso_practico.model.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Repository
@Log4j2
public class ProductRepositoryFileStorage implements ProductRepository {
    private final String storageFilePath;
    private final ObjectMapper objectMapper;

    public ProductRepositoryFileStorage(ObjectMapper objectMapper,
                                        @Value("${storage.file}") String productsFilePath) {
        this.objectMapper = objectMapper;
        this.storageFilePath = productsFilePath;
    }

    @Override
    public List<Product> findAllProducts() {
        try {
            ClassPathResource resource = new ClassPathResource(storageFilePath);
            InputStream inputStream = resource.getInputStream();
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            log.warn("Problem reading products from file: {}", storageFilePath, e);
            return Collections.emptyList();
        }
    }
}
