package saidane.khalil.prospect.domain.response;

import lombok.Builder;

@Builder
public record ContactResponse(
        String firstname,
        String lastname,
        String email
) {
}
