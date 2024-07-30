package saidane.khalil.catalog.utils;

import org.springframework.data.domain.Page;

import java.util.function.Function;

public final class PageResponseMapper {

    private PageResponseMapper() {
    }

    public static <R, T> PageResponse<T> toPageResponse(Page<R> page, Function<R, T> mapper) {
        var mapped = page.getContent().stream().map(mapper).toList();
        return PageResponse.<T>builder()
                .content(mapped)
                .totalElements(page.getTotalElements())
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .build();
    }

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .build();
    }
}
