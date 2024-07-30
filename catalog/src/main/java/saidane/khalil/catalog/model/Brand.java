package saidane.khalil.catalog.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
public class Brand extends BaseModel {
    @OneToMany(mappedBy = "brand")
    private Set<Product> products;

    public Brand() {
        super();
    }

    public Brand(String name) {
        super(name);
    }
}
