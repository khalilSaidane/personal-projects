package saidane.khalil.catalog.service;

import org.springframework.stereotype.Service;
import saidane.khalil.catalog.domain.request.BrandRequest;
import saidane.khalil.catalog.domain.response.BrandResponse;
import saidane.khalil.catalog.model.Brand;
import saidane.khalil.catalog.repository.BrandRepository;
import saidane.khalil.catalog.service.base.BaseService;
import saidane.khalil.catalog.stream.CatalogUpdated;

import java.util.List;

import static saidane.khalil.catalog.domain.mapper.BrandMapper.buildBrandEntity;
import static saidane.khalil.catalog.domain.mapper.BrandMapper.buildBrandResponse;

@Service
public class BrandService extends BaseService<Brand> {


    public BrandService(BrandRepository repository, CatalogUpdated catalogUpdated) {
        super(repository, catalogUpdated);
    }

    public BrandResponse save(BrandRequest brandRequest) {
        return buildBrandEntity.andThen(this::save)
                .andThen(buildBrandResponse)
                .apply(brandRequest);
    }

    public List<BrandResponse> getAllBrands() {
        return findAll(buildBrandResponse);
    }

}
