package saidane.khalil.catalog.domain.response;

import lombok.Builder;

@Builder
public record BrandResponse(String name, Long id) {
}
