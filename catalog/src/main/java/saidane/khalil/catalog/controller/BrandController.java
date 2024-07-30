package saidane.khalil.catalog.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import saidane.khalil.catalog.domain.request.BrandRequest;
import saidane.khalil.catalog.domain.response.BrandResponse;
import saidane.khalil.catalog.exception.NotFoundException;
import saidane.khalil.catalog.model.Brand;
import saidane.khalil.catalog.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Api("Brand")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public BrandResponse createBrand(@RequestBody BrandRequest brandRequest) {
        return brandService.save(brandRequest);
    }

    @GetMapping("{id}")
    public Brand getBrandById(@PathVariable Long id) throws NotFoundException {
        return brandService.findById(id);
    }

    @GetMapping
    public List<BrandResponse> getAll() {
        return brandService.getAllBrands();
    }
}
