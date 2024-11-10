package fr.app.seni.core.service;


import fr.app.seni.core.domain.UserEtablissement;
import fr.app.seni.core.dto.TypeEtablissementDto;
import fr.app.seni.core.mapper.TypeEtablissementMapper;
import fr.app.seni.core.repository.TypeEtablissementRepository;
import fr.app.seni.core.repository.UserEtablissementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEtablissementService {

    private final UserEtablissementRepository userEtablissementRepository;

    public UserEtablissement create(UserEtablissement userEtablissement){
        return userEtablissementRepository.save(userEtablissement);
    }

    public UserEtablissement update(UserEtablissement userEtablissement){
        return userEtablissementRepository.save(userEtablissement);
    }

    public void delete(UserEtablissement userEtablissement){
        userEtablissementRepository.delete(userEtablissement);
    }

}
