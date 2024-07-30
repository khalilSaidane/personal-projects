package saidane.khalil.catalog.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import saidane.khalil.catalog.domain.request.CharacteristicRequest;
import saidane.khalil.catalog.domain.response.CharacteristicResponse;
import saidane.khalil.catalog.service.CharacteristicService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characteristic")
@Api("Characteristic")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @GetMapping("/{id}")
    public CharacteristicResponse getById(@PathVariable Long id) {
        return characteristicService.getCharacteristicById(id);
    }

    @PostMapping
    public Long save(@RequestBody CharacteristicRequest request) {
        return characteristicService.saveCharacteristic(request);
    }
}
