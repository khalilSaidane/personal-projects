package saidane.khalil.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel {
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category(String name, String description) {
        super(name);
        this.description = description;
    }
}
