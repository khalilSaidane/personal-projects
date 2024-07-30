package saidane.khalil.catalog.service.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import saidane.khalil.catalog.exception.NotFoundException;
import saidane.khalil.catalog.model.BaseModel;
import saidane.khalil.catalog.stream.CatalogUpdated;

import java.util.List;
import java.util.function.Function;

import static saidane.khalil.catalog.utils.StringUtils.extractClassName;

@RequiredArgsConstructor
@Service
@Slf4j
public abstract class BaseService<T extends BaseModel> implements IRetrievable<T>, IDeletable<T>, ISavable<T> {

    protected final JpaRepository<T, Long> repository;

    private final CatalogUpdated catalogUpdated;

    protected String getClassName() {
        String fullClassName = getClass().getGenericSuperclass().getTypeName();
        return extractClassName(fullClassName);
    }


    @Override
    public T findById(Long id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id, getClassName()));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        var savedEntity = repository.save(entity);
        log.info(String.format("[%sId=%s] Saved successfully", getClassName(), savedEntity.getId()));
        catalogUpdated.invalidateCache(entity);
        return savedEntity;
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
        catalogUpdated.invalidateCache(entity);
        log.info(String.format("[%sId=%s] Deleted with successfully", getClassName(), entity.getId()));
    }

    @Override
    public <R> List<R> findAll(Function<T, R> mapper) {
        return findAll().stream()
                .map(mapper)
                .toList();
    }

}
