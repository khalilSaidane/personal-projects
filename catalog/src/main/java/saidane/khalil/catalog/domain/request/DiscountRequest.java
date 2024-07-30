package saidane.khalil.catalog.domain.request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record DiscountRequest(
        String name,
        String description,
        LocalDateTime activeFrom,
        LocalDateTime activeTo,
        float percentage
) {
}
