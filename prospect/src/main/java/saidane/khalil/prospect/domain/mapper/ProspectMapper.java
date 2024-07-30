package saidane.khalil.prospect.domain.mapper;

import saidane.khalil.prospect.domain.response.ProspectResponse;
import saidane.khalil.prospect.model.Prospect;

import static saidane.khalil.prospect.domain.mapper.AddressMapper.buildAddressResponse;
import static saidane.khalil.prospect.domain.mapper.ContactMapper.buildContactResponse;

public final class ProspectMapper {
    private ProspectMapper() {

    }

    public static ProspectResponse buildProspectResponse(Prospect prospect) {
        return ProspectResponse.builder()
                .address(buildAddressResponse(prospect))
                .contact(buildContactResponse(prospect))
                .id(prospect.getId())
                .status(prospect.getStatus())
                .build();
    }
}
