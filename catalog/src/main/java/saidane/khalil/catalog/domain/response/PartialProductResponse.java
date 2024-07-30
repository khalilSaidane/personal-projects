package saidane.khalil.catalog.domain.response;

import lombok.Builder;

@Builder
public record PartialProductResponse(String name, String description) implements IName {
    @Override
    public String getName() {
        return name;
    }
}
