package fr.app.seni.auth.gestion.controller;


import fr.app.seni.auth.gestion.service.UserService;
import fr.app.seni.core.dto.AppUserDto;
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

    private final UserService userService;

    @PostMapping
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUserDto appUser) {
        log.info("REQUEST to create MANAGER user : {}", appUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(appUser));
    }

    @PutMapping
    public ResponseEntity<AppUserDto> updateUser(@RequestBody AppUserDto appUser) {
        log.info("REQUEST to update MANAGER user : {}", appUser);
        return ResponseEntity.ok(userService.updateUser(appUser));
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable("idUser") String idUser) {
        log.info("REQUEST to delete MANAGER user : {}", idUser);
        userService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<AppUserDto>> getAllUsers(){
        log.info("REQUEST to list of all manager user");
        return ResponseEntity.ok(userService.getAllUsers());
    }
}