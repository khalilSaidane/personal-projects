package saidane.khalil.prospect.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saidane.khalil.prospect.connector.cart.CartClient;
import saidane.khalil.prospect.domain.request.AddressRequest;
import saidane.khalil.prospect.domain.request.ContactRequest;
import saidane.khalil.prospect.domain.response.AddressResponse;
import saidane.khalil.prospect.domain.response.ContactResponse;
import saidane.khalil.prospect.domain.response.ProspectResponse;
import saidane.khalil.prospect.exception.NotFoundException;
import saidane.khalil.prospect.model.Prospect;
import saidane.khalil.prospect.model.enumeration.EProspectStatus;
import saidane.khalil.prospect.repository.ProspectRepository;

import javax.transaction.Transactional;

import static saidane.khalil.prospect.domain.mapper.AddressMapper.buildAddressResponse;
import static saidane.khalil.prospect.domain.mapper.AddressMapper.setProspectAddress;
import static saidane.khalil.prospect.domain.mapper.ContactMapper.buildContactResponse;
import static saidane.khalil.prospect.domain.mapper.ContactMapper.setProspectContact;
import static saidane.khalil.prospect.domain.mapper.ProspectMapper.buildProspectResponse;


@Service
@RequiredArgsConstructor
public class ProspectService {

    private final ProspectRepository prospectRepository;
    private final CartClient cartClient;

    @Transactional
    public Long initProspect() {
        var prospect = prospectRepository.save(new Prospect());
        var cart = cartClient.createCart(prospect.getId());
        prospect.setCartId(cart.id());
        prospectRepository.save(prospect);
        return prospect.getId();
    }

    public Prospect findOpenProspectById(Long prospectId) {
        return prospectRepository.findByIdAndStatus(prospectId, EProspectStatus.OPEN)
                .orElseThrow(() -> new NotFoundException(prospectId, "Prospect"));
    }

    @Transactional
    public AddressResponse putAddress(AddressRequest address, Long prospectId) {
        var prospect = setProspectAddress(
                findOpenProspectById(prospectId),
                address
        );
        return buildAddressResponse(prospect);
    }

    @Transactional
    public ContactResponse putContact(ContactRequest contact, Long prospectId) {
        var prospect = setProspectContact(
                findOpenProspectById(prospectId),
                contact
        );
        return buildContactResponse(prospect);
    }

    public ProspectResponse getProspect(Long id) {
        var prospect = findOpenProspectById(id);
        return buildProspectResponse(prospect);
    }

    public void checkout(Long id) {
        var prospect = findOpenProspectById(id);
        prospect.setStatus(EProspectStatus.CHECKOUT);
        prospectRepository.save(prospect);
    }

}
