package com.hiberus.caso_practico.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record Product(Integer id, String name, @JsonProperty("sales_units") Integer salesUnits, Map<String, Integer> stock) {
}
