package saidane.khalil.catalog.domain.request;

import java.util.Set;

public record ProductRequest(
        Long id,
        String name,
        String description,
        String characteristics,
        float price,
        Long category,
        Long brandId
) {
}