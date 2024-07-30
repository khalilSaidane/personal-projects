package saidane.khalil.catalog.service.base;

@FunctionalInterface
public interface ISavable<T> {
    T save(T entity);
}
