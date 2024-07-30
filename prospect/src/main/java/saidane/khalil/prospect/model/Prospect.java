package saidane.khalil.prospect.model;

import lombok.*;
import saidane.khalil.prospect.model.enumeration.EProspectStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long cartId;
    @Enumerated(EnumType.STRING)
    private EProspectStatus status;
    private String street;
    private Long streetNumber;
    private String zipCode;
    private String city;
    private String country;
    private String firstname;
    private String lastname;
    private String email;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = EProspectStatus.OPEN;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
