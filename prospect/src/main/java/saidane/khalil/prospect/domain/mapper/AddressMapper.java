package saidane.khalil.prospect.domain.mapper;

import saidane.khalil.prospect.domain.request.AddressRequest;
import saidane.khalil.prospect.domain.response.AddressResponse;
import saidane.khalil.prospect.model.Prospect;

public final class AddressMapper {

    private AddressMapper() {

    }

    public static AddressResponse buildAddressResponse(Prospect prospect) {
        return AddressResponse.builder()
                .city(prospect.getCity())
                .country(prospect.getCountry())
                .street(prospect.getStreet())
                .zipCode(prospect.getZipCode())
                .streetNumber(prospect.getStreetNumber())
                .build();
    }

    public static Prospect setProspectAddress(Prospect prospect, AddressRequest addressRequest) {
        prospect.setCity(addressRequest.city());
        prospect.setCountry(addressRequest.country());
        prospect.setStreet(addressRequest.street());
        prospect.setStreetNumber(addressRequest.streetNumber());
        prospect.setZipCode(addressRequest.zipCode());
        return prospect;
    }
}
