package saidane.khalil.catalog.domain.request;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

public record CharacteristicRequest(@NotNull String name, @NotNull String value, @Nullable String unit) {
}
