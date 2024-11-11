package fr.app.seni.auth.manager.controller;

import fr.app.seni.auth.manager.service.AuthService;
import fr.app.seni.auth.manager.service.KeycloakService;
import fr.app.seni.core.dto.AppUserDto;
import fr.app.seni.core.dto.ChangePasswordRequest;
import fr.app.seni.core.enums.AppUserGroup;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.core.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AuthService authService;
    private final AppUserService appUserService;
    private final KeycloakService keycloakService;

    @GetMapping
    public ResponseEntity<AppUserDto> getAccount(Authentication authentication){
        log.info("Request to get current account: {}", authentication.getName());
        AppUserDto appUserDto = appUserService.findByUsername(authentication.getName(), AppUserGroup.MANAGER);
        if (appUserDto == null){
            throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
        }
        appUserDto.setRoles(keycloakService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateAccount(@RequestBody AppUserDto appUserDto, Authentication authentication) {
        log.info("Request to update account: {}", appUserDto);
        AppUserDto currentAppUserDto = appUserService.findByUsername(authentication.getName(), AppUserGroup.MANAGER);
        currentAppUserDto.setFirstName(appUserDto.getFirstName());
        currentAppUserDto.setLastName(appUserDto.getLastName());
        currentAppUserDto = keycloakService.updateUser(currentAppUserDto.getIdUser(), currentAppUserDto);
        currentAppUserDto.setGroupe(AppUserGroup.MANAGER);
        return ResponseEntity.ok(appUserService.update(currentAppUserDto));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Authentication authentication){
        log.info("Request to change password: {}", changePasswordRequest);
        AppUserDto appUserDto = appUserService.findByUsername(authentication.getName(), AppUserGroup.MANAGER);
        if (appUserDto == null){
            throw new CustomException("Account not found", HttpStatus.NOT_FOUND);
        }
        keycloakService.updatePassword(appUserDto.getIdUser(), changePasswordRequest.newPassword());
        return ResponseEntity.noContent().build();
    }
}