package saidane.khalil.catalog.domain.mapper;

import saidane.khalil.catalog.domain.request.DiscountRequest;
import saidane.khalil.catalog.domain.response.DiscountResponse;
import saidane.khalil.catalog.model.Discount;

import java.util.function.Function;

public final class DiscountMapper {

    private DiscountMapper() {
    }

    public static final Function<DiscountRequest, Discount> buildDiscountEntity =
            request -> Discount.builder()
                    .description(request.description())
                    .percentage(request.percentage())
                    .activeTo(request.activeTo())
                    .activeFrom(request.activeFrom())
                    .name(request.name())
                    .build();

    public static final Function<Discount, DiscountResponse> buildDiscountResponse =
            entity -> DiscountResponse.builder()
                    .name(entity.getName())
                    .activeFrom(entity.getActiveFrom())
                    .activeTo(entity.getActiveTo())
                    .description(entity.getDescription())
                    .build();
}
