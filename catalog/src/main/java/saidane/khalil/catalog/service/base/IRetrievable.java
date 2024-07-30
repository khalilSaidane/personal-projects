package saidane.khalil.catalog.service.base;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Function;

public interface IRetrievable<T> {
    List<T> findAll();
    <R> List<R> findAll(@NotNull Function<T, R> mapper);
    T findById(Long id);
}
