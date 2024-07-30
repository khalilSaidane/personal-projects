package saidane.khalil.catalog.domain.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        String name,
        String description,
        float price,
        String category,
        String brand,
        List<ImageResponse> images
) implements IName {
    @Override
    public String getName() {
        return name;
    }
}
