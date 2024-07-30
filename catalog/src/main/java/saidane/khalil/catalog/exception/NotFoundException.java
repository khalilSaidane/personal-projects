package saidane.khalil.catalog.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String entityName) {
        super(String.format("[%sId=%s] Not found", entityName, id));
    }
}
