package fr.app.seni.core.repository;

import fr.app.seni.core.domain.NotationHopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotationHopitalRepository extends JpaRepository<NotationHopital, String> {

    @Query("from NotationHopital n where n.hopital.idHopital = ?1")
    List<NotationHopital> findByHopital(String idHopital);
}