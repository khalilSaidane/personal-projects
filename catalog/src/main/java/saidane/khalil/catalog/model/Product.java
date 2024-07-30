package saidane.khalil.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product extends BaseModel {
    private String description;
    private float price;
    @ManyToOne
    @JsonIgnore
    private Category category;
    @ManyToOne
    @Nullable
    @JsonIgnore
    private Brand brand;
    @ManyToOne
    @Nullable
    @JsonIgnore
    private Discount discount;
    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Characteristic> characteristics;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Image> images = new HashSet<>();
}
