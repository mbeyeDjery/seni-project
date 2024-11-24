package fr.app.seni.core.repository;

import fr.app.seni.core.domain.ContratHopital;
import fr.app.seni.core.enums.ContratHopitalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContratHopitalRepository extends JpaRepository<ContratHopital, String> {

    @Query("from ContratHopital c where c.hopital.idHopital = ?1")
    List<ContratHopital> findByHopital(String idHopital);

    @Query("from ContratHopital c where c.hopital.idHopital = ?1 and c.statut = ?2")
    List<ContratHopital> findByHopitalAndStatut(String idHopital, ContratHopitalStatus statut);
}