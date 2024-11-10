package fr.app.seni.core.service;


import fr.app.seni.core.dto.EtablissementDto;
import fr.app.seni.core.mapper.EtablissementMapper;
import fr.app.seni.core.repository.EtablissementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EtablissementService {

    private final EtablissementMapper etablissementMapper;
    private final EtablissementRepository etablissementRepository;

    public EtablissementDto create(EtablissementDto etablissementDto){
        return etablissementMapper.toDto(etablissementRepository.save(etablissementMapper.toEntity(etablissementDto)));
    }

    public EtablissementDto update(EtablissementDto etablissementDto){
        return etablissementMapper.toDto(etablissementRepository.save(etablissementMapper.toEntity(etablissementDto)));
    }

    public void delete(EtablissementDto etablissementDto){
        etablissementRepository.delete(etablissementMapper.toEntity(etablissementDto));
    }

    public List<EtablissementDto> findAll(){
        return etablissementRepository.findAll().stream().map(etablissementMapper::toDto).collect(Collectors.toList());
    }

    public EtablissementDto findOne(String id){
        return etablissementMapper.toDto(etablissementRepository.findById(id).get());
    }
}
