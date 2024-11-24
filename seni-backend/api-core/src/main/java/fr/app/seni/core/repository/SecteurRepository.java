package fr.app.seni.core.repository;

import fr.app.seni.core.domain.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecteurRepository extends JpaRepository<Secteur, String> {
    @Query("from Secteur s where s.ville.idVille = ?1")
    List<Secteur> findByVille(String idVille);
}