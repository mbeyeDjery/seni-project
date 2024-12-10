package fr.app.seni.auth.hopital.controller;

import fr.app.seni.auth.hopital.service.UserService;
import fr.app.seni.core.dto.AppUserDto;
import fr.app.seni.core.dto.ChangePasswordRequest;
import fr.app.seni.core.enums.AppUserGroup;
import fr.app.seni.core.service.AccountService;
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

    private final UserService userService;
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<AppUserDto> getAccount(Authentication authentication){
        log.info("Request to get current account: {}", authentication.getName());
        AppUserDto appUserDto = accountService.findByUsername(authentication.getName(), AppUserGroup.HOPITAL);
        appUserDto.setRoles(userService.getUserRole(appUserDto.getIdUser()));
        return ResponseEntity.ok(appUserDto);
    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateAccount(@RequestBody AppUserDto appUserDto, Authentication authentication) {
        log.info("Request to update account: {}", appUserDto);
        AppUserDto currentAppUserDto = accountService.findByUsername(authentication.getName(), AppUserGroup.HOPITAL);
        currentAppUserDto.setFirstName(appUserDto.getFirstName());
        currentAppUserDto.setLastName(appUserDto.getLastName());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(currentAppUserDto));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, Authentication authentication){
        log.info("Request to change password: {}", changePasswordRequest);
        AppUserDto appUserDto = accountService.findByUsername(authentication.getName(), AppUserGroup.HOPITAL);
        userService.updatePassword(appUserDto.getIdUser(), changePasswordRequest.newPassword());
        return ResponseEntity.noContent().build();
    }
}
