package fr.app.seni.core.service;


import fr.app.seni.core.dto.AppUserDto;
import fr.app.seni.core.enums.AppUserGroup;
import fr.app.seni.core.mapper.AppUserMapper;
import fr.app.seni.core.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserMapper appUserMapper;
    private final AppUserRepository appUserRepository;

    public AppUserDto create(AppUserDto appUser) {
        return appUserMapper.toDto(appUserRepository.save(appUserMapper.toEntity(appUser)));
    }

    public AppUserDto update(AppUserDto appUser) {
        return appUserMapper.toDto(appUserRepository.save(appUserMapper.toEntity(appUser)));
    }

    public void delete(AppUserDto appUser) {
        appUserRepository.delete(appUserMapper.toEntity(appUser));
    }

    public List<AppUserDto> findAll(AppUserGroup groupe){
        return appUserRepository.findByGroupe(groupe).stream().map(appUserMapper::toDto).collect(Collectors.toList());
    }

    public AppUserDto findOne(String userId){
        return appUserMapper.toDto(appUserRepository.findByIdUser(userId));
    }

    public AppUserDto findByUsername(String username, AppUserGroup groupe){
        return appUserMapper.toDto(appUserRepository.findByUsernameAndGroupe(username, groupe));
    }

    public AppUserDto findByEmail(String email, AppUserGroup groupe){
        return appUserMapper.toDto(appUserRepository.findByEmailAndGroupe(email, groupe));
    }

    public AppUserDto findByTelephone(String telephone, AppUserGroup groupe){
        return appUserMapper.toDto(appUserRepository.findByTelephoneAndGroupe(telephone, groupe));
    }

    public  List<AppUserDto> findAllByEnabled(Boolean enabled, AppUserGroup groupe){
        return appUserRepository.findByEnabledAndGroupe(enabled, groupe).stream().map(appUserMapper::toDto).collect(Collectors.toList());
    }
}
