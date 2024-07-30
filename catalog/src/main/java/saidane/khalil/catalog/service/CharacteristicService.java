package saidane.khalil.catalog.service;

import org.springframework.stereotype.Service;
import saidane.khalil.catalog.domain.request.CharacteristicRequest;
import saidane.khalil.catalog.domain.response.CharacteristicResponse;
import saidane.khalil.catalog.model.Characteristic;
import saidane.khalil.catalog.repository.CharacteristicRepository;
import saidane.khalil.catalog.service.base.BaseService;
import saidane.khalil.catalog.stream.CatalogUpdated;

import static saidane.khalil.catalog.domain.mapper.CharacteristicMapper.buildCharacteristicEntity;
import static saidane.khalil.catalog.domain.mapper.CharacteristicMapper.buildCharacteristicResponse;

@Service
public class CharacteristicService extends BaseService<Characteristic> {

    public CharacteristicService(CharacteristicRepository repository, CatalogUpdated catalogUpdated) {
        super(repository, catalogUpdated);
    }

    public Long saveCharacteristic(CharacteristicRequest request) {
        return buildCharacteristicEntity.andThen(this::save)
                .andThen(Characteristic::getId)
                .apply(request);
    }

    public CharacteristicResponse getCharacteristicById(Long id) {
        return buildCharacteristicResponse.compose(this::findById).apply(id);
    }

}
