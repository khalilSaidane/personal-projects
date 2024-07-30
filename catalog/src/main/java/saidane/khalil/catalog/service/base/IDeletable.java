package saidane.khalil.catalog.service.base;

@FunctionalInterface
public interface IDeletable<T> {
     void delete(T entity);
}
