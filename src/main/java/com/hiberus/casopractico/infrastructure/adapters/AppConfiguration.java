package com.hiberus.casopractico.infrastructure.adapters;

import com.hiberus.casopractico.application.mapping.WeightedSortingCriteriaFactory;
import com.hiberus.casopractico.application.port.in.SortProductUseCase;
import com.hiberus.casopractico.application.port.out.persistance.ProductRepository;
import com.hiberus.casopractico.application.service.SortProductService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.hiberus.casopractico.infrastructure.adapters.out.persistance"
})
@EntityScan(basePackages = {
        "com.hiberus.casopractico.infrastructure.adapters.dto"
})
public class AppConfiguration {

    @Bean
    public WeightedSortingCriteriaFactory weightedSortingCriteriaFactory() {
        return new WeightedSortingCriteriaFactory();
    }

    @Bean
    public SortProductUseCase sortProductService(ProductRepository productRepository,
                                                 WeightedSortingCriteriaFactory weightedSortingCriteriaFactory) {
        return new SortProductService(productRepository, weightedSortingCriteriaFactory);
    }
}
