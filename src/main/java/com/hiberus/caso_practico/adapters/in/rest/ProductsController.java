package com.hiberus.caso_practico.adapters.in.rest;

import com.hiberus.caso_practico.application.mapping.WeightedSortingCriteriaFactory;
import com.hiberus.caso_practico.adapters.dto.WeightedSortingCriteriaDto;
import com.hiberus.caso_practico.model.Product;
import com.hiberus.caso_practico.application.port.in.SortProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Log4j2
public class ProductsController {
    private final SortProductUseCase sortProductUseCase;

    public ProductsController(SortProductUseCase sortProductUseCase) {
        this.sortProductUseCase = sortProductUseCase;
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
            return ResponseEntity.ok(sortProductUseCase.sort(sortingCriteria));
        } catch (IllegalArgumentException e) {
            log.error("Error sorting products. Invalid criteria name or weight");
            return ResponseEntity.badRequest().build();
        }
    }
}
