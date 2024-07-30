package saidane.khalil.catalog.domain.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DiscountResponse(
        String name,
        String description,
        float percentage,
        LocalDateTime activeFrom,
        LocalDateTime activeTo) {
}
