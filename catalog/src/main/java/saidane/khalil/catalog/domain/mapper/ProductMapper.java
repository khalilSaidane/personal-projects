package saidane.khalil.catalog.domain.mapper;

import org.jetbrains.annotations.NotNull;
import saidane.khalil.catalog.domain.request.ProductRequest;
import saidane.khalil.catalog.domain.response.ImageResponse;
import saidane.khalil.catalog.domain.response.Name;
import saidane.khalil.catalog.domain.response.PartialProductResponse;
import saidane.khalil.catalog.domain.response.ProductResponse;
import saidane.khalil.catalog.model.Brand;
import saidane.khalil.catalog.model.Category;
import saidane.khalil.catalog.model.Product;

import java.util.List;
import java.util.function.Function;

import static saidane.khalil.catalog.domain.mapper.BrandMapper.getBrandNames;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static final Function<Product, ProductResponse> buildProductResponse =
            product -> ProductResponse.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .brand(getBrandNames(product))
                    .category(product.getCategory().getName())
                    .images(getImages(product))
                    .build();

    @NotNull
    private static List<ImageResponse> getImages(Product product) {
        return product.getImages().stream()
                .map(ImageMapper::buildImageResponse)
                .toList();
    }

    public static final Function<Product, Name> buildProductName =
            product -> new Name(product.getName());

    public static Product buildProductEntity(Brand brand, Category category, ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .category(category)
                .brand(brand)
                .build();
    }

    public static final Function<Product, PartialProductResponse> buildPartialProduct =
            product -> PartialProductResponse.builder()
                    .description(product.getDescription())
                    .name(product.getName())
                    .build();
}