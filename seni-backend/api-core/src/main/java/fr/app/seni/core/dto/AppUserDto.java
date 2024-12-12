package fr.app.seni.core.dto;


import fr.app.seni.core.enums.AppUserGroup;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto implements Serializable {

    private String idUser;
    private String username;
    private String hopital;
    private AppUserGroup groupe;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String imageUrl;
    private Boolean enabled;
    private Set<AppRoleDto> roles;
}
