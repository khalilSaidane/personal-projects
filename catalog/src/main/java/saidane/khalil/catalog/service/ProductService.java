package saidane.khalil.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import saidane.khalil.catalog.domain.enumeration.ProductResponseTypeEnum;
import saidane.khalil.catalog.domain.request.ProductRequest;
import saidane.khalil.catalog.domain.response.IName;
import saidane.khalil.catalog.domain.response.ProductResponse;
import saidane.khalil.catalog.exception.NotFoundException;
import saidane.khalil.catalog.model.Image;
import saidane.khalil.catalog.model.Product;
import saidane.khalil.catalog.repository.ProductRepository;
import saidane.khalil.catalog.service.base.BaseService;
import saidane.khalil.catalog.stream.CatalogUpdated;

import javax.transaction.Transactional;
import java.util.List;

import static saidane.khalil.catalog.domain.mapper.ProductMapper.buildProductEntity;
import static saidane.khalil.catalog.domain.mapper.ProductMapper.buildProductResponse;

@Service
public class ProductService extends BaseService<Product> {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    public ProductService(ProductRepository repository, CatalogUpdated catalogUpdated, ProductRepository productRepository, BrandService brandService, CategoryService categoryService, ImageService imageService) {
        super(repository, catalogUpdated);
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    public ProductResponse getProductById(Long id) throws NotFoundException {
        return buildProductResponse.compose(this::findById).apply(id);
    }

    public List<? extends IName> getAllProducts(ProductResponseTypeEnum responseTypeEnum) {
        return findAll(responseTypeEnum.mapper);
    }

    public List<ProductResponse> getProductByPriceRange(float min, float max) {
        return productRepository.findAllWithPriceRange(min, max).stream()
                .map(buildProductResponse)
                .toList();
    }

    public Long saveProduct(ProductRequest productRequest) throws NotFoundException {
        var brand = brandService.findById(productRequest.brandId());
        var category = categoryService.findById(productRequest.category());
        var productEntity = buildProductEntity(brand, category, productRequest);
        return save(productEntity).getId();
    }

    @Transactional
    public ProductResponse addImageToProduct(Long productId, Image.ImageType type, Long weight, MultipartFile file) {
        var product = findById(productId);
        var imageEntity = imageService.saveImage(file, product, type, weight);
        product.getImages().add(imageEntity);
        return buildProductResponse.compose(this::save).apply(product);
    }

}
