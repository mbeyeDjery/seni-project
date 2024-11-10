package fr.app.seni.core.repository;

import fr.app.seni.core.domain.AppUser;
import fr.app.seni.core.enums.AppUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findByIdUser(String idUser);
    List<AppUser> findByGroupe(AppUserGroup groupe);
    AppUser findByEmailAndGroupe(String email, AppUserGroup groupe);
    AppUser findByUsernameAndGroupe(String username, AppUserGroup groupe);
    AppUser findByTelephoneAndGroupe(String telephone, AppUserGroup groupe);
    List<AppUser> findByEnabledAndGroupe(Boolean enabled, AppUserGroup groupe);
}