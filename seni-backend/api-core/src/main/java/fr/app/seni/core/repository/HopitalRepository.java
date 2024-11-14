package fr.app.seni.core.repository;

import fr.app.seni.core.domain.Hopital;
import fr.app.seni.core.domain.TypeHopital;
import fr.app.seni.core.enums.HopitalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopitalRepository extends JpaRepository<Hopital, String> {

    List<Hopital> findByOnline(Boolean online);

    Hopital findByCodeHopital(String codeHopital);

    List<Hopital> findByStatut(HopitalStatus status);

    List<Hopital> findByTypeHopital(TypeHopital typeHopital);
}