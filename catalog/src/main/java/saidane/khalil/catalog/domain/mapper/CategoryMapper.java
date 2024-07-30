package saidane.khalil.catalog.domain.mapper;

import saidane.khalil.catalog.domain.request.CategoryRequest;
import saidane.khalil.catalog.domain.response.CategoryResponse;
import saidane.khalil.catalog.model.Category;

import java.util.function.Function;

public final class CategoryMapper {
    private CategoryMapper() {
    }

    public static final Function<CategoryRequest, Category> buildCategoryEntity =
            categoryRequest -> new Category(
                    categoryRequest.name(),
                    categoryRequest.description()
            );

    public static final Function<Category, CategoryResponse> buildCategoryResponse =
            category -> new CategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getDescription()
            );

}
