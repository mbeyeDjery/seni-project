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
@Table(name = "user_hopital")
public class UserHopital {
    @EmbeddedId
    private UserHopitalId id;

    @MapsId("idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_user", nullable = false)
    @ToString.Exclude
    private AppUser user;

    @MapsId("idHopital")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_hopital", nullable = false)
    @ToString.Exclude
    private Hopital hopital;

}