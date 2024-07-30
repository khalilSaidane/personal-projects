package saidane.khalil.catalog.domain.response;

public record Name(String name) implements IName {
    @Override
    public String getName() {
        return name;
    }
}
