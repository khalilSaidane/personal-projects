package saidane.khalil.catalog.domain.enumeration;

import saidane.khalil.catalog.domain.response.IName;
import saidane.khalil.catalog.model.Product;

import java.util.function.Function;

import static saidane.khalil.catalog.domain.mapper.ProductMapper.*;

public enum ProductResponseTypeEnum {
    FULL(buildProductResponse),
    NAMES(buildProductName),
    PARTIAL(buildPartialProduct);

    public final Function<Product, ? extends IName> mapper;

    <T extends IName> ProductResponseTypeEnum(Function<Product, T> mapper) {
        this.mapper = mapper;
    }
}
