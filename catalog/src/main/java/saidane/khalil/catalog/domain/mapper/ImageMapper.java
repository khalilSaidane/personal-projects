package saidane.khalil.catalog.domain.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;
import saidane.khalil.catalog.domain.request.ImageRequest;
import saidane.khalil.catalog.domain.response.ImageResponse;
import saidane.khalil.catalog.model.Image;
import saidane.khalil.catalog.model.Product;

import java.util.Map;
import java.util.function.BiFunction;

import static java.util.Optional.ofNullable;

public final class ImageMapper {
    private ImageMapper() {
    }

    public static final BiFunction<ImageRequest, Product, Image> buildImageEntity = (request, product) ->
            Image.builder()
                    .imageType(request.type())
                    .weight(request.weight())
                    .url(request.url())
                    .product(product)
                    .build();

    public static ImageResponse buildImageResponse(Image image) {
        return ImageResponse.builder()
                .type(image.getImageType())
                .url(image.getUrl())
                .weight(image.getWeight())
                .build();
    }

    public static ImageRequest buildImageRequest(Long productId, Image.ImageType type, Long weight, MultipartFile file) {
        return ImageRequest.builder()
                .type(type)
                .weight(weight)
                .url(file.getOriginalFilename())
                .productId(productId)
                .build();
    }

    @NotNull
    public static Map<String, String> buildMetadata(Long productId, Image.ImageType type, Long weight) {
        return Map.of(
                "weight", ofNullable(weight).orElse(0L).toString(),
                "type", type.toString(),
                "productId", productId.toString()
        );
    }
}
