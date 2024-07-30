package saidane.khalil.catalog.service;

import org.springframework.stereotype.Service;
import saidane.khalil.catalog.domain.request.DiscountRequest;
import saidane.khalil.catalog.domain.response.DiscountResponse;
import saidane.khalil.catalog.model.Discount;
import saidane.khalil.catalog.repository.DiscountRepository;
import saidane.khalil.catalog.service.base.BaseService;
import saidane.khalil.catalog.stream.CatalogUpdated;

import java.util.List;

import static saidane.khalil.catalog.domain.mapper.DiscountMapper.buildDiscountEntity;
import static saidane.khalil.catalog.domain.mapper.DiscountMapper.buildDiscountResponse;

@Service
public class DiscountService extends BaseService<Discount> {


    public DiscountService(DiscountRepository repository, CatalogUpdated catalogUpdated) {
        super(repository, catalogUpdated);
    }

    public DiscountResponse saveDiscount(DiscountRequest discountRequest) {
        return buildDiscountEntity.andThen(this::save)
                .andThen(buildDiscountResponse)
                .apply(discountRequest);
    }

    public List<DiscountResponse> findAllDiscount() {
        return findAll(buildDiscountResponse);
    }
}
