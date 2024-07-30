package saidane.khalil.catalog.utils;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResponse<T>(
        List<T> content,

        int page,
        int pageSize,
        long totalPages,
        long totalElements
) {
}

