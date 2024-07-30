package saidane.khalil.catalog.domain.response;

import lombok.Builder;
import org.springframework.lang.Nullable;
import saidane.khalil.catalog.model.Image;

@Builder
public record ImageResponse(
        String url,
        Image.ImageType type,
        @Nullable
        Long weight
) {
}
