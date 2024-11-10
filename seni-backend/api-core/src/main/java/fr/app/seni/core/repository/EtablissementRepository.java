package fr.app.seni.core.repository;

import fr.app.seni.core.domain.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtablissementRepository extends JpaRepository<Etablissement, String> {
}