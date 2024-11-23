package fr.app.seni.auth.manager.controller;

import fr.app.seni.auth.manager.service.KeycloakService;
import fr.app.seni.core.dto.AddRemoveRoleRequest;
import fr.app.seni.core.dto.AppUserDto;
import fr.app.seni.core.enums.AppUserGroup;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.core.service.AppUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final AppUserService appUserService;
    private final KeycloakService keycloakService;

    @PostMapping
    public ResponseEntity<AppUserDto> createUser(@Valid @RequestBody AppUserDto appUserDto) {
        log.info("REQUEST to create MANAGER user : {}", appUserDto);
        if (appUserDto.getIdUser() != null) {
            throw new CustomException("Le compte existe déja", HttpStatus.BAD_REQUEST);
        } else if (appUserService.findByUsername(appUserDto.getUsername(), AppUserGroup.MANAGER) != null) {
            throw new CustomException("Login déja utilisé", HttpStatus.CONFLICT);
        } else if (appUserService.findByEmail(appUserDto.getEmail(), AppUserGroup.MANAGER) != null) {
            throw new CustomException("Un compte avec cet email existe déja", HttpStatus.CONFLICT);
        }else if (appUserService.findByTelephone(appUserDto.getTelephone(), AppUserGroup.MANAGER) != null) {
            throw new CustomException("Un compte avec ce téléphone existe déja", HttpStatus.CONFLICT);
        }else {
            appUserDto.setEmail(appUserDto.getEmail().toLowerCase());
            appUserDto.setFirstName(appUserDto.getFirstName().toUpperCase());
            appUserDto = keycloakService.createUser(appUserDto);
            appUserDto.setGroupe(AppUserGroup.MANAGER);
            appUserDto = appUserService.create(appUserDto);
            appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
            return ResponseEntity.status(HttpStatus.CREATED).body(appUserDto);
        }
    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateUser(@Valid @RequestBody AppUserDto appUserDto) {
        log.info("REQUEST to update MANAGER user : {}", appUserDto);
        appUserDto.setFirstName(appUserDto.getFirstName().toUpperCase());
        appUserDto = keycloakService.updateUser(appUserDto.getIdUser(), appUserDto);
        appUserDto.setGroupe(AppUserGroup.MANAGER);
        appUserDto = appUserService.update(appUserDto);
        appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable("idUser") String idUser) {
        log.info("REQUEST to delete MANAGER user : {}", idUser);
        AppUserDto appUserDto = appUserService.findOne(idUser);
        if (appUserDto == null){
            throw new CustomException("Compte introuvable", HttpStatus.NOT_FOUND);
        }
        keycloakService.deleteUserById(appUserDto.getIdUser());
        appUserService.delete(appUserDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<AppUserDto>> getAllUsers(){
        log.info("REQUEST to list of manager user");
        List<AppUserDto> appUserDtoList = appUserService.findAll(AppUserGroup.MANAGER);
        for (AppUserDto appUserDto : appUserDtoList) {
            appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        }
        return ResponseEntity.ok(appUserDtoList);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<AppUserDto> getUser(@PathVariable("idUser") String idUser) {
        AppUserDto appUserDto = appUserService.findOne(idUser);
        if (appUserDto == null){
            throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
        }

        appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<AppUserDto> searchUserByUsername(@PathVariable("username") String username) {
        AppUserDto appUserDto = appUserService.findByUsername(username, AppUserGroup.MANAGER);
        if (appUserDto == null){
            throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
        }
        appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AppUserDto> searchUserByEmail(@PathVariable("email") String email) {
        AppUserDto appUserDto = appUserService.findByEmail(email, AppUserGroup.MANAGER);
        if (appUserDto == null){
            throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
        }
        appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping("/phone/{telephone}")
    public ResponseEntity<AppUserDto> searchUserByPhone(@PathVariable("telephone") String telephone) {
        AppUserDto appUserDto = appUserService.findByTelephone(telephone, AppUserGroup.MANAGER);
        if (appUserDto == null){
            throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
        }
        appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @GetMapping("/enabled/{enabled}")
    public ResponseEntity<List<AppUserDto>> searchUserByEnabled(@PathVariable("enabled") Boolean enabled) {
        List<AppUserDto> appUserDtoList = appUserService.findAllByEnabled(enabled, AppUserGroup.MANAGER);
        for (AppUserDto appUserDto : appUserDtoList) {
            appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        }
        return ResponseEntity.ok(appUserDtoList);
    }

    @PutMapping("/add-role")
    public ResponseEntity<Void> addRoleToUser(@RequestBody AddRemoveRoleRequest addRemoveRoleRequest) {
        keycloakService.addRoleToUser(addRemoveRoleRequest.idUser(), addRemoveRoleRequest.roleName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remove-role")
    public ResponseEntity<Void> removeRoleFromUser(@RequestBody AddRemoveRoleRequest addRemoveRoleRequest) {
        keycloakService.removeRoleFromUser(addRemoveRoleRequest.idUser(), addRemoveRoleRequest.roleName());
        return ResponseEntity.noContent().build();
    }
}
