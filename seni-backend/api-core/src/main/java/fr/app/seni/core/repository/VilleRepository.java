package fr.app.seni.core.repository;

import fr.app.seni.core.domain.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VilleRepository extends JpaRepository<Ville, String> {
    @Query("from Ville v where v.province.idProvince = ?1")
    List<Ville> findByProvince(String idProvince);
}