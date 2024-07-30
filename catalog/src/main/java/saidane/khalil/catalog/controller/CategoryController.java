package saidane.khalil.catalog.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import saidane.khalil.catalog.domain.request.CategoryRequest;
import saidane.khalil.catalog.domain.response.CategoryResponse;
import saidane.khalil.catalog.service.CategoryService;
import saidane.khalil.catalog.utils.PageResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Api("Category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/paged")
    public PageResponse<CategoryResponse> getPaginatedCategories(@RequestParam int page, @RequestParam int size) {
        return categoryService.getPaginatedCategories(PageRequest.of(page, size));
    }

    @DeleteMapping
    public void deleteAllByIdIn(@RequestBody List<String> names) {
        categoryService.deleteAllByNameIn(names);
    }

}
