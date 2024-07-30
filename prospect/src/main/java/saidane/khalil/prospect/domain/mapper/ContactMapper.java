package saidane.khalil.prospect.domain.mapper;

import saidane.khalil.prospect.domain.request.ContactRequest;
import saidane.khalil.prospect.domain.response.ContactResponse;
import saidane.khalil.prospect.model.Prospect;

public final class ContactMapper {

    private ContactMapper() {

    }

    public static ContactResponse buildContactResponse(Prospect prospect) {
        return ContactResponse.builder()
                .email(prospect.getEmail())
                .firstname(prospect.getFirstname())
                .lastname(prospect.getLastname())
                .build();
    }

    public static Prospect setProspectContact(Prospect prospect, ContactRequest request) {
        prospect.setFirstname(request.firstname());
        prospect.setLastname(request.lastname());
        prospect.setEmail(request.email());
        return prospect;
    }
}
