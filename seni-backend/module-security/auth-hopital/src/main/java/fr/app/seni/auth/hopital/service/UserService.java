package fr.app.seni.auth.hopital.service;


import fr.app.seni.auth.hopital.config.KeycloakCredentials;
import fr.app.seni.core.domain.UserHopitalId;
import fr.app.seni.core.dto.AppRoleDto;
import fr.app.seni.core.dto.AppUserDto;
import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.dto.UserHopitalDto;
import fr.app.seni.core.enums.AppUserGroup;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.core.service.AppUserService;
import fr.app.seni.core.service.HopitalService;
import fr.app.seni.core.service.UserHopitalService;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${seni-app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;
    private final AppUserService appUserService;
    private final HopitalService hopitalService;
    private final UserHopitalService userHopitalService;

    public AppUserDto createUser(AppUserDto appUser) {
        if(appUser.getHopital().isBlank()) {
            throw new CustomException("Impossible de créé un utilisateur sans un hopital asocié", HttpStatus.BAD_REQUEST);
        } else if (appUser.getIdUser() != null) {
            throw new CustomException("Le compte existe déja", HttpStatus.BAD_REQUEST);
        } else if (appUserService.findByUsername(appUser.getUsername(), AppUserGroup.HOPITAL) != null) {
            throw new CustomException("Ce login ne peut pas être utilisé", HttpStatus.CONFLICT);
        } else if (appUserService.findByEmail(appUser.getEmail(), AppUserGroup.HOPITAL) != null) {
            throw new CustomException("Un utilisateur avec cet email existe déja", HttpStatus.CONFLICT);
        } else if (appUserService.findByTelephone(appUser.getTelephone(), AppUserGroup.HOPITAL) != null) {
            throw new CustomException("Un utilisateur avec ce numéro de téléphone existe déja", HttpStatus.CONFLICT);
        } else {
            HopitalDto hopital = hopitalService.findOne(appUser.getHopital());
            if (hopital == null) {
                throw new CustomException("Hopital inexistant", HttpStatus.BAD_REQUEST);
            }
            try {
                Response response = getUsersResource().create(mapAppUserToKeycloakUser(appUser));
                if(!Objects.equals(201,response.getStatus())) {
                    throw new CustomException("Erreur interne", HttpStatus.BAD_REQUEST);
                }
                String userId = CreatedResponseUtil.getCreatedId(response);
                appUser.getRoles().forEach(role -> addRoleToUser(userId, role.getRoleName()));
                appUser = getKeycloakUserById(userId);
                appUser.setGroupe(AppUserGroup.HOPITAL);
                appUser = appUserService.create(appUser);
                appUser.setRoles(getUserRole(appUser.getIdUser()));

                UserHopitalId userHopitalId = new UserHopitalId();
                userHopitalId.setIdUser(appUser.getIdUser());
                userHopitalId.setIdHopital(appUser.getHopital());
                userHopitalService.update(UserHopitalDto.builder()
                        .id(userHopitalId)
                        .user(appUser)
                        .hopital(hopital)
                        .build());

                return appUser;
            }catch (Exception e){
                e.printStackTrace();
                throw new CustomException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public AppUserDto updateUser(AppUserDto appUser) {
        HopitalDto hopital = hopitalService.findOne(appUser.getHopital());
        if (hopital == null) {
            throw new CustomException("Hopital inexistant", HttpStatus.BAD_REQUEST);
        }
        try {
            String userId = appUser.getIdUser();
            AppUserDto userKeycloak = getKeycloakUserById(userId);
            userKeycloak.setLastName(appUser.getLastName());
            userKeycloak.setFirstName(appUser.getFirstName());
            userKeycloak.setHopital(hopital.getIdHopital());
            getUsersResource().get(userId).update(mapAppUserToKeycloakUser(userKeycloak));
            getUsersResource().get(userId).roles().realmLevel().listAll().forEach(role -> removeRoleFromUser(userId, role.getName()));
            appUser.getRoles().forEach(role -> addRoleToUser(userId, role.getRoleName()));

            userKeycloak.setGroupe(AppUserGroup.HOPITAL);
            userKeycloak = appUserService.update(userKeycloak);
            userKeycloak.setRoles(getUserRole(userId));

            UserHopitalDto userHopital = userHopitalService.findOneByUser(userId);
            if (userHopital != null && !userHopital.getId().getIdHopital().equals(appUser.getHopital())) {
                userHopitalService.delete(userHopital);

                UserHopitalId userHopitalId = new UserHopitalId();
                userHopitalId.setIdUser(appUser.getIdUser());
                userHopitalId.setIdHopital(appUser.getHopital());
                userHopitalService.update(UserHopitalDto.builder()
                        .id(userHopitalId)
                        .user(appUser)
                        .hopital(hopital)
                        .build());
            }

            return userKeycloak;
        }catch (Exception e){
            if (e instanceof NotFoundException){
                throw new CustomException("L'utilisateur n'existe pas", HttpStatus.NOT_FOUND);
            }else {
                throw new CustomException("Erreur interne", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void deleteUser(String userId) {
        try {
            AppUserDto appUser = appUserService.findOne(userId);
            if (appUser == null){
                throw new CustomException("Utilisateur introuvable", HttpStatus.NOT_FOUND);
            }
            Response response = getUsersResource().delete(userId);
            userHopitalService.delete(userHopitalService.findOneByUser(appUser.getIdUser()));
            appUserService.delete(appUser);
        }catch (Exception e){
            throw new CustomException("Erreur interne", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updatePassword(String userId, String newPassword) {
        try {
            getUsersResource().get(userId).resetPassword(KeycloakCredentials.createPasswordCredentials(newPassword));
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            throw new CustomException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<AppUserDto> getAllUsers(String idHopital) {
        List<AppUserDto> appUsers = userHopitalService.findByHopital(idHopital).stream().map(UserHopitalDto::getUser).toList();
        for (AppUserDto appUser : appUsers) {
            appUser.setRoles(getUserRole(appUser.getIdUser()));
        }
        return appUsers;
    }

    public AppUserDto getKeycloakUserById(String userId) {
        try {
            return mapKeycloakUserToAppUser(getUsersResource().get(userId).toRepresentation());
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            if (e instanceof NotFoundException){
                throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
            }else {
                throw new CustomException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public Set<AppRoleDto> getUserRole(String userId) {
        return getUsersResource().get(userId).roles().realmLevel()
                .listAll()
                .stream()
                .filter(role -> role.getName().startsWith("ROLE_"))
                .map(role -> AppRoleDto.builder()
                        .roleId("")
                        .roleName(role.getName())
                        .build())
                .collect(Collectors.toSet());
    }

    public void addRoleToUser(String userId, String roleName) {
        try {
            List<RoleRepresentation> roleToAdd = new LinkedList<>();
            roleToAdd.add(getRolesResource().get(roleName).toRepresentation());
            getUsersResource().get(userId).roles().realmLevel().add(roleToAdd);
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            if (e instanceof NotFoundException){
                throw new CustomException("Role not found", HttpStatus.NOT_FOUND);
            }else {
                throw new CustomException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void removeRoleFromUser(String userId, String roleName) {
        try {
            List<RoleRepresentation> roleToRemove = new LinkedList<>();
            roleToRemove.add(getRolesResource().get(roleName).toRepresentation());
            getUsersResource().get(userId).roles().realmLevel().remove(roleToRemove);
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            if (e instanceof NotFoundException){
                throw new CustomException("Internal error", HttpStatus.NOT_FOUND);
            }else {
                throw new CustomException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private static UserRepresentation mapAppUserToKeycloakUser(AppUserDto appUserDto) {
        UserRepresentation userRepresentation= new UserRepresentation();
//        Definir les infos principales de l'utilisateur
        userRepresentation.setEnabled(appUserDto.getEnabled());
        userRepresentation.setLastName(appUserDto.getLastName());
        userRepresentation.setUsername(appUserDto.getUsername());
        userRepresentation.setEmailVerified(appUserDto.getEnabled());
        userRepresentation.setEmail(appUserDto.getEmail().toLowerCase());
        userRepresentation.setFirstName(appUserDto.getFirstName().toUpperCase());

//        Definir les attributs  supplementaire
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("telephone", appUserDto.getTelephone() != null ? List.of(appUserDto.getTelephone()) : null);
        attributes.put("hopital", appUserDto.getHopital() != null ? List.of(appUserDto.getHopital()) : null);
        userRepresentation.setAttributes(attributes);

//        Definit le mot de passe si c'est pas null
        if (appUserDto.getPassword() != null && !appUserDto.getPassword().isBlank()){
            userRepresentation.setCredentials(Collections.singletonList(KeycloakCredentials.createPasswordCredentials(appUserDto.getPassword())));
        }

        return userRepresentation;
    }

    public AppUserDto mapKeycloakUserToAppUser(UserRepresentation userRepresentation) {
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setEnabled(userRepresentation.isEnabled());
        appUserDto.setLastName(userRepresentation.getLastName());
        appUserDto.setUsername(userRepresentation.getUsername());
        appUserDto.setEmail(userRepresentation.getEmail().toLowerCase());
        appUserDto.setFirstName(userRepresentation.getFirstName().toUpperCase());
//        appUserDto.setCreatedDate(Instant.ofEpochMilli(userRepresentation.getCreatedTimestamp()));
        appUserDto.setIdUser(userRepresentation.getId());
        appUserDto.setRoles(getUserRole(userRepresentation.getId()));
        Map<String, List<String>> attributes = userRepresentation.getAttributes();
        if (attributes != null) {
            appUserDto.setTelephone(attributes.get("telephone") != null ? attributes.get("telephone").getFirst() : null);
            appUserDto.setHopital(attributes.get("hopital") != null ? attributes.get("hopital").getFirst() : null);
        }
        return appUserDto;
    }

    public List<RoleRepresentation> getAllRealmRoles() {
        return getRolesResource().list();
    }

    private UsersResource getUsersResource(){
        return keycloak.realm(realm).users();
    }
    private RolesResource getRolesResource(){
        return keycloak.realm(realm).roles();
    }
}
