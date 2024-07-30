package saidane.khalil.prospect.domain.response;

import lombok.Builder;

@Builder
public record AddressResponse(
        String street,
        Long streetNumber,
        String zipCode,
        String city,
        String country
) {
}
