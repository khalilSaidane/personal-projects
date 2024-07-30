package saidane.khalil.catalog.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import saidane.khalil.catalog.domain.request.CategoryRequest;
import saidane.khalil.catalog.domain.response.CategoryResponse;
import saidane.khalil.catalog.model.Category;
import saidane.khalil.catalog.repository.CategoryRepository;
import saidane.khalil.catalog.service.base.BaseService;
import saidane.khalil.catalog.stream.CatalogUpdated;
import saidane.khalil.catalog.utils.PageResponse;

import javax.transaction.Transactional;
import java.util.List;

import static saidane.khalil.catalog.domain.mapper.CategoryMapper.buildCategoryEntity;
import static saidane.khalil.catalog.domain.mapper.CategoryMapper.buildCategoryResponse;
import static saidane.khalil.catalog.utils.PageResponseMapper.toPageResponse;

@Service
public class CategoryService extends BaseService<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository repository, CatalogUpdated catalogUpdated, CategoryRepository categoryRepository) {
        super(repository, catalogUpdated);
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        return buildCategoryEntity.andThen(this::save)
                .andThen(buildCategoryResponse)
                .apply(categoryRequest);
    }

    public List<CategoryResponse> getAllCategories() {
        return findAll(buildCategoryResponse);
    }

    public PageResponse<CategoryResponse> getPaginatedCategories(Pageable pageable) {
        var categories = categoryRepository.findAll(pageable);
        return toPageResponse(categories, buildCategoryResponse);
    }

    @Transactional
    public void deleteAllByNameIn(List<String> names) {
        categoryRepository.deleteAllByNameIn(names);
    }

}
