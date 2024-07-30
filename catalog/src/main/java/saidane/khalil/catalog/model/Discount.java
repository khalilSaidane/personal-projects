package saidane.khalil.catalog.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Discount extends BaseModel {
    private String description;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;
    private float percentage;
    @OneToMany(mappedBy = "discount")
    private Set<Product> products;
}
