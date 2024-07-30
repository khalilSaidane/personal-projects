package saidane.khalil.catalog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
public class Image extends BaseModel {
    private Long weight;
    private String url;
    private ImageType imageType;
    @ManyToOne
    private Product product;

    public enum ImageType {
        FULL_SIZE,
        THUMB_NAIL
    }
}
