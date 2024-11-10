package fr.app.seni.auth.manager.controller;

import fr.app.seni.auth.manager.service.AuthService;
import fr.app.seni.core.dto.AuthRequest;
import fr.app.seni.core.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        log.info("Request to login user : {} ", authRequest);
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody AuthRequest authRequest){
        log.info("Request to refresh token");
        return ResponseEntity.ok(authService.refreshToken(authRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody AuthRequest authRequest){
        authService.logout(authRequest);
        return ResponseEntity.noContent().build();
    }
}