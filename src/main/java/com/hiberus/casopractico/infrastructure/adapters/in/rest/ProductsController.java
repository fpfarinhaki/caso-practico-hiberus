package com.hiberus.casopractico.infrastructure.adapters.in.rest;

import com.hiberus.casopractico.application.port.in.SortProductUseCase;
import com.hiberus.casopractico.infrastructure.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.casopractico.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Log4j2
public class ProductsController {
    private final SortProductUseCase sortProductService;

    public ProductsController(SortProductUseCase sortProductService) {
        this.sortProductService = sortProductService;
    }

    @Operation(
            summary = "Sort products by multiple weighted criteria",
            description = "Sorts products using a combination of criteria where each has an assigned weight. " +
                    "Valid criteria names are: 'salesUnits', 'stockRatio'."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Products sorted successfully",
            content = @Content(schema = @Schema(implementation = Product.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid criteria name or weight",
            content = @Content
    )
    @PostMapping("/sort")
    public ResponseEntity<List<Product>> getSortedProducts(@RequestBody List<WeightedSortingCriteriaDto> sortingCriteria) {
        try {
            return ResponseEntity.ok(sortProductService.sort(sortingCriteria));
        } catch (IllegalArgumentException e) {
            log.error("Error sorting products. Invalid criteria name or weight");
            return ResponseEntity.badRequest().build();
        }
    }
}
