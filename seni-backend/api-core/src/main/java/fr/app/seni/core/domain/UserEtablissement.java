package fr.app.seni.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_etablissement")
public class UserEtablissement extends AbstractAuditingEntity{
    @EmbeddedId
    private UserEtablissementId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_user", nullable = false)
    private AppUser appUser;

    @MapsId("idEtablissemnt")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_etablissemnt", nullable = false)
    private Etablissement etablissemnt;

}