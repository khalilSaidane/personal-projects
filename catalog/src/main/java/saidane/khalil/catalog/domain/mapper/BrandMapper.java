package saidane.khalil.catalog.domain.mapper;

import saidane.khalil.catalog.domain.request.BrandRequest;
import saidane.khalil.catalog.domain.response.BrandResponse;
import saidane.khalil.catalog.model.Brand;
import saidane.khalil.catalog.model.Product;

import java.util.function.Function;

import static java.util.Optional.ofNullable;

public final class BrandMapper {

    private BrandMapper() {
    }

    public static final Function<BrandRequest, Brand> buildBrandEntity =
            brandRequest -> new Brand(brandRequest.name());

    public static final Function<Brand, BrandResponse> buildBrandResponse =
            brand -> BrandResponse.builder()
                    .name(brand.getName())
                    .id(brand.getId())
                    .build();

    public static String getBrandNames(Product product) {
        return ofNullable(product.getBrand())
                .map(Brand::getName)
                .orElse(null);
    }
}
