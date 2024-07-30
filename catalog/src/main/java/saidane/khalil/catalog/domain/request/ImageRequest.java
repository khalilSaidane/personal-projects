package saidane.khalil.catalog.domain.request;

import lombok.Builder;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import saidane.khalil.catalog.model.Image;

@Builder
public record ImageRequest(
        String url,
        Image.ImageType type,
        @Nullable
        Long weight,
        Long productId
) {
}
