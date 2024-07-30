package saidane.khalil.prospect.domain.response;

import lombok.Builder;
import saidane.khalil.prospect.model.enumeration.EProspectStatus;

@Builder
public record ProspectResponse(
        Long id,
        EProspectStatus status,
        ContactResponse contact,
        AddressResponse address
) {
}
