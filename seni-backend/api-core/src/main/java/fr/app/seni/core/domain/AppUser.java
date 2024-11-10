package fr.app.seni.core.domain;

import fr.app.seni.core.enums.AppUserGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "app_user")
public class AppUser extends AbstractAuditingEntity {
    @Id
    @Column(name = "id_user", nullable = false, length = 254)
    private String idUser;

    @Column(name = "username", nullable = false, length = 254)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupe", nullable = false, length = 254)
    private AppUserGroup groupe;

    @Column(name = "first_name", nullable = false, length = 254)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 254)
    private String lastName;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "telephone", nullable = false, length = 254)
    private String telephone;

    @Column(name = "image_url", length = 254)
    private String imageUrl;

    @Column(name = "enabled")
    private Boolean enabled;

}