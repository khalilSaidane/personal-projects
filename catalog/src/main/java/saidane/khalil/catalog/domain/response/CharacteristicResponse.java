package saidane.khalil.catalog.domain.response;

import lombok.Builder;

@Builder
public record CharacteristicResponse(Long id, String name, String value, String unit) {
}
