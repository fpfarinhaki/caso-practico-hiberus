package com.hiberus.casopractico;

import com.hiberus.casopractico.application.mapping.WeightedSortingCriteriaFactory;
import com.hiberus.casopractico.application.port.out.persistance.ProductRepository;
import com.hiberus.casopractico.application.service.SortProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CasoPracticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasoPracticoApplication.class, args);
	}

}
