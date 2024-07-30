package saidane.khalil.catalog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import saidane.khalil.catalog.connector.DocumentClient;
import saidane.khalil.catalog.model.Image;
import saidane.khalil.catalog.model.Product;
import saidane.khalil.catalog.repository.ImageRepository;
import saidane.khalil.catalog.service.base.BaseService;
import saidane.khalil.catalog.stream.CatalogUpdated;

import static saidane.khalil.catalog.domain.mapper.ImageMapper.*;


@Service
public class ImageService extends BaseService<Image> {

    private final DocumentClient documentClient;

    public ImageService(ImageRepository repository, CatalogUpdated catalogUpdated, DocumentClient documentClient) {
        super(repository, catalogUpdated);
        this.documentClient = documentClient;
    }

    public Image saveImage(MultipartFile file, Product product, Image.ImageType type, Long weight) {
        var metadata = buildMetadata(product.getId(), type, weight);
        var image = buildImageRequest(product.getId(), type, weight, file);
        documentClient.upload(file, file.getOriginalFilename(), file.getContentType(), metadata);
        return buildImageEntity.andThen(this::save).apply(image,product);
    }
}
