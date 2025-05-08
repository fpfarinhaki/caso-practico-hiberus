package com.hiberus.caso_practico;

import com.hiberus.caso_practico.application.port.in.SortProductUseCase;
import com.hiberus.caso_practico.application.port.out.persistance.ProductRepository;
import com.hiberus.caso_practico.application.service.SortProductService;
import com.hiberus.caso_practico.application.mapping.WeightedSortingCriteriaFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SortProductUseCase sortProductUseCase(ProductRepository productRepository) {
        return new SortProductService(productRepository, new WeightedSortingCriteriaFactory());
    }
}
