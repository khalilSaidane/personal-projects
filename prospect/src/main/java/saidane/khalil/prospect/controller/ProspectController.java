package saidane.khalil.prospect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import saidane.khalil.prospect.domain.request.AddressRequest;
import saidane.khalil.prospect.domain.request.ContactRequest;
import saidane.khalil.prospect.domain.response.AddressResponse;
import saidane.khalil.prospect.domain.response.ContactResponse;
import saidane.khalil.prospect.domain.response.ProspectResponse;
import saidane.khalil.prospect.service.ProspectService;

@RestController
@RequestMapping("/prospect")
@RequiredArgsConstructor
public class ProspectController {
    private final ProspectService prospectService;

    @PostMapping
    public Long initProspect() {
        return prospectService.initProspect();
    }

    @PutMapping("/address/{prospectId}")
    public AddressResponse putAddress(@RequestBody AddressRequest request, @PathVariable Long prospectId) {
        return prospectService.putAddress(request, prospectId);
    }

    @PutMapping("/contact/{prospectId}")
    public ContactResponse putContact(@RequestBody ContactRequest request, @PathVariable Long prospectId) {
        return prospectService.putContact(request, prospectId);
    }

    @GetMapping("/{prospectId}")
    public ProspectResponse getProspect(@PathVariable Long prospectId) {
        return prospectService.getProspect(prospectId);
    }

    @PutMapping("/{prospectId}")
    public void checkout(@PathVariable Long prospectId) {
        prospectService.checkout(prospectId);
    }

}
