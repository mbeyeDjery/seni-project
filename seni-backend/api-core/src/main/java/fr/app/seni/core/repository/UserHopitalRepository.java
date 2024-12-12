package fr.app.seni.core.repository;

import fr.app.seni.core.domain.UserHopital;
import fr.app.seni.core.domain.UserHopitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserHopitalRepository extends JpaRepository<UserHopital, UserHopitalId> {

    @Query("from UserHopital u where u.id.idUser = ?1")
    UserHopital findByIdUser(String idUser);

    @Query("from UserHopital u where u.id.idHopital = ?1")
    List<UserHopital> findByHopital(String idHopital);
}