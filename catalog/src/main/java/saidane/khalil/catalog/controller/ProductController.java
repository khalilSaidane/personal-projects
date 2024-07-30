package saidane.khalil.catalog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import saidane.khalil.catalog.domain.enumeration.ProductResponseTypeEnum;
import saidane.khalil.catalog.domain.request.ImageRequest;
import saidane.khalil.catalog.domain.request.ProductRequest;
import saidane.khalil.catalog.domain.response.IName;
import saidane.khalil.catalog.domain.response.ProductResponse;
import saidane.khalil.catalog.exception.NotFoundException;
import saidane.khalil.catalog.model.Image;
import saidane.khalil.catalog.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Api("Product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Long crateProduct(@RequestBody ProductRequest productRequest) throws NotFoundException {
        return productService.saveProduct(productRequest);
    }

    @GetMapping()
    public List<? extends IName> getAllProducts(@RequestParam ProductResponseTypeEnum responseTypeEnum) {
        return productService.getAllProducts(responseTypeEnum);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductResponse addImageToProduct(
            @PathVariable Long id,
            @RequestParam("imageType") Image.ImageType imageType,
            @RequestParam("weight") Long weight,
            @RequestPart MultipartFile file
    ) {
        return productService.addImageToProduct(id, imageType, weight, file);
    }
}
