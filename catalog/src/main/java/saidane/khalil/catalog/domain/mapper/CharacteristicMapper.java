package saidane.khalil.catalog.domain.mapper;

import saidane.khalil.catalog.domain.request.CharacteristicRequest;
import saidane.khalil.catalog.domain.response.CharacteristicResponse;
import saidane.khalil.catalog.model.Characteristic;

import java.util.function.Function;

public final class CharacteristicMapper {
    private CharacteristicMapper() {
    }

    public static final Function<CharacteristicRequest, Characteristic> buildCharacteristicEntity =
            request -> Characteristic.builder()
                    .value(request.value())
                    .unit(request.unit())
                    .name(request.name())
                    .build();

    public static final Function<Characteristic, CharacteristicResponse> buildCharacteristicResponse =
            entity -> CharacteristicResponse.builder()
                    .value(entity.getValue())
                    .name(entity.getName())
                    .unit(entity.getUnit())
                    .id(entity.getId())
                    .build();
}
