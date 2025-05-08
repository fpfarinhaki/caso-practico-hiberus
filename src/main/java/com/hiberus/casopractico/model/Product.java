package com.hiberus.casopractico.model;

import java.util.Map;

public record Product(Integer id, String name, Integer salesUnits, Map<String, Integer> stock) {
}
